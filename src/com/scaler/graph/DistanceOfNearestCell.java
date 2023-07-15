package com.scaler.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/*
 * Problem Description
Given a matrix of integers A of size N x M consisting of 0 or 1.
For each cell of the matrix find the distance of nearest 1 in the matrix.
Distance between two cells (x1, y1) and (x2, y2) is defined as |x1 - x2| + |y1 - y2|.
Find and return a matrix B of size N x M which defines for each cell in A distance of nearest 1 in the matrix A.
NOTE: There is atleast one 1 is present in the matrix.
 */
public class DistanceOfNearestCell {

	public int[][] solve(int[][] A) {
		int n = A.length;
		int m = A[0].length;
		int [][] B = new int [n][m];
		for(int i = 0 ; i < n ; i++) {
			Arrays.fill(B[i], Integer.MAX_VALUE);
		}
		Queue<int[]> q = new ArrayDeque<>();
		for(int i = 0 ; i < n ; i++)
			for(int j = 0 ; j < m ; j++)
				if(A[i][j] == 1) {
					q.offer(new int [] {i, j});
					B[i][j] = 0;
				}
		int [][] dirs = {{1,0},{0,-1},{-1,0},{0,1}};
		while(!q.isEmpty()) {
			int [] curr = q.poll();
			int x = curr[0];
			int y = curr[1];
			for(int i = 0 ; i < 4 ; i++) {
				int x_ = x + dirs[i][0];
				int y_ = y + dirs[i][1];
				if(x_ >=0 && x_ < n && y_ >=0 && y_ < m) {
					if(B[x_][y_] > B[x][y] +1) {
						B[x_][y_] = B[x][y] +1;
						q.offer(new int [] {x_, y_});
					}
				}
			}
		}
		return B;
					
	}
	
	public static void main(String[] args) {
		int [][] A = {
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,1}
		};
		int [][] op = new DistanceOfNearestCell().solve(A);
		for(int i = 0 ; i < op.length ; i++) {
			for(int j = 0 ; j < op[0].length ; j++) {
				System.out.print(op[i][j] + " ");
			}
			System.out.println();
		}
	}
}
