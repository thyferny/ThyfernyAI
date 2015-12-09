
package org.trimps.images.analyzer.core.render;


public class ScaleParameter {
    
    private int       maxWidth  = 1024;
    
    private int       maxHeight = 1024;

    
    private Algorithm algorithm = Algorithm.AUTO;

    public ScaleParameter(){

    }

    public ScaleParameter(int maxWidth, int maxHeight){
        super();
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    
    public ScaleParameter(int maxWidth, int maxHeight, Algorithm algorithm){
        super();
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.algorithm = algorithm;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    
    public Algorithm getAlgorithm() {
        return algorithm;
    }

    
    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public static enum Algorithm {
        INTERP_BICUBIC_2, INTERP_BICUBIC, SUBSAMPLE_AVG, PROGRESSIVE, LANCZOS, AUTO
    }
}
