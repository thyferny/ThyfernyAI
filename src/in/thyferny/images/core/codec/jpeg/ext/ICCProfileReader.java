
package in.thyferny.images.core.codec.jpeg.ext;

import java.io.IOException;

import in.thyferny.images.core.codec.ExtendImageHeaderReader;
import in.thyferny.images.core.codec.jpeg.MarkerConstants;
import in.thyferny.images.core.io.ImageInputStream;


public class ICCProfileReader implements ExtendImageHeaderReader {

    private int      numOfRed     = 0;
    private int      numOfChunks  = 0;
    private int      totalLength  = 0;
    private boolean  errorOnParse = false;

    private byte[][] rawProfileData;
    private byte[]   profileData;

    
    public void readProperties(ImageInputStream in, int len, ExtendImageHeader imageHeader) throws IOException {
        try {
            if (len < MarkerConstants.ICC_PROFILE_LABEL.length) {
                // this APP2 marker is not used by ICC profile just ignore
                return;
            }

            if (errorOnParse) {
                return;
            }

            byte[] label = new byte[MarkerConstants.ICC_PROFILE_LABEL.length];
            in.read(label);
            len -= label.length;

            if (!isICCProfileLabel(label)) {
                // just ignore this marker
                return;
            }

            int seq = in.read();
            int nums = in.read();
            len -= 2;

            // verify seq
            if (numOfChunks == 0) {
                numOfChunks = nums;
            } else {
                if (nums != numOfChunks) {
                    errorOnParse = true;

                    return;
                }
            }

            if (seq > numOfChunks) {
                errorOnParse = true;

                return;
            }
            // end verify

            byte[] data = new byte[len];
            in.read(data);
            len = 0;

            if (rawProfileData == null) {
                rawProfileData = new byte[numOfChunks + 1][];
            }

            rawProfileData[seq] = data;
            totalLength += data.length;
            numOfRed++;

            if (numOfRed == numOfChunks) {
                imageHeader.setExistProfile(true);
                imageHeader.setProfileData(getProfileData());
            }
        } finally {
            if (len > 0) {
                in.skipBytes(len);
            }
        }

    }

    protected boolean isICCProfileLabel(byte[] label) throws IOException {
        if (label.length != MarkerConstants.ICC_PROFILE_LABEL.length) {
            return false;
        }

        for (int i = 0; i < label.length; i++) {
            if (label[i] != MarkerConstants.ICC_PROFILE_LABEL[i]) {
                // just ignore, this marker data is not ICC Profile data
                return false;
            }
        }

        return true;
    }

    public byte[] getProfileData() {
        if (errorOnParse) {
            return null;
        }

        if (profileData == null) {
            profileData = new byte[totalLength];
            int destPos = 0;

            for (int i = 0; i <= numOfChunks; i++) {
                if (rawProfileData[i] != null) {
                    System.arraycopy(rawProfileData[i], 0, profileData, destPos, rawProfileData[i].length);
                    destPos += rawProfileData[i].length;
                }
            }
        }

        return profileData;
    }
}
