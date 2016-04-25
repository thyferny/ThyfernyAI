package in.thyferny.images.search.tree;

import java.util.List;

import in.thyferny.images.search.cluster.ClusterBuilder;
import in.thyferny.images.search.cluster.Clusterable;
import in.thyferny.images.search.util.SerializationUtils;

public class VocabTreeManager {
	private static KMeansTree tree;

	public static KMeansTree loadVocabTree(String file) {
		if (tree == null) {
			tree = (KMeansTree) SerializationUtils.loadObject(file);
		}
		return tree;
	}
	
	public static KMeansTree makeTree(List<Clusterable> points,
			ClusterBuilder clusterer, int groupId) {
		return makeSampledTree(points, clusterer, 1);
	}

	public static KMeansTree makeSampledTree(List<Clusterable> points,
	                                         ClusterBuilder clusterer, int useEvery) {

		KMeansTree tree = new KMeansTree(points, 10, 5, clusterer);
		return tree;
	}
	
}
