
package in.thyferny.images.core.codec.jpeg;


public class JPEGDecoderException extends RuntimeException {

    private static final long serialVersionUID = -7088965076154241039L;

    public JPEGDecoderException(){
        super();
    }

    
    public JPEGDecoderException(String message, Throwable cause){
        super(message, cause);
    }

    
    public JPEGDecoderException(String message){
        super(message);
    }

    
    public JPEGDecoderException(Throwable cause){
        super(cause);
    }
}
