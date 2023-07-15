package com.scaler.array;

public class RotateMatrix {
	
	public static void solve(int[][] A) {
        int n = A.length;
        for(int j = 0 ; j < n ; j++){
            for(int i = j+1; i < n ; i++){
                swap(A, i,j);
            }
        }
        for(int i = 0 ; i < n ; i++){
            int start = 0, end = n-1;
            while(start < end){
                swap1D(A, i, start, end);
                start++;
                end--;
            }
        }
    }

    public static void swap(int [][] A, int i, int j){
        int temp = A[i][j];
        A[i][j] = A[j][i];
        A[j][i] = temp;
    }
    public static void swap1D(int [][] A, int i, int start, int end){
        int temp = A[i][start];
        A[i][start] = A[i][end];
        A[i][end] = temp;
    }
    
    public static void main(String[] args) {
		int [][] A = {
				{1, 2},
				{3, 4}
		};
		solve(A);
	}
    

}
