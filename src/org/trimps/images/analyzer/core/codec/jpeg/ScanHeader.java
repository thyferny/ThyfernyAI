
package org.trimps.images.analyzer.core.codec.jpeg;

public class ScanHeader {

    private int Ls; // Scan header length
    private int Ns; // Number of image components in scan
    private int Ss; // Start of spectral or predictor selection
    private int Se; // End of spectral selection
    private int Ah; // Successive approximation bit position high
    private int Al; // Successive approximation bit position low or point transform

    public int getLs() {
        return Ls;
    }

    public void setLs(int ls) {
        Ls = ls;
    }

    public int getNs() {
        return Ns;
    }

    public void setNs(int ns) {
        Ns = ns;
    }

    public int getSs() {
        return Ss;
    }

    public void setSs(int ss) {
        Ss = ss;
    }

    public int getSe() {
        return Se;
    }

    public void setSe(int se) {
        Se = se;
    }

    public int getAh() {
        return Ah;
    }

    public void setAh(int ah) {
        Ah = ah;
    }

    public int getAl() {
        return Al;
    }

    public void setAl(int al) {
        Al = al;
    }
}
