package org.trimps.images.analyzer.lsh;

import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class G_Hamming {

	private static Long[] primes = new Long[] {
		2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L, 23L, 29L, 
		31L, 37L, 39L, 41L, 43L, 47L, 51L, 53L, 57L, 59L, 
		61L, 67L, 71L, 73L, 79L, 83L, 89L, 97L, 101L, 103L,
		107L, 109L, 113L, 127L, 131L, 137L };
	
	private Set<Integer> bits = new HashSet<Integer>();
	private Random random = new Random(System.nanoTime());
	
	/**
	 * The function h just checks 1 random bit from the input string. G is
	 * the family of functions that maps an input to k functions h chosen
	 * uniformly at random.
	 * 
	 * @param k
	 * @param maxBit the bitlength of the feature bitset (maximum bit index + 1)
	 */
	public G_Hamming(int k, int maxBit) {
		while (bits.size() < k) {
			bits.add(random.nextInt(maxBit));
		}
	}
	
	public G_Hamming(int k, List<Integer> ints) {		
		for(Integer i : ints) {
			bits.add(i);
		}
	}
	
	public long apply(BitSet features) {
		long hashcode = 1;
		int i = 0;
		for (Integer bitIndex : bits) {
			if (features.get(bitIndex)) {
				hashcode *= primes[i];
			}
			i++;
		}
		
		if (hashcode == 1) {
			return 0;
		}
		return hashcode;
	}

	public Set<Integer> getBits() {
		return bits;
	}

	public void setBits(Set<Integer> bits) {
		this.bits = bits;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("G_Hamming [bits=");
		builder.append(bits);
		builder.append("]");
		return builder.toString();
	}
}
