
package in.thyferny.images.core.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import in.thyferny.images.core.codec.jpeg.JPEGMarkerException;


public class ImageBitsInputStream implements ImageInputStream {

    private InputStream in;

    // bits buffer
    private int         b   = 0;
    private int         cnt = 0;

    public ImageBitsInputStream(InputStream input){
        in = input;
    }

    
    public void close() throws IOException {
        in.close();
    }

    
    public int read() throws IOException {
        cnt = 0;

        return in.read();
    }

    
    public int read(byte[] b, int off, int len) throws IOException {
        cnt = 0;

        return in.read(b, off, len);
    }

    
    public int read(byte[] b) throws IOException {
        cnt = 0;

        return in.read(b);
    }

    
    public int readBit() throws IOException, JPEGMarkerException {
        int bit = 0;

        if (cnt == 0) {
            b = in.read();
            cnt = 8;

            if (b == 0xFF) {
                // Loop here to discard any padding FF's on terminating marker
                do {
                    b = in.read();
                } while (b == 0xFF);

                if (b == 0) {
                    
                    b = 0xFF;
                    cnt = 8;
                } else {
                    throw new JPEGMarkerException(b);
                }
            } else if (b == -1) {
                throw new JPEGMarkerException(b);
            }
        }

        bit = b >> 7;
        cnt--;
        b = b << 1;

        return bit & 0x1;
    }

    
    public long readBits(int bit) throws IOException, JPEGMarkerException {
        if (bit < 0) {
            throw new IllegalArgumentException("bit must be greater than zero");
        }

        int i = 0, v = 0;
        while (i != bit) {
            i++;
            v = (v << 1) | readBit();
        }

        return v;
    }

    
    public int skipBytes(int n) throws IOException {
        cnt = 0;

        int total = 0;
        int cur = 0;

        while ((total < n) && ((cur = (int) in.skip(n - total)) > 0)) {
            total += cur;
        }

        return total;
    }

    
    public int readUnsignedShort() throws IOException {
        cnt = 0;

        int ch1 = in.read();
        int ch2 = in.read();
        if ((ch1 | ch2) < 0) throw new EOFException();

        return (ch1 << 8) + (ch2 << 0);
    }

    
    public short readShort() throws IOException {
        cnt = 0;

        int ch1 = in.read();
        int ch2 = in.read();
        if ((ch1 | ch2) < 0) throw new EOFException();

        return (short) ((ch1 << 8) + (ch2 << 0));
    }

    
    public byte readByte() throws IOException {
        cnt = 0;

        int ch = in.read();
        if (ch < 0) throw new EOFException();

        return (byte) (ch);
    }

    public void resetBuffer() throws IOException {
        cnt = 0;
    }
}
