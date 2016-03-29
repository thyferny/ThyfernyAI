package org.trimps.images.analyzer.dna;

import java.awt.image.BufferedImage;
import java.io.File;

import org.trimps.images.analyzer.core.util.ImageUtils;
import org.trimps.images.analyzer.sift.render.RenderImage;

public class ImageDNA {

	public static void main(String[] args) {
//		String src = "0, 36, 14, 30, 14, 9, 2, 68, 20, 1, 0, 75, 37, 18, 15, 44, 4, 32, 8, 20, 0, 65, 15, 5, 0, 42, 15, 43, 2, 45, 21, 34, 18, 9, 5, 56, 94, 41, 3, 56, 20, 51, 0, 72, 10, 19, 5, 49, 8, 7, 0, -110, 10, 14, 2, -117, 19, 17, 6, 95, 123, 58, 26, 48, 16, 99, 15, 79, 24, 1, 0, 97, 4, 23, 1, 104, 15, 16, 2, -111, -124, 7, 57, 123, -72, 70, 94, 75, 32, -35, 101, 72, 21, 30, 11, 99, 23, 49, -98, 39, 64, 38, -66, 43, -27, 13, -57, 64, 82, 79, 107, -122, 79, -87, -105, 116, 38, -100, -46, 53, 44, -119, -30, 22, 101, 92, -13, 32, 77, 62, -61, 53, 35, 34, -41, 76, 52, 28, -80, 42, 46, 59, -90, 49";
//		String[] arr = src.split(",");
//		byte[] srcInt = new byte[arr.length];
//		int i = 0;
//		for(String s:arr){
//			srcInt[i] = Byte.parseByte(s.trim());
//			i++;
//		}
		DnaResult ret1 = ComputeII144Hash("d:/1-1.jpg");
		
		for(File file:new File("C:\\Users\\thyferny.THYFERNY-LAPTOP\\Desktop\\测试照片\\市局").listFiles()){
			long start = System.currentTimeMillis();
			DnaResult ret2 = ComputeII144Hash(file.getAbsolutePath());
			long end = System.currentTimeMillis();
			System.out.println();
			int sum = 0;
			for(int j=0;j<144;j++){
				sum+=((ret1.hashValue[j] - ret2.hashValue[j])*(ret1.hashValue[j] - ret2.hashValue[j]));
			}
			System.out.println(file.getName()+":"+Math.sqrt(sum)+":"+(end-start));
		}
		;
		
	}
	
	private static final int S_OK                                            = 0x0000;
	private static final int COMPUTE_HASH_FAILED                             = 0x0200;
	private static final int COMPUTE_HASH_BAD_ARGUMENT                       = 0x0201;
	private static final int COMPUTE_HASH_NO_HASH_CALCULATED                 = 0x0202;
	private static final int INITIALIZE_FAILED                               = 0x0203;
	private static final int INITIALIZE_BAD_ARGUMENT                         = 0x0204;
	private static final int GETMATCH_FAILED                                 = 0x0205;
	private static final int GETMATCH_BAD_ARGUMENT                           = 0x0206;
	private static final int UNSUPPORTED_MATCH_ALGORITHM                     = 0x0207;
	private static final int EMPTY_DATASET                                   = 0x0208;
	private static final int OPEN_DATASET_FILE_FAILED                        = 0x0209;
	private static final int GETMATCH_LIBRARY_NOT_INITIALIZED                = 0x0210;
	private static final int EMPTY_DATASET_ENTRY                             = 0x0211;
	private static final int CLOSE_DATASET_FILE_FAILED                       = 0x0212;
	private static final int COMPUTE_HASH_NO_HASH_CALCULATED_IMAGE_TOO_SMALL = 0x0213;
	private static final int COMPUTE_HASH_NO_HASH_CALCULATED_IMAGE_IS_FLAT   = 0x0214;
	private static final int E_OUTOFMEMORY                                   = 0x000E;

	private static final int NUM_BINS = 6;
	private static final int QUAD_SIZE = 4;
	private static final int PATCH_SIZE = NUM_BINS*QUAD_SIZE + 2;
	
	

