package in.thyferny.images.search.util;

public class Common {
	private double MAXDISTANCE = 204;

	public boolean similarEura(int[] feature1, int[] feature2) {
		if(feature1.length != feature2.length) {
			System.out.println("size of feature is not the same!");
			return false;
		}
		
		long sum = 0;
		for(int i = 0; i < feature1.length; i ++) {
			int i1 = feature1[i];
			int i2 = feature2[i];
			
			sum += (i1 - i2) * (i1 - i2);
		}
		
		double distance = Math.sqrt(sum);
		
		if(distance < MAXDISTANCE ) {
			return true;
		}
		
		return false;
	}
}
