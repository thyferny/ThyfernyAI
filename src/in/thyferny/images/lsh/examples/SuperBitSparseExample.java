package in.thyferny.images.lsh.examples;

import java.util.Random;

import in.thyferny.images.core.util.SparseIntegerVector;
import in.thyferny.images.lsh.SuperBit;

/**
 *
 * @author Thibault Debatty
 */
public class SuperBitSparseExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int n = 1000;
        
        // Initialize SuperBit algorithm for n dimensions
        SuperBit sb = new SuperBit(n);
        
        
        // Create some sparse vectors
        Random rand = new Random();
        
        int[] v = new int[n];
        for (int i = 0; i < n/10; i++) {
            v[rand.nextInt(n)] = rand.nextInt(100);
        }
        SparseIntegerVector v1 = new SparseIntegerVector(v);
        
        v = new int[n];
        for (int i = 0; i < n/10; i++) {
            v[rand.nextInt(n)] = rand.nextInt(100);
        }
        SparseIntegerVector v2 = new SparseIntegerVector(v);

        boolean[] sig1 = sb.signature(v1);
        boolean[] sig2 = sb.signature(v2);
        
        System.out.println("Signature (estimated) similarity: " + 
                sb.similarity(sig1, sig2));
        System.out.println("Real cosine similarity: " + v1.dotProduct(v2) / (v1.norm() * v2.norm()));
        
    }
    
}
