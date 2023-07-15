package com.scaler.graph;
/*
 * Problem Description

Given character matrix A of O's and X's, where O = white, X = black.

Return the number of black shapes. A black shape consists of one or more adjacent X's 
(diagonals not included)
 */
public class HW_BlackShape {
	private static final int[] ROW_DIRS = {0, 0, 1, -1};
    private static final int[] COL_DIRS = {1, -1, 0, 0};

    public int black(String[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int numShapes = 0;
        int numRows = A.length;
        int numCols = A[0].length();
        boolean[][] visited = new boolean[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (!visited[i][j] && A[i].charAt(j) == 'X') {
                    dfs(A, visited, i, j);
                    numShapes++;
                }
            }
        }

        return numShapes;
    }
    private void dfs(String[] A, boolean[][] visited, int row, int col) {
        visited[row][col] = true;

        for (int i = 0; i < ROW_DIRS.length; i++) {
            int newRow = row + ROW_DIRS[i];
            int newCol = col + COL_DIRS[i];

            if (newRow >= 0 && newRow < A.length && newCol >= 0 && newCol < A[0].length()
                    && !visited[newRow][newCol] && A[newRow].charAt(newCol) == 'X') {
                dfs(A, visited, newRow, newCol);
            }
        }
    }
    
    public static void main(String[] args) {
		String [] A =  {"XXOO","OOOX","XOXO","XOOO"};
		System.out.println(new HW_BlackShape().black(A));
	}
}
