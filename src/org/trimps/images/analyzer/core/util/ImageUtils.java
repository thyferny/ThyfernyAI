
package org.trimps.images.analyzer.core.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.media.jai.ImageLayout;
import javax.media.jai.JAI;

import org.apache.commons.io.IOUtils;
import org.trimps.images.analyzer.core.ImageFormat;
import org.trimps.images.analyzer.core.io.ByteArrayInputStream;
import org.trimps.images.analyzer.core.io.ByteArrayOutputStream;


public final class ImageUtils {

	private static final float FLOAT_MIN = -Float.MAX_VALUE;

	private ImageUtils() {

	}

	public static void closeQuietly(ImageInputStream inStream) {
		if (inStream != null) {
			try {
				inStream.close();
			} catch (IOException ignore) {

			}
		}
	}

	public static void closeQuietly(ImageOutputStream outStream) {
		if (outStream != null) {
			try {
				outStream.close();
			} catch (IOException ignore) {

			}
		}
	}

	public static void closeQuietly(org.trimps.images.analyzer.core.io.ImageInputStream stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException ignore) {

			}
		}
	}

	public static void closeQuietly(ImageReader reader) {
		if (reader != null) {
			try {
				reader.dispose();
			} catch (Exception igonre) {

			}
		}
	}

	public static ImageLayout getImageLayoutHint(RenderingHints renderHints) {
		if (renderHints == null) {
			return null;
		} else {
			return (ImageLayout) renderHints.get(JAI.KEY_IMAGE_LAYOUT);
		}
	}

	public static boolean isJPEG(InputStream source) throws IOException {
		InputStream iis = source;

		if (!source.markSupported()) { throw new IllegalArgumentException("Input stream must support mark"); }

		iis.mark(30);
		// If the first two bytes are a JPEG SOI marker, it's probably
		// a JPEG file. If they aren't, it definitely isn't a JPEG file.
		try {
			int byte1 = iis.read();
			int byte2 = iis.read();
			if ((byte1 == 0xFF) && (byte2 == 0xD8)) {

			return true; }
		} finally {
			iis.reset();
		}

		return false;
	}

	public static boolean isBMP(InputStream in) throws IOException {
		if (!in.markSupported()) { throw new IllegalArgumentException("Input stream must support mark"); }

		byte[] b = new byte[2];
		try {
			in.mark(30);
			in.read(b);
		} finally {
			in.reset();
		}

		return (b[0] == 0x42) && (b[1] == 0x4d);
	}

	public static boolean isGIF(InputStream in) throws IOException {
		if (!in.markSupported()) { throw new IllegalArgumentException("Input stream must support mark"); }

		byte[] b = new byte[6];

		try {
			in.mark(30);
			in.read(b);
		} finally {
			in.reset();
		}

		return b[0] == 'G' && b[1] == 'I' && b[2] == 'F' && b[3] == '8' && (b[4] == '7' || b[4] == '9') && b[5] == 'a';
	}

	public static boolean isPNG(InputStream in) throws IOException {
		if (!in.markSupported()) { throw new IllegalArgumentException("Input stream must support mark"); }

		byte[] b = new byte[8];
		try {
			in.mark(30);
			in.read(b);
		} finally {
			in.reset();
		}

		return (b[0] == (byte) 137 && b[1] == (byte) 80 && b[2] == (byte) 78 && b[3] == (byte) 71 && b[4] == (byte) 13 && b[5] == (byte) 10 && b[6] == (byte) 26 && b[7] == (byte) 10);
	}

	public static boolean isTIFF(InputStream in) throws IOException {
		if (!in.markSupported()) { throw new IllegalArgumentException("Input stream must support mark"); }
		byte[] b = new byte[4];
		try {
			in.mark(30);
			in.read(b);
		} finally {
			in.reset();
		}

		return ((b[0] == (byte) 0x49 && b[1] == (byte) 0x49 && b[2] == (byte) 0x2a && b[3] == (byte) 0x00) || (b[0] == (byte) 0x4d && b[1] == (byte) 0x4d && b[2] == (byte) 0x00 && b[3] == (byte) 0x2a));
	}

	public static ImageFormat identifyFormat(InputStream in) throws IOException {
		if (isJPEG(in)) { return ImageFormat.JPEG; }

		if (isPNG(in)) { return ImageFormat.PNG; }

		if (isGIF(in)) { return ImageFormat.GIF; }

		if (isBMP(in)) { return ImageFormat.BMP; }

		if (isTIFF(in)) { return ImageFormat.TIFF; }

		return null;
	}

	public static InputStream createMemoryStream(InputStream input) throws IOException {
		if ((!(input instanceof ByteArrayInputStream)) && (!(input instanceof java.io.ByteArrayInputStream))) {
			ByteArrayOutputStream temp = new ByteArrayOutputStream();
			IOUtils.copy(input, temp);

			input = temp.toInputStream();
			temp = null;
		}

		return input;
	}

	public static final byte clampRoundByte(double in) {
		return (in > 0xFF ? (byte) 0xFF : (in >= 0 ? (byte) (in + 0.5) : (byte) 0));
	}

	public static final short clampRoundUShort(double in) {
		return (in > 0xFFFF ? (short) 0xFFFF : (in >= 0 ? (short) (in + 0.5) : (short) 0));
	}

	public static final short clampRoundShort(double in) {
		return (in > Short.MAX_VALUE ? Short.MAX_VALUE : (in >= Short.MIN_VALUE ? (short) Math.floor(in + 0.5) : Short.MIN_VALUE));
	}

	public static final int clampRoundInt(double in) {
		return (in > Integer.MAX_VALUE ? Integer.MAX_VALUE : (in >= Integer.MIN_VALUE ? (int) Math.floor(in + 0.5) : Integer.MIN_VALUE));
	}

	public static final float clampFloat(double in) {
		return (in > Float.MAX_VALUE ? Float.MAX_VALUE : (in >= FLOAT_MIN ? (float) in : FLOAT_MIN));
	}

	public static BufferedImage readStandard(String srcImageFile){
		try {
			double ratio = 0.0; // 缩放比例
			File f = new File(srcImageFile);
			BufferedImage bi = ImageIO.read(f);
			int height = 100;
			int width = 100;
			Image itemp = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			// 计算比例
			if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
				if (bi.getHeight() > bi.getWidth()) {
					ratio = (new Integer(height)).doubleValue() / bi.getHeight();
				} else {
					ratio = (new Integer(width)).doubleValue() / bi.getWidth();
				}
				AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(bi, null);
			}
			return grayFilter((BufferedImage) itemp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public final static void scaleByRate(String srcImageFile, String result, int scale, boolean flag) {
		try {
			BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
			int width = src.getWidth(); // 得到源图宽
			int height = src.getHeight(); // 得到源图长
			if (flag) {// 放大
				width = width * scale;
				height = height * scale;
			} else {// 缩小
				width = width / scale;
				height = height / scale;
			}
			// 对图片进行缩放
			Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			// Returns:a Graphics2D, which can be used to draw into this BufferedImage
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			ImageIO.write(tag, "JPEG", new File(result));// 输出到文件流
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public final static void scaleFixedSize(String srcImageFile, String result, int height, int width, boolean bb) {
		try {
			double ratio = 0.0; // 缩放比例
			File f = new File(srcImageFile);
			BufferedImage bi = ImageIO.read(f);
			Image itemp = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			// 计算比例
			if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
				if (bi.getHeight() > bi.getWidth()) {
					ratio = (new Integer(height)).doubleValue() / bi.getHeight();
				} else {
					ratio = (new Integer(width)).doubleValue() / bi.getWidth();
				}
				AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(bi, null);
			}
			if (bb) {// 补白
				BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = image.createGraphics();
				g.setColor(Color.white);
				g.fillRect(0, 0, width, height);
				if (width == itemp.getWidth(null)) g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
				else g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
				g.dispose();
				itemp = image;
			}
			ImageIO.write((BufferedImage) itemp, "JPEG", new File(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public final static void cutByRange(String srcImageFile, String result, int x, int y, int width, int height) {
		try {
			// 读取源图像
			BufferedImage bi = ImageIO.read(new File(srcImageFile));
			int srcWidth = bi.getHeight(); // 源图宽度
			int srcHeight = bi.getWidth(); // 源图高度
			if (srcWidth > 0 && srcHeight > 0) {
				Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
				// 四个参数分别为图像起点坐标和宽高
				// 即: CropImageFilter(int x,int y,int width,int height)
				ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
				Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
				BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(img, 0, 0, width, height, null); // 绘制切割后的图
				g.dispose();
				// 输出为文件
				ImageIO.write(tag, "JPEG", new File(result));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public final static void cutChip(String srcImageFile, String descDir, int rows, int cols) {
		try {
			if (rows <= 0 || rows > 20) rows = 2; // 切片行数
			if (cols <= 0 || cols > 20) cols = 2; // 切片列数
			// 读取源图像
			BufferedImage bi = ImageIO.read(new File(srcImageFile));
			int srcWidth = bi.getHeight(); // 源图宽度
			int srcHeight = bi.getWidth(); // 源图高度
			if (srcWidth > 0 && srcHeight > 0) {
				Image img;
				ImageFilter cropFilter;
				Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
				int destWidth = srcWidth; // 每张切片的宽度
				int destHeight = srcHeight; // 每张切片的高度
				// 计算切片的宽度和高度
				if (srcWidth % cols == 0) {
					destWidth = srcWidth / cols;
				} else {
					destWidth = (int) Math.floor(srcWidth / cols) + 1;
				}
				if (srcHeight % rows == 0) {
					destHeight = srcHeight / rows;
				} else {
					destHeight = (int) Math.floor(srcWidth / rows) + 1;
				}
				// 循环建立切片
				// 改进的想法:是否可用多线程加快切割速度
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						// 四个参数分别为图像起点坐标和宽高
						// 即: CropImageFilter(int x,int y,int width,int height)
						cropFilter = new CropImageFilter(j * destWidth, i * destHeight, destWidth, destHeight);
						img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
						BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
						Graphics g = tag.getGraphics();
						g.drawImage(img, 0, 0, null); // 绘制缩小后的图
						g.dispose();
						// 输出为文件
						ImageIO.write(tag, "JPEG", new File(descDir + "_r" + i + "_c" + j + ".jpg"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public final static void cutChipBySize(String srcImageFile, String descDir, int destWidth, int destHeight) {
		try {
			if (destWidth <= 0) destWidth = 200; // 切片宽度
			if (destHeight <= 0) destHeight = 150; // 切片高度
			// 读取源图像
			BufferedImage bi = ImageIO.read(new File(srcImageFile));
			int srcWidth = bi.getHeight(); // 源图宽度
			int srcHeight = bi.getWidth(); // 源图高度
			if (srcWidth > destWidth && srcHeight > destHeight) {
				Image img;
				ImageFilter cropFilter;
				Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
				int cols = 0; // 切片横向数量
				int rows = 0; // 切片纵向数量
				// 计算切片的横向和纵向数量
				if (srcWidth % destWidth == 0) {
					cols = srcWidth / destWidth;
				} else {
					cols = (int) Math.floor(srcWidth / destWidth) + 1;
				}
				if (srcHeight % destHeight == 0) {
					rows = srcHeight / destHeight;
				} else {
					rows = (int) Math.floor(srcHeight / destHeight) + 1;
				}
				// 循环建立切片
				// 改进的想法:是否可用多线程加快切割速度
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						// 四个参数分别为图像起点坐标和宽高
						// 即: CropImageFilter(int x,int y,int width,int height)
						cropFilter = new CropImageFilter(j * destWidth, i * destHeight, destWidth, destHeight);
						img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
						BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
						Graphics g = tag.getGraphics();
						g.drawImage(img, 0, 0, null); // 绘制缩小后的图
						g.dispose();
						// 输出为文件
						ImageIO.write(tag, "JPEG", new File(descDir + "_r" + i + "_c" + j + ".jpg"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public final static void convert(String srcImageFile, String formatName, String destImageFile) {
		try {
			File f = new File(srcImageFile);
			f.canRead();
			f.canWrite();
			BufferedImage src = ImageIO.read(f);
			ImageIO.write(src, formatName, new File(destImageFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public final static void gray(String srcImageFile, String destImageFile) {
		try {
			BufferedImage src = ImageIO.read(new File(srcImageFile));
			ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
			ColorConvertOp op = new ColorConvertOp(cs, null);
			src = op.filter(src, null);
			ImageIO.write(src, "JPEG", new File(destImageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public final static BufferedImage grayFilter(BufferedImage srcBufferedImage) {
		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
		ColorConvertOp op = new ColorConvertOp(cs, null);
		return op.filter(srcBufferedImage, null);
	}

	public final static void pressText(String pressText, String srcImageFile, String destImageFile, String fontName, int fontStyle, Color color, int fontSize, int x, int y, float alpha) {
		try {
			File img = new File(srcImageFile);
			Image src = ImageIO.read(img);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);
			g.setColor(color);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			// 在指定坐标绘制水印文字
			g.drawString(pressText, (width - (getLength(pressText) * fontSize)) / 2 + x, (height - fontSize) / 2 + y);
			g.dispose();
			ImageIO.write((BufferedImage) image, "JPEG", new File(destImageFile));// 输出到文件流
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public final static void pressText2(String pressText, String srcImageFile, String destImageFile, String fontName, int fontStyle, Color color, int fontSize, int x, int y, float alpha) {
		try {
			File img = new File(srcImageFile);
			Image src = ImageIO.read(img);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);
			g.setColor(color);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			// 在指定坐标绘制水印文字
			g.drawString(pressText, (width - (getLength(pressText) * fontSize)) / 2 + x, (height - fontSize) / 2 + y);
			g.dispose();
			ImageIO.write((BufferedImage) image, "JPEG", new File(destImageFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public final static void pressImage(String pressImg, String srcImageFile, String destImageFile, int x, int y, float alpha) {
		try {
			File img = new File(srcImageFile);
			Image src = ImageIO.read(img);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);
			// 水印文件
			Image src_biao = ImageIO.read(new File(pressImg));
			int wideth_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			g.drawImage(src_biao, (wideth - wideth_biao) / 2, (height - height_biao) / 2, wideth_biao, height_biao, null);
			// 水印文件结束
			g.dispose();
			ImageIO.write((BufferedImage) image, "JPEG", new File(destImageFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public final static int getLength(String text) {
		int length = 0;
		for (int i = 0; i < text.length(); i++) {
			if (new String(text.charAt(i) + "").getBytes().length > 1) {
				length += 2;
			} else {
				length += 1;
			}
		}
		return length / 2;
	}
}
