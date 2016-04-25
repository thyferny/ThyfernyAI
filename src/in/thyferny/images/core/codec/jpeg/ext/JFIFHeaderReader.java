
package in.thyferny.images.core.codec.jpeg.ext;

import java.io.IOException;

import in.thyferny.images.core.codec.ExtendImageHeaderReader;
import in.thyferny.images.core.io.ImageInputStream;


public class JFIFHeaderReader implements ExtendImageHeaderReader {

    
    public void readProperties(ImageInputStream in, int len, ExtendImageHeader imageHeader) throws IOException {
        int numToRead = 0;

        // get the interesting part of the marker data
        if (len >= 14) {
            numToRead = 14;
        } else if (len > 0) {
            numToRead = len;
        } else {
            numToRead = 0;
        }

        byte[] datas = new byte[numToRead];

        in.read(datas);

        len -= numToRead;

        if (numToRead >= 14 && datas[0] == 0x4A && datas[1] == 0x46 && datas[2] == 0x49 && datas[3] == 0x46
            && datas[4] == 0) {
            // Found JFIF APP0 marker: save info
            imageHeader.setSawJFIFMarker(true);
            imageHeader.setJFIFMajorVersion(datas[5]);
            imageHeader.setJFIFMinorVersion(datas[6]);
            imageHeader.setDensityUnit(datas[7]);
            imageHeader.setXDensity(datas[8] << 8);
            imageHeader.setYDensity(datas[10] << 8);

            // need check metadata info or not?
        } else if (numToRead >= 6 && datas[0] == 0x4A && datas[1] == 0x46 && datas[2] == 0x58 && datas[3] == 0x58
                   && datas[4] == 0) {
            // Found JFIF "JFXX" extension APP0 marker
            imageHeader.setSawJFXXMarker(true);
        }

        // skip any remaining data -- could be lots
        if (len > 0) {
            in.skipBytes(len);
        }
    }

}
