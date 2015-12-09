
package org.trimps.images.analyzer.harris.io;

import java.util.List;

import org.trimps.images.analyzer.harissurf.SURFInterestPoint;


public class InterestPointListInfo {

    private String                  imageFile;
    private List<SURFInterestPoint> list;
    private int                     width;
    private int                     height;
    private int                     maxSize = 10;

    

    public int getMaxSize() {
        return maxSize;
    }


    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public List<SURFInterestPoint> getList() {
        return list;
    }

    public void setList(List<SURFInterestPoint> list) {
        this.list = list;
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

}
