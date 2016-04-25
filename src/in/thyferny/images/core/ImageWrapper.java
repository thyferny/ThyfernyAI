
package in.thyferny.images.core;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRenderedImage;

import javax.media.jai.PlanarImage;
import javax.media.jai.RenderedImageAdapter;
import javax.media.jai.WritableRenderedImageAdapter;

import org.w3c.dom.Node;

import in.thyferny.images.core.util.NodeUtils;


public class ImageWrapper extends MetadataRenderedImage {

    public static final int   DEFAULT_QUALITY = 93;

    protected RenderedImage[] images;

    // only support jpeg broken indicator
    protected boolean         broken;

    public ImageWrapper(BufferedImage bi){
        this(bi, DEFAULT_QUALITY, false);
    }

    public ImageWrapper(PlanarImage img){
        this(img, DEFAULT_QUALITY, false);
    }

    public ImageWrapper(BufferedImage bi, boolean isBroken){
        this(bi, DEFAULT_QUALITY, isBroken);
    }

    public ImageWrapper(PlanarImage img, boolean isBroken){
        this(img, DEFAULT_QUALITY, isBroken);
    }

    public ImageWrapper(BufferedImage bi, int quality){
        this(bi, quality, false);
    }

    public ImageWrapper(BufferedImage bi, int quality, boolean isBroken){
        this.quality = quality;
        this.images = new RenderedImage[1];
        this.images[0] = PlanarImage.wrapRenderedImage(bi);
        this.broken = isBroken;
    }

    public ImageWrapper(PlanarImage image, int quality){
        this(image, quality, false);
    }

    public ImageWrapper(PlanarImage image, int quality, boolean isBroken){
        this.images = new RenderedImage[1];
        this.images[0] = image;
        this.quality = quality;
        this.broken = isBroken;
    }

    public ImageWrapper(BufferedImage[] imgs){
        setImages(imgs);
        this.quality = DEFAULT_QUALITY;
    }

    public ImageWrapper(PlanarImage[] imgs){
        setImages(imgs);
        this.quality = DEFAULT_QUALITY;
    }

    public BufferedImage getAsBufferedImage(int index) {
        if (images[index] instanceof BufferedImage) {
            return (BufferedImage) images[index];
        } else if (images[index] instanceof PlanarImage) {
            return ((PlanarImage) images[index]).getAsBufferedImage();
        } else if (images[index] instanceof WritableRenderedImage) {
            return new WritableRenderedImageAdapter((WritableRenderedImage) images[index]).getAsBufferedImage();
        } else {
            return new RenderedImageAdapter(images[index]).getAsBufferedImage();
        }
    }

    public BufferedImage getAsBufferedImage() {
        return getAsBufferedImage(0);
    }

    public PlanarImage getAsPlanarImage(int index) {
        if (images[index] instanceof PlanarImage) {
            return (PlanarImage) images[index];
        } else if (images[index] instanceof BufferedImage) {
            return PlanarImage.wrapRenderedImage(images[index]);
        } else {
            return new RenderedImageAdapter(images[index]);
        }
    }

    public PlanarImage getAsPlanarImage() {
        return getAsPlanarImage(0);
    }

    public BufferedImage[] getAsBufferedImages() {
        BufferedImage[] imgs = new BufferedImage[images.length];

        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = getAsBufferedImage(i);
        }

        return imgs;
    }

    public PlanarImage[] getAsPlanarImages() {
        PlanarImage[] imgs = new PlanarImage[images.length];

        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = getAsPlanarImage(i);
        }

        return imgs;
    }

    public void setImages(BufferedImage[] imgs) {
        images = new RenderedImage[imgs.length];
        for (int i = 0; i < imgs.length; i++) {
            images[i] = imgs[i];
        }
    }

    public void setImages(PlanarImage[] imgs) {
        images = new RenderedImage[imgs.length];
        for (int i = 0; i < imgs.length; i++) {
            images[i] = imgs[i];
        }
    }

    public void setImage(int index, BufferedImage bi) {
        this.images[index] = bi;
    }

    public void setImage(BufferedImage bi) {
        setImage(0, bi);
    }

    public void setImage(int index, PlanarImage img) {
        this.images[index] = img;
    }

    public void setImage(PlanarImage img) {
        setImage(0, img);
    }

    public int getNumOfImages() {
        return images.length;
    }

    
    public int getWidth() {
        if (format == ImageFormat.GIF && streamMetadata != null) {
            Node screenDescNode = NodeUtils.getChild(streamMetadata, "LogicalScreenDescriptor");
            if (screenDescNode != null) {
                return NodeUtils.getIntAttr(screenDescNode, "logicalScreenWidth");
            }
        }

        return getWidth(0);
    }

    
    public int getHeight() {
        if (format == ImageFormat.GIF && streamMetadata != null) {
            Node screenDescNode = NodeUtils.getChild(streamMetadata, "LogicalScreenDescriptor");
            if (screenDescNode != null) {
                return NodeUtils.getIntAttr(screenDescNode, "logicalScreenHeight");
            }
        }

        return getHeight(0);
    }

    public int getWidth(int index) {
        if (index < 0 || index >= images.length) {
            throw new IndexOutOfBoundsException("Just totally have " + images.length + " images");
        }

        return images[index].getWidth();
    }

    public int getHeight(int index) {
        if (index < 0 || index >= images.length) {
            throw new IndexOutOfBoundsException("Just totally have " + images.length + " images");
        }

        return images[index].getHeight();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        int numOfImages = images.length;
        BufferedImage[] imgs = new BufferedImage[numOfImages];
        for (int i = 0; i < numOfImages; i++) {
            PlanarImage oldImg = getAsPlanarImage(i);
            imgs[i] = oldImg.getAsBufferedImage();
        }

        ImageWrapper newImgWrapper = new ImageWrapper(imgs);
        newImgWrapper.quality = quality;
        newImgWrapper.broken = broken;
        newImgWrapper.format = format;
        if (horizontalSamplingFactors != null) {
            newImgWrapper.horizontalSamplingFactors = horizontalSamplingFactors.clone();
        }
        if (verticalSamplingFactors != null) {
            newImgWrapper.verticalSamplingFactors = verticalSamplingFactors.clone();
        }
        if (streamMetadata != null) {
            newImgWrapper.streamMetadata = NodeUtils.cloneNode(streamMetadata);
        }
        if (metadatas != null) {
            newImgWrapper.metadatas = new Node[metadatas.length];
            for (int i = 0; i < metadatas.length; i++) {
                newImgWrapper.metadatas[i] = NodeUtils.cloneNode(metadatas[i]);
            }
        }

        return newImgWrapper;
    }

    
    public boolean isBroken() {
        return broken;
    }

    
    public void setBroken(boolean broken) {
        this.broken = broken;
    }
}
