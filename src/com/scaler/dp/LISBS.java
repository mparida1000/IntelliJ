package com.scaler.dp;

import java.util.Arrays;

public class LISBS {
	public static int longestIncreasingSubsequence(int[] nums) {
	    int n = nums.length;
	    int[] tails = new int[n];
	    int len = 0;

	    for (int x : nums) {
	        int i = Arrays.binarySearch(tails, 0, len, x);
	        if (i < 0) {
	            i = -(i + 1);
	        }
	        tails[i] = x;
	        if (i == len) {
	            len++;
	        }
	    }

	    return len;
	}
	
	public static void main(String[] args) {
		int [] A = {1,5,3,6};
		int [] B = {0,0,0,0};
		System.out.println(new LISBS().longestIncreasingSubsequence(A));
	}


}
