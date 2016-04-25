
package in.thyferny.images.core.codec.jpeg.ext;

import java.io.IOException;

import in.thyferny.images.core.codec.ExtendImageHeaderReader;
import in.thyferny.images.core.io.ImageInputStream;


public class AdobeHeaderReader implements ExtendImageHeaderReader {

    
    public void readProperties(ImageInputStream in, int len, ExtendImageHeader imageHeader) throws IOException {
        int transform = 0, numToRead = 0;

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

        if (numToRead >= 12 && datas[0] == 0x41 && datas[1] == 0x64 && datas[2] == 0x6F && datas[3] == 0x62
            && datas[4] == 0x65) {
            // Found Adobe APP14 marker
            transform = datas[11];

            imageHeader.setSawAdobeMarker(true);
            imageHeader.setAdobeTransform(transform);
        }

        // skip any remaining data
        if (len > 0) {
            in.skipBytes(len);
        }
    }

}
