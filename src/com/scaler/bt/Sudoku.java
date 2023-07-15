package com.scaler.bt;
/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells. Empty cells are indicated by the character '.' 
 * You may assume that there will be only one unique solution.

A sudoku puzzle,

and its solution numbers marked in red.

Problem Constraints
N = 9
 * @author mp1863
 *
 */
public class Sudoku {

	public void solveSudoku(char[][] A) {
		int n = A.length;
		solve(0,0,n,A);
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n ; j++) {
				System.out.print(A[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	boolean solve(int r, int c, int n, char[][] sudoku) {
		if(r == n)
			return true;
		if(c == n)
			return solve(r+1, 0, n, sudoku);
		if(sudoku[r][c] != '.')
			return solve(r, c+1, n, sudoku);
		char ch = (char) ('0'+ n);
		for(char i = '1'; i <= ch; i++) {
			if(valid(r,c,i,n,sudoku)) {
				sudoku[r][c] = i;
				if(solve(r, c+1, n, sudoku))
					return true;
				sudoku[r][c] = '.';
			}
		}
		return false;
	}
	
	boolean valid(int r, int c, char val, int n, char [][] sudoku) {
		for(int i = 0 ; i < n; i++) {
			if(sudoku[i][c] == val || sudoku[r][i] == val) {
				return false;
			}
		}
		int p = (int)Math.floor(Math.sqrt(n));
		int x = r - r%p;
		int y = c - c%p;
		for(int i = 0 ; i < p ; i++) {
			for(int j = 0 ; j < p ; j++) {
				int x1 = x + i;
				int y1 = y + j;
				if(sudoku[x1][y1] == val) {
					return false;
				}
			}
		}
		return true;		
	}
	
	public static void main(String[] args) {
		char [][] sudoku = {
				{'5','3','.','.','7','.','.','.','.'},
				{'6','.','.','1','9','5','.','.','.'},
				{'.','9','8','.','.','.','.','6','.'},
				{'8','.','.','.','6','.','.','.','3'},
				{'4','.','.','8','.','3','.','.','1'},
				{'7','.','.','.','2','.','.','.','6'},
				{'.','6','.','.','.','.','2','8','.'},
				{'.','.','.','4','1','9','.','.','5'},
				{'.','.','.','.','8','.','.','7','9'}
		};
		Sudoku su = new Sudoku();
		su.solveSudoku(sudoku);
	}
}
