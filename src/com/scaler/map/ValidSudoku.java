package com.scaler.map;

import java.util.HashMap;
import java.util.Map;

public class ValidSudoku {
	public static int isValidSudoku(final String[] A) {
		int [][] sudoku = new int[9][9];
		for(int i = 0 ; i < A.length ; i++) {
			String s = A[i];
			for(int j = 0 ; j < s.length() ; j++) {
				sudoku[i][j] = s.charAt(j)!='.' ? Integer.parseInt(String.valueOf(s.charAt(j))) : 0;
			}
		}

		Map<String, String> map = new HashMap();
		for(int i = 0 ; i < 9 ; i++) {
			for(int j = 0 ; j < 9 ; j++) {
				if(sudoku[i][j] != 0) {
					if(!map.containsKey("C"+j)) {
						map.put("C"+j, String.valueOf(sudoku[i][j]));
					}else {
						if(map.get("C"+j).contains(String.valueOf(sudoku[i][j]))) return 0;
						map.put("C"+j, map.get("C"+j) + String.valueOf(sudoku[i][j]));
					}
					if(!map.containsKey("R"+i)) {
						map.put("R"+i, String.valueOf(sudoku[i][j]));
					}else {
						if(map.get("R"+i).contains(String.valueOf(sudoku[i][j]))) return 0;
						map.put("R"+i, map.get("R"+i) + String.valueOf(sudoku[i][j]));
					}
					String box = SudokuBoxes.getrowAndCol(i, j);
					if(!map.containsKey(box)) {
						map.put(box, String.valueOf(sudoku[i][j]));
					}else {
						if(map.get(box).contains(String.valueOf(sudoku[i][j]))) return 0;
						map.put(box, map.get(box)+String.valueOf(sudoku[i][j]));
					}
				}
			}
		}
		
		return 1;
    }
	
	public static void main(String[] args) {
		
		final String [] A = {"..5.....6", "....14...", ".........", ".....92..", "5....2...", ".......3.", "...54....", "3.....42.", "...27.6.."};
		long t1 = System.currentTimeMillis();
		int resutlt = isValidSudoku(A);
		//System.out.println(System.currentTimeMillis() - t1);
		System.out.println(resutlt);
	}

}

	enum SudokuBoxes {
	   BOX0("012-012"), BOX1("012-345"), BOX2("012-678"), BOX3("345-012"), BOX4("345-345"), BOX5("345-678"), 
	   	BOX6("678-012"), BOX7("678-345"), BOX8("678-678");
	   private String row_col;
	   SudokuBoxes(String row_col) {
	      this.row_col = row_col;
	   }
	   public String getrowAndCol() {
	      return this.row_col;
	   }
	   
	   public static String getrowAndCol(int r, int c) {
		   SudokuBoxes sudoku[] = SudokuBoxes.values();
		   for(SudokuBoxes su: sudoku) {
		      if(su.getrowAndCol().split("-")[0].contains(String.valueOf(r)) && su.getrowAndCol().split("-")[1].contains(String.valueOf(c))) {
		       return su.toString();
		      }
		   }
		   return null;
	   }
	}
