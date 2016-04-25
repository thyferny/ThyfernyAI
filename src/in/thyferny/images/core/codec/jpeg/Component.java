
package in.thyferny.images.core.codec.jpeg;


public class Component {

    private int       index;
    private int       C;                   // Component identifier
    private int       H           = 1;     // Horizontal sampling factor
    private int       V           = 1;     // Vertical sampling factor
    private int       Tq;                  // Quantization table destination selector
    private int       HorizonDCTScaledSize;
    private int       VerticaDCTScaledSize;

    // runtime var
    HuffmanTable      dcHuffTable;         // DC Huffman table used by this component
    HuffmanTable      acHuffTable;         // AC Huffman table used by this component
    QuantizationTable qTable;              // Quantization table used by this component
    int               sampleTimes = 1;     // 

    public int getC() {
        return C;
    }

    public void setC(int c) {
        C = c;
    }

    public int getH() {
        return H;
    }

    public void setH(int h) {
        H = h;
    }

    public int getV() {
        return V;
    }

    public void setV(int v) {
        V = v;
    }

    public int getTq() {
        return Tq;
    }

    public void setTq(int tq) {
        Tq = tq;
    }

    public HuffmanTable getDcHuffTable() {
        return dcHuffTable;
    }

    public void setDcHuffTable(HuffmanTable dcHuffTable) {
        this.dcHuffTable = dcHuffTable;
    }

    public HuffmanTable getAcHuffTable() {
        return acHuffTable;
    }

    public void setAcHuffTable(HuffmanTable acHuffTable) {
        this.acHuffTable = acHuffTable;
    }

    public QuantizationTable getQTable() {
        return qTable;
    }

    public void setQTable(QuantizationTable table) {
        qTable = table;
    }

    
    public int getSampleTimes() {
        return sampleTimes;
    }

    public void setSampleTimes(int st) {
        this.sampleTimes = st;
    }

    
    public int getIndex() {
        return index;
    }

    
    public void setIndex(int index) {
        this.index = index;
    }

    
    public int getHorizonDCTScaledSize() {
        return HorizonDCTScaledSize;
    }

    
    public void setHorizonDCTScaledSize(int horizonDCTScaledSize) {
        HorizonDCTScaledSize = horizonDCTScaledSize;
    }

    
    public int getVerticaDCTScaledSize() {
        return VerticaDCTScaledSize;
    }

    
    public void setVerticaDCTScaledSize(int verticaDCTScaledSize) {
        VerticaDCTScaledSize = verticaDCTScaledSize;
    }
}
