
package in.thyferny.images.sift.scale;

import in.thyferny.images.sift.ImagePixelArray;


public class FeaturePoint {

    public float          x;
    public float          y;

    public ImagePixelArray image;                   // 特征点所在的高斯图层
    public float          imgScale;                // 找到该点的高斯图层在8度空间尺度
    public float          scale;

    public float          orientation;

    public float[]        features;
    public boolean         hasFeatures = false;

    public int xDim; 
    public int yDim;
    public int oDim;
    public FeaturePoint(){
    }

    public FeaturePoint(ImagePixelArray image, float x, float y, float imgScale, float kfScale, float orientation){
        this.image = image;
        this.x = x;
        this.y = y;
        this.imgScale = imgScale;
        this.scale = kfScale;
        this.orientation = orientation;
    }
    
    public void createVector(int xDim, int yDim, int oDim) {
        this.hasFeatures = true;
        this.xDim = xDim;// 4 dim
        this.yDim = yDim;// 4 dim
        this.oDim = oDim;// 8 dim
        features = new float[yDim * xDim * oDim];
    }
}

