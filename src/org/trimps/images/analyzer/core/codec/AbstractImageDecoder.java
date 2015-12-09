
package org.trimps.images.analyzer.core.codec;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.trimps.images.analyzer.core.ImageWrapper;
import org.trimps.images.analyzer.core.codec.jpeg.CalculateConsts;


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
