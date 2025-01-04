package com.scaler.array;

public class RotateArray {
	public static int[] solve(int[] A, int B) {
        int n = A.length;
        int k = B % n;
        reverseArray(A, 0, n-k-1);
        reverseArray(A, n-k, n-1);
        reverseArray(A, 0, n-1);
        return A;
    }
	public static void reverseArray(int[] A, int B, int C) {
        //if(B > A.length-1 || C > A.length-1) return A;
        int i = B, j = C;
        while(i < j){
            swap(i, j, A);
            i++;
            j--;
        }
    }
	
	static void swap(int i, int j, int [] A){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
	
	public static void main(String[] args) {
        int [] A = {1,2,3,4,5,6,7};		A = solve(A, 3);
		for(int i = 0 ; i <A.length ; i++) {
			System.out.print(A[i] + " ");
		}
	}

}
