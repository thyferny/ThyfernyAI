package org.trimps.images.analyzer.search.tree;

import java.util.List;

import org.trimps.images.analyzer.search.cluster.ClusterBuilder;
import org.trimps.images.analyzer.search.cluster.Clusterable;
import org.trimps.images.analyzer.search.util.SerializationUtils;

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
