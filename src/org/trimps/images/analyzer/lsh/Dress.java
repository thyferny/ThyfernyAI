package org.trimps.images.analyzer.lsh;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;


public class Dress {
	private final BitSet features = new BitSet();
	private final BitSet originfeatures = new BitSet();
	private List<Integer> ifeature = new ArrayList<Integer>();
	private final String name;
	
	public Dress(String name, List<Integer> featuresEnabled) {
		this.name = name;
		features.clear();
		originfeatures.clear();
		for (Integer feature : featuresEnabled) {
			ifeature.add(new Integer(feature.intValue()));
			originfeatures.set(feature);
			int feature2 = feature / 8; //TODO 对算法的改进，粗化索引值用
			features.set(feature2);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getSimpleFeatures() {
		StringBuilder sb = new StringBuilder();
		int nextSet = features.nextSetBit(0);
		while(nextSet >= 0) {
			sb.append(Feature.getByIndex(nextSet).description());
			sb.append(", ");
			nextSet = features.nextSetBit(nextSet+1);
		}
		return sb.toString();
	}
	
//	public String getFeatures() {
//		StringBuilder sb = new StringBuilder();
//		int nextSet = features.nextSetBit(0);
//		while(nextSet >= 0) {
//			sb.append(Feature.getByIndex(nextSet).description());
//			sb.append(", ");
//			nextSet = features.nextSetBit(nextSet+1);
//		}
//		return sb.toString();
//	}
	
	public BitSet getFeatureBitSet() {
		return features;
	}
	
	public BitSet getOriginfeatureBitSet() {
		return originfeatures;
	}

	public List<Integer> getIfeature() {
		return ifeature;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(" - ");
		sb.append(getSimpleFeatures());
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((originfeatures == null) ? 0 : originfeatures.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result
//				+ ((features == null) ? 0 : features.hashCode());
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		return result;
//	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dress other = (Dress) obj;
		if (originfeatures == null) {
			if (other.originfeatures != null)
				return false;
		} else if (!originfeatures.equals(other.originfeatures))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Dress other = (Dress) obj;
//		if (features == null) {
//			if (other.features != null)
//				return false;
//		} else if (!features.equals(other.features))
//			return false;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
//		return true;
//	}



//	private static Random random = new  Random(System.nanoTime());
//	public static Dress generateRandom() {
//		List<Integer> features = new ArrayList<Integer>();
//		
//		Bust[] bustSizes = Feature.Bust.values();
//		int index = random.nextInt(bustSizes.length);
//		features.add(bustSizes[index].index());
//		if(random.nextBoolean() && index > 0) {
//			features.add(bustSizes[index-1].index());
//		}
//		if(random.nextBoolean() && index < bustSizes.length-1) {
//			features.add(bustSizes[index+1].index());
//		}
//		
//		Feature.Waist[] waistSizes = Feature.Waist.values();
//		index = random.nextInt(waistSizes.length);
//		features.add(waistSizes[index].index());
//		if(random.nextBoolean() && index > 0) {
//			features.add(waistSizes[index-1].index());
//		}
//		if(random.nextBoolean() && index < waistSizes.length-1) {
//			features.add(waistSizes[index+1].index());
//		}
//		
//		Feature.Hip[] hipSizes = Feature.Hip.values();
//		index = random.nextInt(hipSizes.length);
//		features.add(hipSizes[index].index());
//		if(random.nextBoolean() && index > 0) {
//			features.add(hipSizes[index-1].index());
//		}
//		if(random.nextBoolean() && index < hipSizes.length-1) {
//			features.add(hipSizes[index+1].index());
//		}
//		
//		Feature.Length[] lengthSizes = Feature.Length.values();
//		index = random.nextInt(lengthSizes.length);
//		features.add(lengthSizes[index].index());
//		
//		return new Dress("Dress " + random.nextInt(Integer.MAX_VALUE), features);
//	}
}
