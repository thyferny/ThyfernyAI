
package org.trimps.images.analyzer.search.cluster.impl;


import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.trimps.images.analyzer.search.cluster.ClusterBuilder;
import org.trimps.images.analyzer.search.cluster.ClusterChecker;
import org.trimps.images.analyzer.search.cluster.Clusterable;

public abstract class AbstractClusterBuilder implements ClusterBuilder
{
    public static float DISTANCE_TOLERANCE = 0.005f;
    public static int MAX_RECLUSTERING = 100;
    
    int mMaxReclustering = MAX_RECLUSTERING;
    ClusterChecker mChecker;
    
    protected AbstractClusterBuilder(){
        this(new DriftClusterChecker(DISTANCE_TOLERANCE),MAX_RECLUSTERING);
    }
    
    protected AbstractClusterBuilder(ClusterChecker checker){
        this(checker,MAX_RECLUSTERING);
    }
    
    protected AbstractClusterBuilder(ClusterChecker checker, int maxReclustering){
        mChecker = checker;
        mMaxReclustering = maxReclustering;
    }
    
    public Clusterable[] collect(final List<? extends Clusterable> values, int numClusters) {
        Clusterable[] clusters = calculateInitialClusters(values,numClusters);
        
        boolean recalculateClusters = true;

        int numIterations = 0;
        while ( recalculateClusters ){
            //add all items to nearest cluster
            //System.out.println(numIterations);
            clusters = assignClusters(clusters,values);
            
            //see if the cluster distance hasn't moved
            recalculateClusters = mChecker.recalculateClusters(clusters);
            
            //if it needs to be run again, set up new clusters on the updated centers
            if ( recalculateClusters ){
                if ( numIterations > mMaxReclustering ){
                    recalculateClusters = false;
                }
                
                clusters = getNewClusters(clusters);
                
                numIterations++;
            }
        }
        
        return clusters;
    }

    protected abstract Clusterable[] assignClusters(Clusterable[] clusters,final List<? extends Clusterable> values);
    
    protected abstract Clusterable[] getNewClusters(Clusterable[] clusters);
    
    
    protected Cluster[] calculateInitialClusters(List<? extends Clusterable> values, int numClusters){
        Cluster[] clusters = new Cluster[numClusters];
        //choose centers and create the initial clusters
        Random random = new Random(1);
        Set<Integer> clusterCenters = new HashSet<Integer>();
        for ( int i = 0; i < numClusters; i++ ){
            int index = random.nextInt(values.size());
            while ( clusterCenters.contains(index) ){
                index = random.nextInt(values.size());
            }
            clusterCenters.add(index);
            clusters[i] = new Cluster(values.get(index).getLocation(),i);
        }
        return clusters;
    }
}
