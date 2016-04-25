
package in.thyferny.images.core.codec.jpeg;

public class QuantizationTable {

    private int   Lq; // Quantization table definition length
    private int   Pq; // Quantization table element precision
    private int   Tq; // Quantization table destination identifier
    private int[] Q; // Quantization table elements (in natural order)

    public QuantizationTable(int[] q){
        this.Q = q;
    }

    public int getLq() {
        return Lq;
    }

    public void setLq(int lq) {
        Lq = lq;
    }

    public int getPq() {
        return Pq;
    }

    public void setPq(int pq) {
        Pq = pq;
    }

    public int getTq() {
        return Tq;
    }

    public void setTq(int tq) {
        Tq = tq;
    }

    public int[] getQ() {
        return Q;
    }

    public void setQ(int[] q) {
        Q = q;
    }
}