	public static DnaResult ComputeII144Hash(String imageFile)// , byte *hashValue
	{
		BufferedImage bufferedImage = ImageUtils.readStandard(imageFile);
		RenderImage renderImage = new RenderImage(bufferedImage);
		
		int height = renderImage.getHeight();
        int width = renderImage.getWidth();
        int[] pix = bufferedImage.getRGB(0, 0, width, height, null, 0, width);
        int[] buffer = renderImage.toRGBArray();
//		int height = floatArray.height;
//		int width = floatArray.width;
//		float[] buffer = floatArray.data;
		int pb;
		// SIFT sift1 = new SIFT();
		// sift1.detectFeatures();
		int hResult = S_OK;
		int r, c, i, j;
		float intensity;

		// If image is too small return
		if (width < 50 || height < 50) {
			return new DnaResult(COMPUTE_HASH_NO_HASH_CALCULATED_IMAGE_TOO_SMALL, null);
		}

		// Initialize SumImage Array
		float[] sumImage = new float[width * height];

		// First step is to create an integral image used for quick downsampling
		pb = 0;

		// Handle border cases
		intensity = buffer[pb];
		pb++;
		intensity += buffer[pb];
		pb++;
		intensity += buffer[pb];
		pb++;

		intensity /= 3.0;

		sumImage[0] = intensity;

		for (c = 1; c < width; c++) {
			intensity = buffer[pb];
			pb++;
			intensity += buffer[pb];
			pb++;
			intensity += buffer[pb];
			pb++;

			intensity /= 3.0;

			sumImage[c] = intensity + sumImage[c - 1];
		}

		for (r = 1; r < height; r++) {
			intensity = buffer[pb];
			pb++;
			intensity += buffer[pb];
			pb++;
			intensity += buffer[pb];
			pb++;

			intensity /= 3.0;

			sumImage[r * width] = intensity + sumImage[(r - 1) * width];

			for (c = 1; c < width; c++) {
				// Compute rest of pixel values after border cases are handled
				intensity = buffer[pb];
				pb++;
				intensity += buffer[pb];
				pb++;
				intensity += buffer[pb];
				pb++;

				intensity /= 3.0;

				sumImage[r * width + c] = intensity + sumImage[(r - 1) * width + c] + sumImage[r * width + c - 1] - sumImage[(r - 1) * width + c - 1];
			}
		}

		// Downsample the image into a (6*quadSize + 2) x (6*quadSize + 2) patch

		double patchSampleDistH = (double) width / ((double) (PATCH_SIZE + 2));
		double patchSampleDistV = (double) height / ((double) (PATCH_SIZE + 2));
		double[][][] box = new double[3][4][2];
		double[][] boxMult = new double[3][4];
		double[] patch = new double[PATCH_SIZE * PATCH_SIZE];
		double sigma0 = 0.5 * 0.8;
		double sigma1 = 1.0 * 0.8;
		double sigma2 = 2.0 * 0.8;

		// Approximate a Gaussian blur using three fast to compute box filters
		for (i = 0; i < 3; i++) {
			double sigma = 0;
			double scale = 0;

			if (i == 0) {
				sigma = sigma0;
				scale = Math.exp(-(sigma0 * sigma0) / 2.0) - Math.exp(-(sigma1 * sigma1) / 2.0);
			}

			if (i == 1) {
				sigma = sigma1;
				scale = Math.exp(-(sigma1 * sigma1) / 2.0) - Math.exp(-(sigma2 * sigma2) / 2.0);
			}

			if (i == 2) {
				sigma = sigma2;
				scale = Math.exp(-(sigma2 * sigma2) / 2.0);
			}

			j = 0;
			for (r = -1; r <= 1; r += 2)
				for (c = -1; c <= 1; c += 2) {
					box[i][j][0] = (double) c * patchSampleDistH * sigma / 2.0;
					box[i][j][1] = (double) r * patchSampleDistV * sigma / 2.0;
					boxMult[i][j] = scale;
					j++;

				}

			box[i][0][0]--;
			box[i][0][1]--;
			box[i][1][1]--;
			box[i][2][0]--;

			boxMult[i][1] = -boxMult[i][1];
			boxMult[i][2] = -boxMult[i][2];
		}

		double scalar = patchSampleDistH * patchSampleDistV * sigma0 * sigma0 * boxMult[0][0] + patchSampleDistH * patchSampleDistV * sigma1 * sigma1 * boxMult[1][0] + patchSampleDistH * patchSampleDistV * sigma2 * sigma2 * boxMult[2][0];

		// Compute patch values
		for (r = 0; r < PATCH_SIZE; r++) {
			for (c = 0; c < PATCH_SIZE; c++) {
				double x0 = ((double) c + 1.5) * patchSampleDistH;
				double y0 = ((double) r + 1.5) * patchSampleDistV;

				patch[r * PATCH_SIZE + c] = 0.0;

				for (i = 0; i < 3; i++) {
					double boxAvg = 0.0;

					for (j = 0; j < 4; j++) {
						double x1 = x0 + box[i][j][0];
						double y1 = y0 + box[i][j][1];
						// make sure sample points are within the image
						// boundaries
						double x1in = Math.min((double) width - 2.0, Math.max(0.0, x1));
						double y1in = Math.min((double) height - 2.0, Math.max(0.0, y1));

						// Bilinearly interpolate value
						int ix = (int) x1in;
						int iy = (int) y1in;

						double xd = x1in - (double) ix;
						double yd = y1in - (double) iy;

						double val = 0.0;

						val += (1.0 - xd) * (1.0 - yd) * sumImage[iy * width + ix];
						val += (1.0 - xd) * (yd) * sumImage[(iy + 1) * width + ix];
						val += (xd) * (1.0 - yd) * sumImage[iy * width + ix + 1];
						val += (xd) * (yd) * sumImage[(iy + 1) * width + (ix + 1)];

						boxAvg += val * boxMult[i][j];
					}

					// Add box filter response to patch value
					patch[r * PATCH_SIZE + c] += boxAvg;
				}

				patch[r * PATCH_SIZE + c] /= scalar;

			}
		}

		// Compute edge histograms for fuzzy hash values
		double[] histogram = new double[NUM_BINS * NUM_BINS * 4];
		int rbin, cbin, rdx, cdx, rb, cb;
		int histIdx;
		int featureOrien = 4;
		int offset1 = NUM_BINS * featureOrien;
		int offset2 = featureOrien;

		// Initialize histogram values
		int histInitId = 0;
		for (histInitId = 0; histInitId < NUM_BINS * NUM_BINS * 4; histInitId++) {
			histogram[histInitId] = 0;
		}

		// Create hash from rectified horizontal and vertical gradients
		for (rbin = 0; rbin < NUM_BINS; rbin++) {
			for (cbin = 0; cbin < NUM_BINS; cbin++) {
				for (rdx = 0; rdx < QUAD_SIZE; rdx++) {
					for (cdx = 0; cdx < QUAD_SIZE; cdx++) {
						r = rbin * QUAD_SIZE + rdx + 1;
						c = cbin * QUAD_SIZE + cdx + 1;

						double diffr, diffc;
						double rect0, rect1, rect2, rect3;

						diffc = patch[(r) * PATCH_SIZE + c - 1];
						diffc -= patch[(r) * PATCH_SIZE + c + 1];
						diffr = patch[(r - 1) * PATCH_SIZE + c];
						diffr -= patch[(r + 1) * PATCH_SIZE + c];

						if (diffc > 0.f) {
							rect0 = diffc;
							rect1 = 0.f;
						} else {
							rect0 = 0.f;
							rect1 = -diffc;
						}

						if (diffr > 0.f) {
							rect2 = diffr;
							rect3 = 0.f;
						} else {
							rect2 = 0.f;
							rect3 = -diffr;
						}

						double rf = (double) r - 2.5;
						double cf = (double) c - 2.5;

						rf /= (double) QUAD_SIZE;
						cf /= (double) QUAD_SIZE;

						rb = (int) Math.floor(rf);
						cb = (int) Math.floor(cf);

						double rd = rf - (double) rb;
						double cd = cf - (double) cb;

						double del;

						// Add gradients to histogram using bilinear
						// interpolation
						if (rb >= 0 && cb >= 0) {
							del = (1.f - rd) * (1.f - cd);
							histIdx = rb * offset1 + cb * offset2;
							histogram[histIdx + 0] += rect0 * del;
							histogram[histIdx + 1] += rect1 * del;
							histogram[histIdx + 2] += rect2 * del;
							histogram[histIdx + 3] += rect3 * del;
						}

						if (rb >= 0 && cb < NUM_BINS - 1) {
							del = (1.f - rd) * (cd);
							histIdx = rb * offset1 + (cb + 1) * offset2;
							histogram[histIdx + 0] += rect0 * del;
							histogram[histIdx + 1] += rect1 * del;
							histogram[histIdx + 2] += rect2 * del;
							histogram[histIdx + 3] += rect3 * del;
						}

						if (rb < NUM_BINS - 1 && cb >= 0) {
							del = (rd) * (1.f - cd);
							histIdx = (rb + 1) * offset1 + cb * offset2;
							histogram[histIdx + 0] += rect0 * del;
							histogram[histIdx + 1] += rect1 * del;
							histogram[histIdx + 2] += rect2 * del;
							histogram[histIdx + 3] += rect3 * del;
						}

						if (rb < NUM_BINS - 1 && cb < NUM_BINS - 1) {
							del = (rd) * (cd);
							histIdx = (rb + 1) * offset1 + (cb + 1) * offset2;
							histogram[histIdx + 0] += rect0 * del;
							histogram[histIdx + 1] += rect1 * del;
							histogram[histIdx + 2] += rect2 * del;
							histogram[histIdx + 3] += rect3 * del;
						}
					}
				}
			}
		}

		// Make sure no value in the histogram is greater than 0.25. This
		// results is a better/more uniform distribution of hash values.
		int iter = 0;
		boolean found = true;
		double maxThres = 0.25;

		while (iter < 10 && found == true) {
			iter++;
			double denom = 0.00000001;

			for (rb = 0; rb < NUM_BINS; rb++) {
				for (cb = 0; cb < NUM_BINS; cb++) {
					for (i = 0; i < featureOrien; i++) {
						histIdx = rb * offset1 + cb * offset2 + i;
						denom += histogram[histIdx] * histogram[histIdx];
					}
				}
			}

			// If denom is zero return no hash. This should only happen if the
			// image is flat.
			if (denom <= 0.0) {
				// delete[] sumImage; java would release
				sumImage = null;
				return new DnaResult(COMPUTE_HASH_NO_HASH_CALCULATED_IMAGE_IS_FLAT, null);
			}

			denom = Math.sqrt(denom);

			found = false;

			for (rb = 0; rb < NUM_BINS; rb++) {
				for (cb = 0; cb < NUM_BINS; cb++) {
					for (i = 0; i < featureOrien; i++) {
						histIdx = rb * offset1 + cb * offset2 + i;
						histogram[histIdx] /= denom;

						if (histogram[histIdx] >= maxThres && iter < 10) {
							histogram[histIdx] = maxThres;
							found = true;
						}
					}
				}
			}
		}

		// Create final array to return hash values

		int k = 0;
		byte[] hashValue = new byte[NUM_BINS * NUM_BINS * featureOrien];
		for (rb = 0; rb < NUM_BINS; rb++) {
			for (cb = 0; cb < NUM_BINS; cb++) {
				for (i = 0; i < featureOrien; i++) {
					hashValue[k] = (byte) Math.min(255.0, 256.0 * histogram[rb * offset1 + cb * offset2 + i] / maxThres);
					k++;
				}
			}
		}

		// delete[] sumImage;
		sumImage = null;

		return new DnaResult(hResult, hashValue);
	}
}
