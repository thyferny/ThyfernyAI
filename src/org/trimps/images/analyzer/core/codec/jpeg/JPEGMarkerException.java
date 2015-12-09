
package org.trimps.images.analyzer.core.codec.jpeg;


public class JPEGMarkerException extends Exception {

    private static final long serialVersionUID = 1338828846556134421L;

    private int               marker;

    public JPEGMarkerException(int marker){
        this.marker = marker;
    }

    public int getMarker() {
        return marker;
    }
}
