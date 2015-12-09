
package org.trimps.images.analyzer.core.render;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import org.trimps.images.analyzer.core.ImageRender;
import org.trimps.images.analyzer.core.ImageWrapper;
import org.trimps.images.analyzer.core.SimpleImageException;
import org.trimps.images.analyzer.core.util.ImageDrawHelper;

public class DrawTextRender extends ImageRender {

    private DrawTextParameter drawTextParameter = null;
    private ImageWrapper      imgWrapper        = null;

    public DrawTextRender(ImageRender imageRender, DrawTextParameter param){
        super(imageRender);
        this.drawTextParameter = param;
    }

    public DrawTextRender(ImageWrapper imgWrapper, DrawTextParameter param){
        super(null);
        this.drawTextParameter = param;
        this.imgWrapper = imgWrapper;
    }

    public DrawTextRender(InputStream input, DrawTextParameter param){
        super(new ReadRender(input));
        this.drawTextParameter = param;
    }

    public DrawTextRender(InputStream input, boolean tosRGBColorSpace, DrawTextParameter param){
        super(new ReadRender(input, tosRGBColorSpace));
        this.drawTextParameter = param;
    }

    @Override
    public void dispose() throws SimpleImageException {
        super.dispose();

        drawTextParameter = null;
    }

    
    @Override
    public ImageWrapper render() throws SimpleImageException {
        if (drawTextParameter == null || drawTextParameter.getTextInfo() == null
            || drawTextParameter.getTextInfo().size() == 0) {
            if (imgWrapper == null) {
                return imageRender.render();
            }

            return imgWrapper;
        }

        if (imgWrapper == null) {
            imgWrapper = imageRender.render();
        }

        for (int i = 0; i < imgWrapper.getNumOfImages(); i++) {
            BufferedImage bi = imgWrapper.getAsBufferedImage(i);
            ImageDrawHelper.drawText(bi, this.drawTextParameter);
            imgWrapper.setImage(i, bi);
        }

        return imgWrapper;
    }

    
    public DrawTextParameter getDrawTextParameter() {
        return drawTextParameter;
    }

    
    public void setDrawTextParameter(DrawTextParameter drawTextParameter) {
        this.drawTextParameter = drawTextParameter;
    }
}
