
package org.trimps.images.analyzer.core.codec.convertor;


public abstract class MapColorConvertor implements ColorConvertor {

    public static final int[] Cr2R = new int[256];
    public static final int[] Cb2B = new int[256];
    public static final int[] Cr2G = new int[256];
    public static final int[] Cb2G = new int[256];

    static {
        int nScale = 1 << 16;
        int nHalf = nScale >> 1;

        for (int i = 0, x = -128; i <= 255; i++, x++) {
            
            
            
            Cr2R[i] = (((int) (1.40200 * nScale + 0.5)) * x + nHalf) >> 16;
            
            Cb2B[i] = (((int) (1.77200 * nScale + 0.5)) * x + nHalf) >> 16;
            
            Cr2G[i] = ((-(int) (0.71414 * nScale + 0.5) * x));
            
            
            Cb2G[i] = ((-((int) (0.34414 * nScale + 0.5)) * x) + nHalf);
        }
    } // end static statment

    
    public abstract long convert(int[] input, int inPos);

    
    public abstract byte[] convertBlock(int[] input, int inPos, byte[] output, int numOfComponents,
                                        int startCoordinate, int row, int scanlineStride);
}
