package com.scaler.recursion;

import java.util.Scanner;

class Matrix {
    int row;
    int col;
    int matrix[][];
    
    public Matrix(int row, int col) {
    	this.row = row;
    	this.col = col;
    	this.matrix = new int[row][col];
    }
    
	void input(Scanner sc){
		int row = sc.nextInt();
		 int col = sc.nextInt();
	    // Use the Scanner object passed as argument for taking input and not a new Scanner object
		// Complete the function
		
	}
	
	Matrix add(Matrix x){
		int m = row;
		int n = col;
		for(int i = 0 ; i < m ; i++) {
			for(int j = 0 ; j < n ; j++) {
				this.matrix[i][j]+= x.matrix[i][j];
			}
		}
		return this;
		
	}
	
	Matrix subtract(Matrix x){
		int m = row;
		int n = col;
		for(int i = 0 ; i < m ; i++) {
			for(int j = 0 ; j < n ; j++) {
				this.matrix[i][j]-= x.matrix[i][j];
			}
		}
		return this;
		
	}
		
	Matrix transpose(){
		int m = row;
		int n = col;
		Matrix tm = new Matrix(m, n);
		for(int i = 0 ; i < m ; i++) {
			for(int j = 0 ; j < n ; j++) {
				tm.matrix[i][j] = this.matrix[j][i];
			}
		}
		return this;
		
	}
	
	void print(){
		int m = row;
		int n = col;
		for(int i = 0 ; i < m ; i++) {
			System.out.println();
			for(int j = 0 ; j < n ; j++) {
				System.out.println(this.matrix[i][j] + " ");
			}
		}
		
	}
}

/*
    Matrix a = new Matrix(10, 5)  // 10 rows, 5 columns
	a.input() 
	Matrix b = new Matrix(10, 5)  // 10 rows, 5 columns
	b.input()
    Matrix c1 = a.add(b)
    Matrix c2 = a.subtract(b)
    Matrix c3 = a.transpose()
    a.print()
*/
