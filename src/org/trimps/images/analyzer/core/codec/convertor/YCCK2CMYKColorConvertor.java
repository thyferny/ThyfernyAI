
package org.trimps.images.analyzer.core.codec.convertor;


public class YCCK2CMYKColorConvertor extends MapColorConvertor {

    
    @Override
    public long convert(int[] input, int inPos) {
        int tempC, tempM, tempY, tempK, Y, Cb, Cr, K;

        Y = input[inPos++] & 0xFF;
        Cb = input[inPos++] & 0xFF;
        Cr = input[inPos++] & 0xFF;
        K = input[inPos] & 0xFF;

        tempC = 255 - sampleRangeLimitTable[sampleRangeLimitOffset + MAXJSAMPLE - (Y + Cr2R[Cr])];
        tempM = 255 - sampleRangeLimitTable[sampleRangeLimitOffset + MAXJSAMPLE - (Y + ((Cb2G[Cb] + Cr2G[Cr]) >> 16))];
        tempY = 255 - sampleRangeLimitTable[sampleRangeLimitOffset + MAXJSAMPLE - (Y + Cb2B[Cb])];
        tempK = 255 - K;

        return (tempC << 24) | ((tempM << 16) | (tempY << 8) | (tempK & 0xFFL));
    }

    
    @Override
    public byte[] convertBlock(int[] input, int inPos, byte[] output, int numOfComponents, int startCoordinate,
                               int row, int scanlineStride) {
        int index = 0, inputOffset = inPos, bounds = 0;
        int Y, Cb, Cr, K;
        int len = output.length;

        for (int i = 0; i < DCTSIZE; i++) {
            index = startCoordinate + i * scanlineStride;
            bounds = row * scanlineStride;

            for (int j = 0; j < DCTSIZE; j++) {
                Y = input[inputOffset++] & 0xFF;
                Cb = input[inputOffset++] & 0xFF;
                Cr = input[inputOffset++] & 0xFF;
                K = input[inputOffset++] & 0xFF;

                if (index >= len) {
                    return output;
                }

                if (index < bounds) {
                    output[index++] = (byte) (255 - sampleRangeLimitTable[sampleRangeLimitOffset + MAXJSAMPLE
                                                                          - (Y + Cr2R[Cr])]);
                    output[index++] = (byte) (255 - sampleRangeLimitTable[sampleRangeLimitOffset + MAXJSAMPLE
                                                                          - (Y + ((Cb2G[Cb] + Cr2G[Cr]) >> 16))]);
                    output[index++] = (byte) (255 - sampleRangeLimitTable[sampleRangeLimitOffset + MAXJSAMPLE
                                                                          - (Y + Cb2B[Cb])]);
                    output[index++] = (byte) (255 - K);
                }
            }

            row++;
        }

        return output;
    }
}
