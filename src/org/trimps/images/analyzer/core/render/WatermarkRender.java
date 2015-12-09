
package org.trimps.images.analyzer.core.render;

import java.awt.image.BufferedImage;

import org.trimps.images.analyzer.core.ImageRender;
import org.trimps.images.analyzer.core.ImageWrapper;
import org.trimps.images.analyzer.core.SimpleImageException;
import org.trimps.images.analyzer.core.util.ImageDrawHelper;


public class WatermarkRender extends ImageRender {

    private WatermarkParameter param;
    private ImageWrapper       imageWrapper;

    public WatermarkRender(ImageRender imageRender, WatermarkParameter param){
        super(imageRender);
        this.param = param;
    }

    public WatermarkRender(ImageWrapper srcImage, WatermarkParameter param){
        super(null);
        this.imageWrapper = srcImage;
        this.param = param;
    }

    
    @Override
    public ImageWrapper render() throws SimpleImageException {
        if(imageWrapper == null) {
            imageWrapper = imageRender.render();
        }
        
        if(param == null) {
            return imageWrapper;
        }
        
        for (int i = 0; i < imageWrapper.getNumOfImages(); i++) {
            BufferedImage img = ImageDrawHelper.drawWatermark(imageWrapper.getAsBufferedImage(i), param);
            imageWrapper.setImage(i, img);
        }

        return imageWrapper;
    }

    
    @Override
    public void dispose() throws SimpleImageException {
        super.dispose();
        this.param = null;
    }

    
    public WatermarkParameter getParam() {
        return param;
    }

    
    public void setParam(WatermarkParameter param) {
        this.param = param;
    }
}
