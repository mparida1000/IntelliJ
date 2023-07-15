package com.scaler.array;

public class BuySellStocks {
	
	public static int maxProfit(final int[] A) {
		int maxProfit = 0;
		int min = Integer.MAX_VALUE;
		int n = A.length;
		for(int i = 0 ; i < n ; i++) {
			if(A[i] < min) {
				min = A[i];
			}
			maxProfit = Math.max(maxProfit, A[i] - min);
		}
		return maxProfit;
    }
	
	public static void main(String[] args) {
		int [] A = {10, 0, 1, 4, 5, 2, 4, 0, 6, 7};
		System.out.println(maxProfit(A));
	}

}
