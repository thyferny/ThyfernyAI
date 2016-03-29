package org.trimps.images.analyzer.lsh;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LSH {
	
//	private static final int MAXDISTANCE = 5;
	
//	private final int k;
//	private final int l;
	private Map<Long, List<Dress>> table = new HashMap<Long, List<Dress>>();	//由Spark处理
	private List<G_Hamming> G = new ArrayList<G_Hamming>();	//存放G_Hamming对象列表
	private List<Dress> catalog;	//只是存放所有的Dress
	private Random random = new Random(System.nanoTime());
	
	//下面是新的LSH索引算法参数
	private int dimention = 144; //维度大小，例如对于sift特征来说就是128
	private int max = 255; //所需向量中元素可能的上限，譬如对于RGB来说，就是255
	private int hashCount = 5; //哈希表的数量，用于更大程度地削减false positive
	//LSH随机选取的采样位数，该值越小，则近似查找能力越大，但相应的false positive也越大；若该值等于size，则为由近似查找退化为精确匹配
	private int bitCount = 10; 
	private int size = 144 * 255; //转化为01字符串之后的位数，等于max乘以dimensions
	private int[][] hashFamily= {{33976, 9285, 30408, 32127, 370, 29035, 9919, 24881, 4146, 20356}, 
    		{28541, 18262, 15992, 6692, 33834, 26461, 11318, 19958, 33924, 11872}, 
    		{21499, 1736, 7436, 14157, 13727, 26023, 4754, 6303, 10826, 15554}, 
    		{32341, 32184, 34624, 4893, 32492, 8573, 2199, 20805, 30658, 23726}, 
    		{5481, 3690, 12492, 21617, 35316, 19758, 9686, 35818, 3759, 2889}}; //LSH哈希族，保存了随机采样点的INDEX
	
	public LSH() {
//		k = SystemConfig.HammingColumns;
//		l = SystemConfig.HammingSize;
	}
	
//	/**
//	 * 计算指定特征向量的特征值列表（方法1）
//	 * @param featureQuery
//	 * @return
//	 */
//	public Set<Long> computeHashList(BitSet featureQuery) {
//		
//		Set<Long> set = new HashSet<Long>();
//		for (G_Hamming g : SystemConfig.ghammingList) {
//			//for each bucket that in g(featureQuery)
//			long hash = g.apply(featureQuery);
//			if(hash > 0) {
//				set.add(hash);
//			}
//		}
//		
//		return set;
//	}
	
	/**
	 * 计算Hash值（方法2）
	 * @param ilist
	 * @return
	 */
	public List<byte[]> computeHashList(List<Integer> ilist) {
		
		List<byte[]> hlist = new ArrayList<byte[]>();
		
		for(int i = 0; i < hashCount; i ++) {
			hlist.add(generateHashKey(ilist, i));
		}
		
		return hlist;
	}
	
//	/**
//	 * 判断两个特征向量是否相似（之间的Hamming距离是否在maxDistance之内）
//	 * @param featureQuery
//	 * @param featureBase
//	 * @param maxDistance
//	 * @return
//	 */
//	public boolean similar(BitSet featureQuery, BitSet featureBase, int maxDistance) {
//		int distance = hammingDistance(featureQuery, featureBase);
//		if(distance == 0) {
//			log.info("complete the same!");
//		}
//		
//		if (distance <= maxDistance) {
//			return true;
//		}
//		
//		log.info("distance: " + distance);
//		
//		return false;
//	}
	
	
	/**
	 * 计算两个特征向量之间的Hamming距离
	 * @param a
	 * @param b
	 * @return
	 */
	private int hammingDistance(BitSet a, BitSet b) {
		BitSet clone = (BitSet)a.clone();
		clone.xor(b);
		return clone.cardinality();
	}
	
	//将向量转化为二进制字符串，比如元素的最大范围255，则元素65就被转化为65个1以及190个0
	private int[] unAray(List<Integer> data) {
	    int unArayData[] = new int[size];
	    for (int i = 0; i < data.size(); i++) {
	        for (int j = 0; j < data.get(i); j++) {
		    unArayData[i * max + j] = 1;
	        }
	    }
	    return unArayData;
	}
	
	//将向量映射为LSH中的key
	private byte[] generateHashKey(List<Integer> list, int hashNum) {
	    StringBuilder sb = new StringBuilder();
	    int[] tempData = unAray(list);
	    int[] hashedData = new int[bitCount];
	    //首先将向量转为二进制字符串
	    for (int i = 0; i < bitCount; i++) {
	        hashedData[i] = tempData[hashFamily[hashNum][i]];
	        sb.append(hashedData[i]);
//		        System.out.print(hashedData[i]);
	    }
	    
	    //再用常规hash函数比如MD5对key进行压缩
	    MessageDigest messageDigest = null;
	    try 
	    {
	        messageDigest = MessageDigest.getInstance("MD5");
	    }
	    catch (NoSuchAlgorithmException e) {
	    }

	    byte[] binary = sb.toString().getBytes();
	    byte[] hash = messageDigest.digest(binary);
//	    String hashV = toHex(hash);
	    
//	    System.out.println(hashV);
	    
	    
	    return hash;
	}
	
	public static String toHex(byte buffer[]) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 15, 16));
		}

		return sb.toString();
	}
	
	public static void main(String[] args) {
		MessageDigest messageDigest = null;
	    try 
	    {
	        messageDigest = MessageDigest.getInstance("MD5");
	    }
	    catch (NoSuchAlgorithmException e) {
	    }
	    byte[] hash = messageDigest.digest("http://www.chinainperspective.com:80/ArtShow.aspx?AID=84925".getBytes());
	    System.out.println(toHex(hash));
	}
	
	/**
	 * 将16位byte[] 转换为32位String
	 * 
	 * @param buffer
	 * @return
	 */
	
}
