
package org.trimps.images.analyzer.core.render;

import java.io.InputStream;

import javax.media.jai.PlanarImage;

import org.trimps.images.analyzer.core.ImageRender;
import org.trimps.images.analyzer.core.ImageWrapper;
import org.trimps.images.analyzer.core.SimpleImageException;
import org.trimps.images.analyzer.core.util.ImageCropHelper;

public class CropRender extends ImageRender {

    public CropRender(ImageRender imageRender){
        super(imageRender);
    }

    private CropParameter cropParameter = null;
    private ImageWrapper  imgWrapper    = null;

    public CropRender(ImageRender imageRender, CropParameter param) {
        super(imageRender);
        this.cropParameter = param;
    }

    public CropRender(ImageWrapper imgWrapper, CropParameter param) {
        super(null);
        this.imgWrapper = imgWrapper;
        this.cropParameter = param;
    }

    public CropRender(InputStream input, CropParameter param){
        super(new ReadRender(input));
        this.cropParameter = param;
    }

    @Override
    public void dispose() throws SimpleImageException {
        super.dispose();
        cropParameter = null;
    }

    @Override
    public ImageWrapper render() throws SimpleImageException {
        if (cropParameter == null) {
            if(imgWrapper == null) {
                return imageRender.render();
            }
            
            return imgWrapper;
        }
        
        if(imgWrapper == null) {
            imgWrapper = imageRender.render();
        }
        
        for (int i = 0; i < imgWrapper.getNumOfImages(); i++) {
            PlanarImage pi = ImageCropHelper.crop(imgWrapper.getAsPlanarImage(i), this.cropParameter);
            imgWrapper.setImage(i, pi);
        }
        
        return imgWrapper;
    }

    public CropParameter getCropParameter() {
        return cropParameter;
    }

    public void setCropParameter(CropParameter cropParameter) {
        this.cropParameter = cropParameter;
    }

}
