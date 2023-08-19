package com.scaler.graph;
/*
 * Problem Description
Given a matrix of integers A of size N x M consisting of 0, 1 or 2.
Each cell can have three values:
The value 0 representing an empty cell.
The value 1 representing a fresh orange.
The value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (Left, Right, Top, or Bottom) to a rotten orange becomes 
rotten. 
Return the minimum number of minutes that must elapse until no cell has a fresh orange. 
If this is impossible, return -1 instead.

Note: Your solution will run on multiple test cases. If you are using global variables, make sure to clear them.
 */
import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {

	public int solve(int[][] A) {
        int n = A.length;
        int m = A[0].length;        
        int fresh = 0;
        Queue<int[]> queue = new LinkedList<>();        
        // count the number of fresh oranges and add the rotten oranges to the queue
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] == 1) {
                    fresh++;
                } else if (A[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        
        int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                for (int[] dir : dirs) {
                    int r = curr[0] + dir[0];
                    int c = curr[1] + dir[1];
                    if (r >= 0 && r < n && c >= 0 && c < m && A[r][c] == 1) {
                        A[r][c] = 2;
                        fresh--;
                        queue.offer(new int[]{r, c});
                    }
                }
            }
            if (!queue.isEmpty()) {
                time++;
            }

        }        
        return fresh == 0 ? time : -1;
    }
	
	public static void main(String[] args) {
		int [][] A = {
				{1,2,0,0},
				{1,1,1,0},
				{0,1,2,1},
                {2,1,0,0}
		};
		System.out.println(new RottenOranges().solve(A));
	}
}
