
package in.thyferny.images.core.io;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.sun.media.jai.codec.ByteArraySeekableStream;

public class ByteArraySeekableStreamWrap extends ByteArraySeekableStream {

    public static ByteArraySeekableStreamWrap wrapInputStream(InputStream is) throws IOException {
        byte[] data = IOUtils.toByteArray(is);
        ByteArraySeekableStreamWrap stream = new ByteArraySeekableStreamWrap(data);

        return stream;
    }

    public ByteArraySeekableStreamWrap(byte[] src, int offset, int length) throws IOException{
        super(src, offset, length);
    }

    public ByteArraySeekableStreamWrap(byte[] src) throws IOException{
        super(src);
    }

}
