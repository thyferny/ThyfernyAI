
package org.trimps.images.analyzer.search.cluster.impl;

import org.trimps.images.analyzer.search.cluster.ClusterChecker;
import org.trimps.images.analyzer.search.cluster.Clusterable;
import org.trimps.images.analyzer.search.util.ClusterUtils;


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
