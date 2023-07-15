package com.scaler.bt;

import java.util.ArrayList;
/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer A, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
The final list should be generated in such a way that the indices of the queens in each list should be in reverse lexicographical order.
 * @author mp1863
 *
 */
public class NQueen {
	int [] col;
	public ArrayList<ArrayList<String>> solveNQueens(int A) {
		col = new int[A];
		boolean res =  NQueen(0, col, A );
		ArrayList<ArrayList<String>> result = new ArrayList<>();
		for(int i = 0 ; i < A ; i ++) {
			ArrayList<String> list = new ArrayList<>();
			for(int j = 0 ; j < A ; j++) {
				
			}
		}
		return result;
	}
	
	boolean NQueen(int r, int [] col, int n) {
		if(r == n) {
			return true;
		}
		for(int i = 0 ; i < n ; i++) {
			if(isValid (r, i, col)) {
				col[r] = i;
				if(NQueen(r+1,col,n)) {
					return true;
				}
				col[r] = -1;
			}
		}
		return false;
	}
	boolean isValid(int r, int c, int [] col) {
		for(int i = 0 ; i < r ; i++) {
			if(checkIfAttacks(r, c, i, col[i])) {
				return false;
			}
		}
		return true;
	}
	boolean checkIfAttacks(int x1, int y1, int x2, int y2) {
		if(x1 == x2 || y1 == y2 || (x1+y1) == (x2+y2) || (x1-y1) == (x2-y2)) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		NQueen nq = new NQueen();
		System.out.println(nq.col);
		System.out.println(nq.solveNQueens(4));
	}
}
