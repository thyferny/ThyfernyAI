
package org.trimps.images.analyzer.core.codec;

import java.io.IOException;

import org.trimps.images.analyzer.core.codec.jpeg.ext.ExtendImageHeader;
import org.trimps.images.analyzer.core.io.ImageInputStream;


public interface ExtendImageHeaderReader {

    public void readProperties(ImageInputStream in, int length, ExtendImageHeader target) throws IOException;
}
