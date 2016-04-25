
package in.thyferny.images.core.render;

import java.io.InputStream;

import javax.media.jai.PlanarImage;

import in.thyferny.images.core.ImageRender;
import in.thyferny.images.core.ImageWrapper;
import in.thyferny.images.core.SimpleImageException;
import in.thyferny.images.core.util.ImageColorConvertHelper;
import in.thyferny.images.core.util.ImageReadHelper;


public class ReadRender extends ImageRender {

    protected InputStream inStream;
    protected boolean     tosRGBColorSpace = true;
    protected boolean     needClean        = false;

    public ReadRender(InputStream input, boolean tosRGBColorSpace) {
        super(null);
        this.inStream = input;
        this.tosRGBColorSpace = tosRGBColorSpace;
    }

    public ReadRender(InputStream input) {
        this(input, true);
    }

    
    @Override
    public ImageWrapper render() throws SimpleImageException {
        try {
            ImageWrapper imgWrapper;
            if (inStream == null) {
                throw new SimpleImageException("No input set");
            }

            imgWrapper = ImageReadHelper.read(inStream);

            if (tosRGBColorSpace) {
                for (int i = 0; i < imgWrapper.getNumOfImages(); i++) {
                    PlanarImage img = ImageColorConvertHelper.convert2sRGB(imgWrapper
                            .getAsPlanarImage(i));
                    imgWrapper.setImage(i, img);
                }
            }

            return imgWrapper;
        } catch (Exception e) {
            throw new SimpleImageException(e);
        }
    }

    
    @Override
    public void dispose() throws SimpleImageException {
        super.dispose();
    }
}
