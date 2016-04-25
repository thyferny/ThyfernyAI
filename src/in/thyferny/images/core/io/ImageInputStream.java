
package in.thyferny.images.core.io;

import java.io.IOException;

import in.thyferny.images.core.codec.jpeg.JPEGMarkerException;


public interface ImageInputStream {

    int read() throws IOException;

    int readUnsignedShort() throws IOException;

    int read(byte[] b, int off, int len) throws IOException;

    int read(byte[] b) throws IOException;

    int readBit() throws IOException, JPEGMarkerException;

    short readShort() throws IOException;

    byte readByte() throws IOException;

    long readBits(int bit) throws IOException, JPEGMarkerException;

    int skipBytes(int n) throws IOException;

    void close() throws IOException;

    void resetBuffer() throws IOException;
}
