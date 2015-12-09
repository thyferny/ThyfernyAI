
package org.trimps.images.analyzer.search.util;

import java.util.List;

import org.trimps.images.analyzer.search.cluster.Clusterable;


public class ClusterUtils {
    public static float getEuclideanDistance(double[] a,double[] b){
        if ( a.length != b.length ){
            throw new RuntimeException("Attempting to compare two clusterables of different dimensions");
        }

        double sum = 0;
        for ( int i = 0; i < a.length; i++ ){
            double diff = a[i] - b[i];
            sum += diff*diff; 
        }
        return (float)Math.sqrt(sum);
    }
    
    public static float getEuclideanDistance(float[] a,float[] b){
        if ( a.length != b.length ){
            throw new RuntimeException("Attempting to compare two clusterables of different dimensions");
        }

        float sum = 0f;
        for ( int i = 0; i < a.length; i++ ){
            float diff = a[i] - b[i];
            sum += diff*diff; 
        }
        return (float)Math.sqrt(sum);
    }
    
    public static float getEuclideanDistance(Clusterable a,Clusterable b){
        return getEuclideanDistance(a.getLocation(),b.getLocation());
    }
    
    public static float sumDifferences(List<Double> a, List<Double> b){
        assert(a.size() == b.size());
        float sumDiff = 0f;
//        double aSum = 0;
//        double bSum = 0;
        for ( int i = 0; i < a.size(); i++ ){
            sumDiff += Math.abs(a.get(i) - b.get(i));
//            aSum += a.get(i);
//            bSum += b.get(i);
        }
        return (float)sumDiff;
    }
}
