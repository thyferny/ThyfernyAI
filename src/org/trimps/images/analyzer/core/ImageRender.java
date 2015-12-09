
package org.trimps.images.analyzer.core;


public abstract class ImageRender {

    protected ImageRender imageRender;

    
    public ImageRender(ImageRender imageRender){
        super();
        this.imageRender = imageRender;
    }

    public abstract ImageWrapper render() throws SimpleImageException;

    public void dispose() throws SimpleImageException {
        if (imageRender != null) {
            imageRender.dispose();
        }
    }
}
