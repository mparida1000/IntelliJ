package com.scaler.bt;
/*
 * Problem Description
Given a matrix of integers A of size N x M . There are 4 types of squares in it:

1. 1 represents the starting square.  There is exactly one starting square.
2. 2 represents the ending square.  There is exactly one ending square.
3. 0 represents empty squares we can walk over.
4. -1 represents obstacles that we cannot walk over.
Find and return the number of 4-directional walks from the starting square to the ending 
square, that walk over every non-obstacle square exactly once.

Note: Rows are numbered from top to bottom and columns are numbered from left to right.
Problem Constraints
2 <= N * M <= 20
-1 <= A[i] <= 2
 */
public class UniquePaths3 {

	public int solve(int[][] A) {
		int s = 0, e = 0, zeros = 0;
		for(int i = 0 ; i < A.length ; i++) {
			for(int j = 0 ; j < A[0].length ; j++) {
				if(A[i][j] == 0)zeros++;
				if(A[i][j] == 1) {
					s = i;
					e = j;
				}
			}
		}
		return dfs(A, s, e, zeros);
    }
	
	int dfs(int [][] arr, int x, int y, int zeros) {
		if(x < 0 || y < 0 || x >= arr.length || y >= arr[0].length || arr[x][y] == -1) {
			return 0;
		}
		if(arr[x][y] == 2) {
			return zeros == -1 ? 1 : 0;
		}
		arr[x][y] = -1;
		zeros--;
		int uniquePaths = dfs(arr, x+1, y, zeros) + dfs(arr, x-1, y, zeros) +
				dfs(arr, x, y+1, zeros) + dfs(arr, x, y-1, zeros);
		arr[x][y] = 0;
		zeros++;
		return uniquePaths;
	}
	
	public static void main(String[] args) {
		int [][] A = {
				{1,0},
				{2,0}
		};
		UniquePaths3 up3 = new UniquePaths3();
		System.out.println(up3.solve(A));
	}
}
