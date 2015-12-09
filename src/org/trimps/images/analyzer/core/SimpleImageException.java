
package org.trimps.images.analyzer.core;

public class SimpleImageException extends Exception {

    private static final long serialVersionUID = 745614279229259820L;

    public SimpleImageException(){
        super();
    }

    public SimpleImageException(String message){
        super(message);
    }

    public SimpleImageException(String message, Throwable cause){
        super(message, cause);
    }

    public SimpleImageException(Throwable cause){
        super(cause);
    }
}
