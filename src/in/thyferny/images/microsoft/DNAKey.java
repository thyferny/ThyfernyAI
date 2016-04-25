package in.thyferny.images.microsoft;

import java.util.Arrays;

public class DNAKey {
	int hResult;
	byte[] hashValue;
	
	public DNAKey(int hResult, byte[] hashValue) {
		super();
		this.hResult = hResult;
		this.hashValue = hashValue;
	}
	public int gethResult() {
		return hResult;
	}
	public void sethResult(int hResult) {
		this.hResult = hResult;
	}
	public byte[] getHashValue() {
		return hashValue;
	}
	public void setHashValue(byte[] hashValue) {
		this.hashValue = hashValue;
	}
	@Override
	public String toString() {
		return "DnaResult [hResult=" + hResult + ", hashValue=" + Arrays.toString(hashValue) + "]";
	}
	
	
}
