package com.scaler.dp;

public class MaxRectangleInBinaryMatrix {
	int[][] dp;
    public int maximalRectangle(int[][] A) {
        int n = A.length;
        if (n == 0) return 0;
        int m = A[0].length;
        
        dp = new int[n][m];
        int maxArea = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] == 1) {
                    dp[i][j] = (i == 0) ? 1 : dp[i-1][j] + 1;
                    maxArea = Math.max(maxArea, getMaxArea(i, j, A));
                }
            }
        }
        
        return maxArea;
    }
    
    private int getMaxArea(int i, int j, int[][] A) {
        int minHeight = dp[i][j];
        int maxArea = minHeight;
        for (int k = j-1; k >= 0 && A[i][k] == 1; k--) {
            minHeight = Math.min(minHeight, dp[i][k]);
            maxArea = Math.max(maxArea, (j-k+1)*minHeight);
        }
        return maxArea;
    }
    
    public static void main(String[] args) {
		int [][] A = {
				{1 ,0, 0, 1, 1},
				{1 ,1, 1, 1, 1},
				{0 ,1, 1, 0, 1}
		};
		System.out.println(new MaxRectangleInBinaryMatrix().maximalRectangle(A));
	}
}
