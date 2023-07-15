package com.scaler.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TusharBirthdayParty {

	public int solve(final int[] A, final int[] B, final int[] C) {
		int maxCapacity = 0;
		for(int i = 0 ; i < A.length ; i++) {
			maxCapacity = Math.max(maxCapacity, A[i]);
		}
		int [] dp = new int[maxCapacity+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for(int i = 1 ; i <= maxCapacity ; i++) {
			for(int j = 0 ; j < B.length ; j++) {
				if(i >= B[j]) {
					dp[i] = Math.min(dp[i], dp[i-B[j]]+C[j]);
				}
			}
		}
		int ans = 0;
		for(int i = 0 ; i < A.length ; i++) {
			ans+=dp[A[i]];
		}
		return ans;
    }
    

    public static void main(String[] args) {
    	int [] A = {2,4,6};
    	int [] B = {2,1,3};
    	int [] C = {2,1,5};
		System.out.println(new TusharBirthdayParty().solve(A, B, C));
	}
}
