
package org.trimps.images.analyzer.harris.io;

import java.util.List;

import org.trimps.images.analyzer.harissurf.SURFInterestPointN;


public class InterestPointNListInfo {

    private String                  imageFile;
    private List<SURFInterestPointN> list;
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

    public List<SURFInterestPointN> getList() {
        return list;
    }

    public void setList(List<SURFInterestPointN> list) {
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
