
package in.thyferny.images.core.codec;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import in.thyferny.images.core.ImageWrapper;
import in.thyferny.images.core.codec.jpeg.CalculateConsts;


public abstract class AbstractImageDecoder implements ImageDecoder, CalculateConsts {

    protected Map<Integer, ExtendImageHeaderReader> extendImageHeaderReaders = new HashMap<Integer, ExtendImageHeaderReader>();

    
    public boolean addExtendHeaderReader(int marker, ExtendImageHeaderReader reader) {
        extendImageHeaderReaders.put(marker, reader);

        return true;
    }

    
    public abstract ImageWrapper decode() throws IOException;

    
    public Iterator<ExtendImageHeaderReader> getExtendHeaderReaders() {
        return extendImageHeaderReaders.values().iterator();
    }

    
    public boolean removeExtendHeaderReader(int marker) {
        return extendImageHeaderReaders.remove(marker) != null;
    }
}
