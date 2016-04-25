package in.thyferny.images.search.tree;

import java.util.List;

import in.thyferny.images.search.cluster.Clusterable;

public interface VocabularyTree {
	public List<Float> getCurrentWords();

	public List<Integer> addImage(List<? extends Clusterable> imagePoint);

	public void reset();
}
