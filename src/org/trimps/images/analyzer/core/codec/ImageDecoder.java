
package org.trimps.images.analyzer.core.codec;

import java.io.IOException;
import java.util.Iterator;

import org.trimps.images.analyzer.core.ImageWrapper;


public interface ImageDecoder {

    public ImageWrapper decode() throws IOException;

    public boolean addExtendHeaderReader(int marker, ExtendImageHeaderReader reader);

    public boolean removeExtendHeaderReader(int marker);

    public Iterator<ExtendImageHeaderReader> getExtendHeaderReaders();
}
