
package org.trimps.images.analyzer.core.render;

import java.io.InputStream;

import javax.media.jai.PlanarImage;

import org.trimps.images.analyzer.core.ImageRender;
import org.trimps.images.analyzer.core.ImageWrapper;
import org.trimps.images.analyzer.core.SimpleImageException;
import org.trimps.images.analyzer.core.util.ImageColorConvertHelper;
import org.trimps.images.analyzer.core.util.ImageReadHelper;


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
