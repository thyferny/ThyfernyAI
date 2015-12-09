package org.trimps.images.analyzer.search.tree;

import java.util.List;

import org.trimps.images.analyzer.search.cluster.Clusterable;

public interface VocabularyTree {
	public List<Float> getCurrentWords();

	public List<Integer> addImage(List<? extends Clusterable> imagePoint);

	public void reset();
}
