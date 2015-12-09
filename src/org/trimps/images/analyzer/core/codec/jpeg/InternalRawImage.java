
package org.trimps.images.analyzer.core.codec.jpeg;



public class InternalRawImage {

    private int            width;
    private int            height;
    private int            numOfComponents;
    private JPEGColorSpace colorspace;
    private JPEGColorSpace rawColorspace;
    private byte[]         data;

    public void initData() {
        data = new byte[width * height * numOfComponents];
    }

    
    public int getNumOfComponents() {
        return numOfComponents;
    }

    
    public void setNumOfComponents(int numOfComponents) {
        this.numOfComponents = numOfComponents;
    }

    
    public int getWidth() {
        return width;
    }

    
    public void setWidth(int width) {
        this.width = width;
    }

    
    public int getHeight() {
        return height;
    }

    
    public void setHeight(int height) {
        this.height = height;
    }

    
    public JPEGColorSpace getColorspace() {
        return colorspace;
    }

    
    public void setColorspace(JPEGColorSpace colorspace) {
        this.colorspace = colorspace;
    }

    
    public JPEGColorSpace getRawColorspace() {
        return rawColorspace;
    }

    
    public void setRawColorspace(JPEGColorSpace rawColorspace) {
        this.rawColorspace = rawColorspace;
    }

    
    public byte[] getData() {
        return data;
    }

    
    public void setData(byte[] data) {
        this.data = data;
    }
}
