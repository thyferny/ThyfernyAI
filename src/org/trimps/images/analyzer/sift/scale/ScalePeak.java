
package org.trimps.images.analyzer.sift.scale;


public class ScalePeak {

    public int       x;
    public int       y;
    public int       level;
    public LocalInfo local;

    public static class LocalInfo {

        public float fineX;
        public float fineY;
        public float scaleAdjust;
        public float dValue;

        public LocalInfo(float scaleAdjust,float fineX, float fineY){
            this.fineX = fineX;
            this.fineY = fineY;
            this.scaleAdjust = scaleAdjust;
        }
    }

    public ScalePeak(int x, int y, int level){
        this.x = x;
        this.y = y;
        this.level = level;
    }
}

