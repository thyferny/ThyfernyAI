
package in.thyferny.images.search.cluster;

import java.util.List;


public interface ClusterBuilder {
    public Clusterable[] collect(final List<? extends Clusterable> values, int numClusters);
}
