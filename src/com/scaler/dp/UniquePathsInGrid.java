package com.scaler.dp;

import java.util.Arrays;

/**
 * Problem Description
Given a grid of size n * m, lets assume you are starting at (1,1) and your goal is to 
reach (n, m). At any instance, if you are on (x, y), you can either go to (x, y + 1) or (x + 1, y).

Now consider if some obstacles are added to the grids. How many unique paths would there be? 
An obstacle and empty space is marked as 1 and 0 respectively in the grid.

Problem Constraints
1 <= n, m <= 100
A[i][j] = 0 or 1
 * @author mp1863
 * 
 * https://drive.google.com/file/d/1-f0PcLhHgJoZGgwE9_CoBW8AfMQX9FvW/view?usp=share_link
 *
 */
public class UniquePathsInGrid {
	int [][] dp;
	public int uniquePathsWithObstacles(int[][] A) {
		if(A[0][0] == 1)return 0;
		int n = A.length;
		int m = A[0].length;
		dp = new int [n][m];
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m ; j++) {
				if(A[i][j] != 1) {
					dp[i][j] = -1;
				}
				if(A[i][j] == 1) {
					dp[i][j] = Integer.MIN_VALUE;
				}
			}
		}
		dp[0][0] = 1;
		for(int j = 1 ; j < m ; j++) {
			if(dp[0][j-1] != Integer.MIN_VALUE && dp[0][j] != Integer.MIN_VALUE) {
				dp[0][j] = 1;
			}else {
				dp[0][j] = Integer.MIN_VALUE;
			}
		}		
		for(int i = 1 ; i < n ; i++) {
			if(dp[i-1][0] != Integer.MIN_VALUE && dp[i][0] != Integer.MIN_VALUE) {
				dp[i][0] = 1;
			}else {
				dp[i][0] = Integer.MIN_VALUE;
			}
		}
		return ways(n-1,m-1);
    }
	
	int ways(int n, int m) {
		if(n < 0 || m < 0)return 0;
		if(dp[n][m] == Integer.MIN_VALUE) return 0;
		if(dp[n][m] != -1) {
			return dp[n][m];
		}
		dp[n][m] = ways(n-1,m) + ways(n,m-1);
		
		return dp[n][m];
	}
	public static void main(String[] args) {
		int [][] A = {
				{0,0,0},
				{1,0,0},
				{1,1,0}
				//{0, 0, 0, 0, 0, 1, 0, 0, 0}
		};
		System.out.println(new UniquePathsInGrid().uniquePathsWithObstacles(A));
	}
}
