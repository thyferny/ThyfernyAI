
package in.thyferny.images.core.codec.convertor;


public class InverseColorConvertor implements ColorConvertor {

    
    public long convert(int[] input, int inPos) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    
    public byte[] convertBlock(int[] input, int inPos, byte[] output, int numOfComponents, int startCoordinate,
                               int row, int scanlineStride) {
        int index = 0, inputOffset = 0, bounds = 0;
        int len = output.length;

        for (int i = 0; i < DCTSIZE; i++) {
            index = startCoordinate + i * scanlineStride;
            bounds = row * scanlineStride;

            for (int j = 0; j < DCTSIZE; j++) {
                if (index >= len) {
                    return output;
                }

                if (index < bounds) {
                    for (int c = 0; c < numOfComponents; c++) {
                        output[index++] = (byte) (255 - (input[inputOffset++] & 0xFF));
                    }
                } else {
                    inputOffset += numOfComponents;
                }
            }

            row++;
        }

        return output;
    }

}
