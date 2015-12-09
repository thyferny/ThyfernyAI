
package org.trimps.images.analyzer.search.cluster.impl;

import java.util.List;

import org.trimps.images.analyzer.search.cluster.Clusterable;
import org.trimps.images.analyzer.search.util.ClusterUtils;


public class MeansClusterBuilder extends AbstractClusterBuilder {

    public MeansClusterBuilder(){
        super();
    }

    protected Clusterable[] assignClusters(Clusterable[] clusters, final List<? extends Clusterable> values) {
        assignClustersByDistance(clusters, values);
        return clusters;
    }

    protected void assignClustersByDistance(Clusterable[] clusters, List<? extends Clusterable> values) {
        // long start = 0;
        // long end = 0;
        // long cost = 0;
        // start = System.currentTimeMillis();
        for (int j = 0; j < values.size(); j++) {
            // start = System.currentTimeMillis();
            Clusterable val = values.get(j);
            // end = System.currentTimeMillis();
            // start = System.currentTimeMillis();
            Clusterable nearestCluster = null;
            float minDistance = Float.MAX_VALUE;
            for (int i = 0; i < clusters.length; i++) {
                Clusterable cluster = clusters[i];
                float distance = ClusterUtils.getEuclideanDistance(val, cluster);

                if (distance < minDistance) {
                    nearestCluster = cluster;
                    minDistance = distance;
                }
            }
            // end = System.currentTimeMillis();
            ((Cluster) nearestCluster).addItem(val);
            // end = System.currentTimeMillis();
            // cost += (end - start);
            
        }
    }

    protected Clusterable[] getNewClusters(Clusterable[] clusters) {
        for (int i = 0; i < clusters.length; i++) {
            if (((Cluster) clusters[i]).getItems().size() > 0) {
                clusters[i] = new Cluster(((Cluster) clusters[i]).getClusterMean(), i);
            }
        }
        return clusters;
    }

    public static void main(String args[]) {
        // Random random = new Random(System.currentTimeMillis());
        // int numPoints = 400000;
        // List<Clusterable> points = new ArrayList<Clusterable>(numPoints);
        // for ( int i = 0; i < numPoints; i++ ){
        // int x = random.nextInt(1000) - 500;
        // int y = random.nextInt(1000) - 500;
        // points.add(new Point((float)x,(float)y));
        //
        // }
        // KClusterer clusterer = new KMeansClusterer();
        // Cluster[] clusters = clusterer.cluster(points,10);
        // System.out.println("--------- cluster info --------------");
        // for ( Cluster c : clusters ){
        // System.out.println(c.getItems().size());
        // }
    }

    public static boolean hasBadValue(float[] values) {
        for (float value : values) {
            if (!(value < 1 && value > -1)) {
                return true;
            }
        }
        return false;
    }

}
