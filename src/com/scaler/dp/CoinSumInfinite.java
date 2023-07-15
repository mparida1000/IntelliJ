package com.scaler.dp;
/*
 * Problem Description
You are given a set of coins A. In how many ways can you make sum B assuming you have infinite amount of each coin in the set.

NOTE:

Coins in set A will be unique. Expected space complexity of this problem is O(B).
The answer can overflow. So, return the answer % (106 + 7).


Problem Constraints
1 <= A <= 500
1 <= A[i] <= 1000
1 <= B <= 50000
 */
import java.util.Arrays;

public class CoinSumInfinite {

	int m = 1000007;
    public int coinchange2(int[] A, int B) {
    	int[] dp = new int[B+1];
        dp[0] = 1;
        for (int coin : A) {
            for (int i = coin; i <= B; i++) {
                dp[i] += dp[i-coin];
                dp[i] %= m;
            }
        }
        return dp[B]; 
    }
    private int coinChangeWaysHelper(int[] A, int B, int m, int[][] dp) {
        if (B == 0) {
            return 1;
        }
        if (B < 0 || m == 0) {
            return 0;
        }
        if (dp[B][m] != -1) {
            return dp[B][m];
        }
        int ways = coinChangeWaysHelper(A, B, m-1, dp) + coinChangeWaysHelper(A, B-A[m-1], m, dp);
        ways %= (int)(1e6+7);
        dp[B][m] = ways;
        return ways;
    }
    
    public static void main(String[] args) {
		int [] A = {1,2, 3};
		int B =4;
		System.out.println(new CoinSumInfinite().coinchange2(A, B));
		
	}
}
