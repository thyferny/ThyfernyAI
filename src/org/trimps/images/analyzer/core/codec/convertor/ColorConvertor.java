
package org.trimps.images.analyzer.core.codec.convertor;

import org.trimps.images.analyzer.core.codec.jpeg.CalculateConsts;


public interface ColorConvertor extends CalculateConsts {

    public abstract byte[] convertBlock(int[] input, int inPos, byte[] output, int numOfComponents,
                                        int startCoordinate, int row, int scanlineStride);

    public long convert(int[] input, int inPos);
}
