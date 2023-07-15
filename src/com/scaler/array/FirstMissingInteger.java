package com.scaler.array;

import java.util.ArrayList;

public class FirstMissingInteger {

	public static int firstMissingPositive(int[] A) {
        int n = A.length;
        for(int i = 0 ; i < n ; i++){
            while(A[i] > 0 && A[i] <= n && A[i] != i+1){
                int x = A[i];
                if(x == A[x-1])break;
                swap(A, i, x-1);
            }
        }
        for(int i = 0 ; i < n ; i++){
            if(A[i] != i+1) return (i+1);
        }
        return n+1;
    }
	public static void swap (int [] A, int i , int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
	
	public static void main(String[] args) {
		int  [] A = {-8, -9, 6, 3, 4, 1, 2};
		int a = firstMissingPositive(A);
		System.out.println(a);
		//6 2 4 3 0 5
		//400 399 398 397 56 45
	}
}
