package com.scaler.dp;

public class MaxProductSubarray {

	public int maxProduct(final int[] A) {
		int max = A[0];
		int min = A[0];
		int result = max;
		for(int i = 1; i < A.length ; i++) {
			int temp = Math.max(A[i], Math.max(max*A[i], min*A[i]));
			min = Math.min(A[i], Math.min(max*A[i], min*A[i]));
			max = temp;
			result = Math.max(max, result);
		}
		return result;
    }
	
	public static void main(String[] args) {
		int [] A = {0, 0, -1, 2, 0, 0, 1, 2, 1, 2, 1};
		System.out.println(new MaxProductSubarray().maxProduct(A));
	}
}
