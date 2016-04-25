
package in.thyferny.images.core.codec;

import java.io.IOException;

import in.thyferny.images.core.codec.jpeg.ext.ExtendImageHeader;
import in.thyferny.images.core.io.ImageInputStream;


public interface ExtendImageHeaderReader {

    public void readProperties(ImageInputStream in, int length, ExtendImageHeader target) throws IOException;
}
