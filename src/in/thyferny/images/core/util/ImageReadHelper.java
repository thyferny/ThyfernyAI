
package in.thyferny.images.core.util;

import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;

import org.w3c.dom.Node;

import in.thyferny.images.core.ImageFormat;
import in.thyferny.images.core.ImageWrapper;
import in.thyferny.images.core.SimpleImageException;
import in.thyferny.images.core.codec.jpeg.JPEGDecoder;
import in.thyferny.images.core.io.ByteArraySeekableStreamWrap;
import in.thyferny.images.core.io.ImageBitsInputStream;
import in.thyferny.images.core.io.ImageInputStream;


public class ImageReadHelper {
    
    public static final int   DEFAULT_HIGHT_QUALITY = 93;

    static {
        JAIRegisterHelper.register();
    }

    protected static ImageLog log                   = ImageLog.getLog(ImageReadHelper.class);

    public static ImageWrapper read(InputStream input)
            throws SimpleImageException {
        try {
            input = ImageUtils.createMemoryStream(input);

            if (ImageUtils.isJPEG(input)) {
                return readJPEG(input);
            }

            if (ImageUtils.isGIF(input)) {
                return readGIF(input);
            }

            return readGeneral(input);
        } catch (Exception e) {
            throw new SimpleImageException(e);
        }
    }

    public static ImageWrapper readJPEG(InputStream input)
            throws SimpleImageException {
        ImageWrapper img = null;
        ImageInputStream imageStream = null;

        try {
            imageStream = new ImageBitsInputStream(input);
            JPEGDecoder decoder = new JPEGDecoder(imageStream);

            img = decoder.decode();
        } catch (Exception e) {
            throw new SimpleImageException(e);
        }

        return img;
    }

    public static ImageWrapper readGIF(InputStream input)
            throws SimpleImageException {
        javax.imageio.stream.ImageInputStream imageIn = null;
        ImageReader reader = null;
        
        try {
            imageIn = ImageIO.createImageInputStream(input);
            Iterator<ImageReader> readers = ImageIO.getImageReaders(imageIn);

            if (readers.hasNext()) {
                reader = readers.next();
            } else {
                throw new IllegalStateException("No GIF reader matched");
            }

            reader.setInput(imageIn);

            int numOfImages = reader.getNumImages(true);
            if(numOfImages <= 0) {
                throw new SimpleImageException("a GIF without pictures inside, maybe it's a attack");
            }
            BufferedImage[] images = new BufferedImage[numOfImages];
            Node[] metadatas = new Node[numOfImages];
            IIOMetadata streamMetadata = reader.getStreamMetadata();
            
            for (int i = 0; i < numOfImages; i++) {
                images[i] = reader.read(i);
                metadatas[i] = reader.getImageMetadata(i).getAsTree(ImageWrapper.GIF_IMAGE_METADATA_NAME);
            }
            ImageWrapper img = new ImageWrapper(images);
            img.setImageFormat(ImageFormat.GIF);
            img.setStreamMetadata(streamMetadata.getAsTree(ImageWrapper.GIF_STREAM_METADATA_NAME));
            img.setMetadatas(metadatas);

            return img;
        } catch (Exception e) {
            throw new SimpleImageException(e);
        } finally {
            if(reader != null){
                reader.dispose();
            }
        }
    }

    public static ImageWrapper readGeneral(InputStream input)
            throws SimpleImageException {
        try {
            ImageWrapper img = null;
            ImageFormat format = ImageUtils.identifyFormat(input);
            if(format == null) {
                throw new IllegalArgumentException("Unsupported image format, only JPEG, GIF, PNG, BMP and TIFF are supported");
            }
            ByteArraySeekableStreamWrap wrap = null;
            wrap = ByteArraySeekableStreamWrap.wrapInputStream(input);

            
            ParameterBlock pb = new ParameterBlock();
            pb.add(wrap);
            PlanarImage src = JAI.create("Stream", pb);
            img = new ImageWrapper(src, DEFAULT_HIGHT_QUALITY);
            img.setImageFormat(format);

            return img;
        } catch (Exception e) {
            throw new SimpleImageException(e);
        }
    }
}
