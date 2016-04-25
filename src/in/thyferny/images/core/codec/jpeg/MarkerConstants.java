
package in.thyferny.images.core.codec.jpeg;

public final class MarkerConstants implements CalculateConsts {

    
    public static final int TEM                       = 0x01;

    // Codes 0x02 - 0xBF are reserved

    // SOF markers for Nondifferential Huffman coding
    
    public static final int SOF0                      = 0xC0;
    
    public static final int SOF1                      = 0xC1;
    
    public static final int SOF2                      = 0xC2;
    
    public static final int SOF3                      = 0xC3;

    
    public static final int DHT                       = 0xC4;

    // SOF markers for Differential Huffman coding
    
    public static final int SOF5                      = 0xC5;
    
    public static final int SOF6                      = 0xC6;
    
    public static final int SOF7                      = 0xC7;

    
    public static final int JPG                       = 0xC8;

    // SOF markers for Nondifferential arithmetic coding
    
    public static final int SOF9                      = 0xC9;
    
    public static final int SOF10                     = 0xCA;
    
    public static final int SOF11                     = 0xCB;

    
    public static final int DAC                       = 0xCC;

    // SOF markers for Differential arithmetic coding
    
    public static final int SOF13                     = 0xCD;
    
    public static final int SOF14                     = 0xCE;
    
    public static final int SOF15                     = 0xCF;

    // Restart Markers
    public static final int RST0                      = 0xD0;
    public static final int RST1                      = 0xD1;
    public static final int RST2                      = 0xD2;
    public static final int RST3                      = 0xD3;
    public static final int RST4                      = 0xD4;
    public static final int RST5                      = 0xD5;
    public static final int RST6                      = 0xD6;
    public static final int RST7                      = 0xD7;
    
    public static final int RESTART_RANGE             = 8;

    
    public static final int SOI                       = 0xD8;
    
    public static final int EOI                       = 0xD9;
    
    public static final int SOS                       = 0xDA;

    
    public static final int DQT                       = 0xDB;

    
    public static final int DNL                       = 0xDC;

    
    public static final int DRI                       = 0xDD;

    
    public static final int DHP                       = 0xDE;

    
    public static final int EXP                       = 0xDF;

    // Application markers
    
    public static final int APP0                      = 0xE0;
    public static final int APP1                      = 0xE1;
    
    public static final int APP2                      = 0xE2;
    public static final int APP3                      = 0xE3;
    public static final int APP4                      = 0xE4;
    public static final int APP5                      = 0xE5;
    public static final int APP6                      = 0xE6;
    public static final int APP7                      = 0xE7;
    public static final int APP8                      = 0xE8;
    public static final int APP9                      = 0xE9;
    public static final int APP10                     = 0xEA;
    public static final int APP11                     = 0xEB;
    public static final int APP12                     = 0xEC;
    public static final int APP13                     = 0xED;
    
    public static final int APP14                     = 0xEE;
    public static final int APP15                     = 0xEF;

    // codes 0xF0 to 0xFD are reserved

    
    public static final int COM                       = 0xFE;

    // JFIF Resolution units
    
    public static final int DENSITY_UNIT_ASPECT_RATIO = 0;
    
    public static final int DENSITY_UNIT_DOTS_INCH    = 1;
    
    public static final int DENSITY_UNIT_DOTS_CM      = 2;
    
    public static final int NUM_DENSITY_UNIT          = 3;

    // Adobe transform values
    public static final int ADOBE_IMPOSSIBLE          = -1;
    public static final int ADOBE_UNKNOWN             = 0;
    public static final int ADOBE_YCC                 = 1;
    public static final int ADOBE_YCCK                = 2;
}
