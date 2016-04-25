
package in.thyferny.images.core.jai.scale;


public class LanczosResizeFilter {
    private double support;
    private double windowSupport;
    private double scale;
    private double blur;
    private double scaleDivideWS;

    public LanczosResizeFilter() {
        this.support = 3.0;
        this.scale = 1.0;
        this.blur = 1.0;
        this.windowSupport = this.support;
        this.scaleDivideWS = this.scale / this.windowSupport;
    }

    public double filter(double x) {
        if (x == 0.0) {
            return 1.0;
        }
        double tmp = Math.PI * x;

        return Math.sin(tmp) / tmp;
    }

    public double window(double x) {
        if (x == 0.0) {
            return 1.0;
        }
        double tmp = Math.PI * x;

        return Math.sin(tmp) / tmp;
    }
    
    public double getScaleDivedeWindowSupport() {
        return scaleDivideWS;
    }

    
    public double getSupport() {
        return support;
    }

    
    public double getWindowSupport() {
        return windowSupport;
    }

    
    public double getScale() {
        return scale;
    }

    
    public double getBlur() {
        return blur;
    }

    
    public void setSupport(double support) {
        this.support = support;
    }

    
    public void setWindowSupport(double windowSupport) {
        this.windowSupport = windowSupport;
    }

    
    public void setScale(double scale) {
        this.scale = scale;
    }

    
    public void setBlur(double blur) {
        this.blur = blur;
    }

}
