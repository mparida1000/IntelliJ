package com.scaler.dp;

import java.util.Arrays;

public class Knapsack2 {
	// Memoization table to store previously computed subproblems
    
    public int solve(int[] A, int [] B, int C) {

        int n = B.length;
        int[] dp = new int[C+1];

        for(int i = 1; i <= n; i++){
            for(int j = C; j >= 0; j--){
                if(B[i-1] <= j){
                    dp[j] = Math.max(dp[j], A[i-1] + dp[j - B[i-1]]);
                }  
            }
        }
        return dp[C];  
    }    
    
    public int solve1(int [] A, int [] B, int C) {
        int N = B.length;
        int[][] dp = new int[N+1][C+1];
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= C; j++){
                int rem = j - B[i-1];
               if(rem >= 0) dp[i][j] = Math.max(dp[i-1][j], A[i-1] + dp[i-1][rem]);
               else dp[i][j] = dp[i-1][j];
            }
        }
        return dp[N][C];        
    }
    
    public int solve3(int[] A, int[] B, int C) {
        int n = A.length;
        int [][] dp1 = new int[n+1][C+1];
        for (int[] row : dp1) {
            Arrays.fill(row, -1);
        }
        return knapsack(A, B, n, C,dp1);
    }
    
    private static int knapsack(int[] A, int[] B, int n, int C,int[][] dp1) {
        // Base case: If the knapsack capacity is 0 or there are no items left to choose
        if (C == 0 || n == 0) {
            return 0;
        }
        // If the subproblem has already been solved, return the precomputed result
        if (dp1[n][C] != -1) {
            return dp1[n][C];
        }
        // If the weight of the nth item is greater than the knapsack capacity, exclude it
        if (B[n-1] > C) {
            dp1[n][C] = knapsack(A, B, n-1, C,dp1);
        }
        // Otherwise, choose the item and include it in the knapsack or exclude it
        else {
            dp1[n][C] = Math.max(A[n-1] + knapsack(A, B, n-1, C-B[n-1],dp1), knapsack(A, B, n-1, C,dp1));
        }
        return dp1[n][C];
    }
    public static void main(String[] args) {
        int[] A = {8, 5, 10, 12};
        int[] B = {4, 3, 5, 9};
        int C = 14;                
        int maxValue = new Knapsack2().solve3( A, B, C);
        System.out.println(maxValue);
    }
}
