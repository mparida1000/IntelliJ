package com.scaler.graph;
/*
 * Problem Description
Given a matrix of integers A of size N x M consisting of 0 and 1. A group of connected 1's forms an island. 
From a cell (i, j) such that A[i][j] = 1 you can visit any cell that shares a corner with (i, j) and value in that cell is 1.

More formally, from any cell (i, j) if A[i][j] = 1 you can visit:

(i-1, j) if (i-1, j) is inside the matrix and A[i-1][j] = 1.
(i, j-1) if (i, j-1) is inside the matrix and A[i][j-1] = 1.
(i+1, j) if (i+1, j) is inside the matrix and A[i+1][j] = 1.
(i, j+1) if (i, j+1) is inside the matrix and A[i][j+1] = 1.
(i-1, j-1) if (i-1, j-1) is inside the matrix and A[i-1][j-1] = 1.
(i+1, j+1) if (i+1, j+1) is inside the matrix and A[i+1][j+1] = 1.
(i-1, j+1) if (i-1, j+1) is inside the matrix and A[i-1][j+1] = 1.
(i+1, j-1) if (i+1, j-1) is inside the matrix and A[i+1][j-1] = 1.
Return the number of islands.

NOTE: Rows are numbered from top to bottom and columns are numbered from left to right.
 */
import java.util.ArrayDeque;
import java.util.Queue;

public class NumbeOfIslands {
	int [] row = {1,1,1,0,-1,-1,-1,0};
	int [] col = {1,0,-1,-1,-1,0,1,1};
	public int solve(int[][] A) {
		int n = A.length;
		int m = A[0].length;
		boolean [][] visited = new boolean[n][m];
		int count = 0;
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m ; j++) {
				if(A[i][j] == 1 && !visited[i][j]) {
					dfs(A, visited, i, j);
					count++;
				}
			}
		}
		return count;
    }
	
	boolean isValidMovement(int x, int y, int [][] arr, boolean [][] visited) {
		return (x >= 0 && x < visited.length) && 
				(y >= 0 && y < visited[0].length) &&
				(!visited[x][y] && arr[x][y] == 1);
	}
	
	void dfs(int [][] arr, boolean [][] visited, int i, int j) {
		Queue<Pair> queue = new ArrayDeque<>();
		queue.add(new Pair(i, j));
		visited[i][j] = true;
		while(!queue.isEmpty()) {
			int x = queue.peek().x;
			int y = queue.peek().y;
			queue.poll();
			for(int k = 0 ; k < row.length ; k++) {
				if(isValidMovement(x + row[k], y + col[k], arr, visited)) {
					visited[x + row[k]][y + col[k]] = true;
					queue.add(new Pair(x + row[k], y + col[k]));					
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int [][] arr = {
				{ 1, 0, 1, 0, 0, 0, 1, 1, 1, 1 },
                { 0, 0, 1, 0, 1, 0, 1, 0, 0, 0 },
                { 1, 1, 1, 1, 0, 0, 1, 0, 0, 0 },
                { 1, 0, 0, 1, 0, 1, 0, 0, 0, 0 },
                { 1, 1, 1, 1, 0, 0, 0, 1, 1, 1 },
                { 0, 1, 0, 1, 0, 0, 1, 1, 1, 1 },
                { 0, 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 1, 0, 0, 1, 1, 1, 0 },
                { 1, 0, 1, 0, 1, 0, 0, 1, 0, 0 },
                { 1, 1, 1, 1, 0, 0, 0, 1, 1, 1 }
		};
		System.out.println(new NumbeOfIslands().solve(arr));
	}

}

class Pair{
	int x;
	int y;
	
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
