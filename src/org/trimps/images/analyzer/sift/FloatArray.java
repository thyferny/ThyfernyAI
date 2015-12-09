
package org.trimps.images.analyzer.sift;


public abstract class FloatArray {
    public float[] data;                     //公开为public可以直接访问而不是在访问大量的数据元素时需要大量的getter方法调用
    public abstract FloatArray clone();
}

