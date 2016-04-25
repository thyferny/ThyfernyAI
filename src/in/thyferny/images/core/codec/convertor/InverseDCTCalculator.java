
package in.thyferny.images.core.codec.convertor;

import in.thyferny.images.core.codec.jpeg.CalculateConsts;


public abstract class InverseDCTCalculator implements CalculateConsts {

    
    public abstract Object calculate(int[] input, int inPos, int[] quant, int[] output, int outPos, int width,
                                     int height);
}
