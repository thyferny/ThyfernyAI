
package org.trimps.images.analyzer.core.render;

import org.trimps.images.analyzer.core.ImageWrapper;


public class WatermarkParameter {

    
    private ImageWrapper watermark;
    
    private float        alpha = 1.0f;
    
    private int          x     = 0;
    
    private int          y     = 0;

    
    public WatermarkParameter(ImageWrapper watermark, float alpha, int x, int y){
        super();
        setWatermark(watermark);
        setAlpha(alpha);
        setX(x);
        setY(y);
    }

    
    public WatermarkParameter(ImageWrapper watermark){
        super();
        setWatermark(watermark);
    }

    
    
    public ImageWrapper getWatermark() {
        return watermark;
    }

    
    
    public void setWatermark(ImageWrapper watermark) {
        if(watermark == null) {
            throw new IllegalArgumentException("Watermark must not be null");
        }
        this.watermark = watermark;
    }

    
    
    public float getAlpha() {
        return alpha;
    }

    
    
    public void setAlpha(float alpha) {
        if(alpha > 1.0f || alpha < 0.0f) {
            throw new IllegalArgumentException("Alpha must be in [0.0, 1.0]");
        }
        this.alpha = alpha;
    }

    
    
    public int getX() {
        return x;
    }

    
    
    public void setX(int x) {
        if(x < 0) {
            throw new IllegalArgumentException("x must be greater than 0");
        }
        this.x = x;
    }

    
    
    public int getY() {
        return y;
    }

    
    
    public void setY(int y) {
        if(y < 0) {
            throw new IllegalArgumentException("y must be greater than 0");
        }
        this.y = y;
    }
}
