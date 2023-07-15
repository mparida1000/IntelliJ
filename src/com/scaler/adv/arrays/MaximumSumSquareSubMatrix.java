package com.scaler.adv.arrays;

public class MaximumSumSquareSubMatrix {
	public static int solve(int[][] A, int B) {
		int n = A.length;
		int max = Integer.MIN_VALUE;
		int pf [][] = new int[n][n];
		pf[0][0] = A[0][0];
		for(int i = 0 ; i < n ; i++) {
			pf[i][0] = A[i][0];
		}
		for(int i = 0 ; i < n ; i++) {
			for(int j = 1 ; j < n ; j++) {
				pf[i][j] = pf[i][j-1]+A[i][j]; //row wise prefix
			}
		}
		for(int i = 0 ; i < n ; i++) {
			for(int j = 1 ; j < n ; j++) {
				pf[j][i] = pf[j-1][i]+pf[j][i]; //column wise prefix
			}
		}
		
		for(int i = 0 ; i <= n-B ; i++) {
			for(int j = 0 ; j <= n-B ; j++) {
				int a1 = i, b1 = j, a2 = i+B-1, b2 = j+B-1;
				int currMax = pf[a2][b2] - (b1 > 0 ? pf[a2][b1-1] : 0) - (a1 > 0 ? pf[a1-1][b2] : 0) + (a1 > 0 && b1 > 0 ? pf[a1-1][b1-1] : 0);
				max = Math.max(currMax, max);
			}
			
		}
			
		return max;
    }
	
	
	public static void main(String[] args) {
		int [][] A = {
				{1, 1, 1, 1, 1},
				{2, 2, 2, 2, 2},
				{3, 8, 6, 7, 3},
				{4, 4, 4, 4, 4},
				{5, 5, 5, 5, 5}
				};
		System.out.println(solve(A, 3));
	}

}
