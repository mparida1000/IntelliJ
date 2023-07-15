package com.scaler.array;

/**
 * Problem Description
 * Say you have an array, A, for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 *
 * Return the maximum possible profit.
 *
 *
 *
 * Problem Constraints
 * 0 <= A.size() <= 700000
 * 1 <= A[i] <= 107
 */
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
