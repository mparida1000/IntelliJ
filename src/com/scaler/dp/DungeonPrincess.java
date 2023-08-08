package com.scaler.dp;
/**
 * Problem Description
 * =-09asd
The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon. 
The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. 
If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; 
other rooms are either empty (0's) or contain magic herbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.

Given a 2D array of integers A of size M x N. Find and return the knight's minimum initial health so that he is able to rescue the princess.
 * @author mp1863
 *
 */
public class DungeonPrincess {

	public int calculateMinimumHP(int[][] A) {
		int n = A.length;
		int m = A[0].length;
		int [][] dp = new int [n][m];
		dp[n-1][m-1] = Math.max(1-A[n-1][m-1], 1);
		//last row
		for(int i =  n-2 ; i >=0 ; i--){
			dp[i][m-1] = Math.max(dp[i+1][m-1] - A[i][m-1], 1);
		}
		//last column
		for(int i = m-2 ; i >=0 ; i--) {
			dp[n-1][i] = Math.max(dp[n-1][i+1] - A[n-1][i], 1);
		}
		
		for(int i =  n-2 ; i >=0 ; i--){
			for(int j = m-2 ; j >=0 ; j--) {
				int down = Math.max(dp[i+1][j] - A[i][j], 1);
				int right = Math.max(dp[i][j+1] - A[i][j], 1);
				dp[i][j] = Math.min(down, right);
			}
		}
		
		return dp[0][0];
    }
	
	public static void main(String[] args) {
		int [][] A = {
				{-2, -3, 3},
				{-5, -10, 1},
				{10, 30, -5}
		};
		System.out.println(new DungeonPrincess().calculateMinimumHP(A));
	}
}
