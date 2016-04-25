
package in.thyferny.images.core.render;

import java.io.InputStream;

import javax.media.jai.PlanarImage;

import in.thyferny.images.core.ImageFormat;
import in.thyferny.images.core.ImageRender;
import in.thyferny.images.core.ImageWrapper;
import in.thyferny.images.core.SimpleImageException;
import in.thyferny.images.core.util.ImageScaleHelper;

public class ScaleRender extends ImageRender {

    private ScaleParameter zoom       = null;
    private ImageWrapper   imgWrapper = null;

    public ScaleRender(ImageRender imageRender, ScaleParameter p){
        super(imageRender);
        this.zoom = p;
    }

    public ScaleRender(ImageWrapper imgWrapper, ScaleParameter p){
        super(null);
        this.zoom = p;
        this.imgWrapper = imgWrapper;
    }

    public ScaleRender(InputStream input, ScaleParameter param){
        super(new ReadRender(input));
        this.zoom = param;
    }

    public ScaleRender(InputStream input, boolean tosRGBColorSpace, ScaleParameter param){
        super(new ReadRender(input, tosRGBColorSpace));
        this.zoom = param;
    }

    
    @Override
    public void dispose() throws SimpleImageException {
        super.dispose();

        this.zoom = null;
    }

    
    @Override
    public ImageWrapper render() throws SimpleImageException {
        if(imgWrapper == null) {
            imgWrapper = imageRender.render();
        }
        
        if (zoom == null) {
            return imgWrapper;
        }
        
        if(imgWrapper.getImageFormat() == ImageFormat.GIF) {
            imgWrapper = ImageScaleHelper.scaleGIF(imgWrapper, zoom);
        } else {
            PlanarImage img = ImageScaleHelper.scale(imgWrapper.getAsPlanarImage(), zoom);
            imgWrapper.setImage(img);
        }

        return imgWrapper;
    }

    
    public ScaleParameter getZoom() {
        return zoom;
    }

    
    public void setZoom(ScaleParameter zoom) {
        this.zoom = zoom;
    }
}
