
package in.thyferny.images.search.cluster.impl;

import in.thyferny.images.search.cluster.ClusterChecker;
import in.thyferny.images.search.cluster.Clusterable;
import in.thyferny.images.search.util.ClusterUtils;


public class DriftClusterChecker implements ClusterChecker {

    private float mDriftTolerance;

    public DriftClusterChecker(float driftTolerance){
        mDriftTolerance = driftTolerance;
    }

    public boolean recalculateClusters(Clusterable[] clusters) {
        for (Clusterable cluster : clusters) {
            if (cluster instanceof Cluster) {
                if (((Cluster) cluster).getItems().size() > 0) {
                    float distanceChange = ClusterUtils.getEuclideanDistance(((Cluster) cluster).getClusterMean(),
                                                                             cluster.getLocation());
                    if (distanceChange > mDriftTolerance) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
