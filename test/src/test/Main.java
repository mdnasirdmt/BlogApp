package test;

public class Main {
	public static void main(String[] args) {
		
		int mi= Integer.MAX_VALUE;
		int ma= Integer.MIN_VALUE;
		int[]prices = {3,3};
		
		for (int i = 0; i < prices.length; i++) {
			
			if(prices[i]<mi) {
				mi=prices[i];
				
				
			}
		}
		
		System.out.println(mi);
		
		for (int i = mi; i < prices.length; i++) {
			
			if(prices[i]>ma) {
				ma=prices[i];
//				2147483645			
			}
		}
		System.out.println(ma);
		System.out.println(ma-mi);
	}
}
