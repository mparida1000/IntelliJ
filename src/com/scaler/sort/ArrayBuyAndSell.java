package com.scaler.sort;

public class ArrayBuyAndSell {
	static int[] a = {33,1,4,1,7,2,1,10};
	public static void main(String[] args) {
		System.out.println(maxProfit());
	}
	static int maxProfit() {
		int maxProfit = 0;
		int minimum = a[0];
		for(int i=0 ; i<a.length ; i++) {
			minimum = Math.min(minimum, a[i]);
			int profit = a[i] - minimum;
			maxProfit = Math.max(maxProfit, profit);
		}
		return maxProfit;
	}

}
