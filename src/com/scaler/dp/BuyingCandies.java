package com.scaler.dp;

import java.util.Arrays;

/**
 * Problem Description
Rishik likes candies a lot. So, he went to a candy-shop to buy candies.

The shopkeeper showed him N packets each containg A[i] candies for cost of C[i] nibbles, each candy in that packet has a sweetness B[i]. 
The shopkeeper puts the condition that Rishik can buy as many complete candy-packets as he wants but he can't buy a part of the packet.

Rishik has D nibbles, can you tell him the maximum amount of sweetness he can get from candy-packets he will buy?
 * @author mp1863
 *
 */
public class BuyingCandies {
	int [][] dp;
	public int solve(int[] A, int[] B, int[] C, int D) {
        int n = A.length;
        dp = new int[n+1][D+1];
        for(int i=0; i<=n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return dfs(A, B, C, D, n);
    }
    private int dfs(int[] A, int[] B, int[] C, int d, int i) {
        if(i == 0 || d == 0) {
            return 0;
        }
        if(dp[i][d] != -1) {
            return dp[i][d];
        }
        int exclude = dfs(A, B, C, d, i-1);
        int include = 0;
        if(d >= C[i-1]) {
            include = dfs(A, B, C, d-C[i-1], i) + A[i-1]*B[i-1];
        }
        return dp[i][d] = Math.max(exclude, include);
    }
	
	public static void main(String[] args) {
		int [] A = {2, 3};
		int [] B = {3, 5};
		int [] C = {2, 4};
		System.out.println(new BuyingCandies().solve(A, B, C, 3));
		
	}
}
