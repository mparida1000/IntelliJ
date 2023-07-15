package com.scaler.array;

public class MinimumSwaps {

	public static int solve(int[] A, int B) {
        int n = A.length;
        int k = 0;
        for(int i = 0 ; i < n ; i++){
            if(A[i] <= B){
                k++;
            }
        }
        if(k == 0 || k == 1 || k == n){
            return 0;
        }

        int ng = 0;
        for(int i = 0 ; i < k ; i++){
            if(A[i] <=B){
                ng++;
            }
        }
        int min = k-ng;
        int s = 1, e = k;
        while(e < n){
            if(A[s-1] <= B)ng--;
            if(A[e] <= B)ng++;
            min = Math.min(min, k-ng);
            s++;
            e++;
        }
        return min; 
    }
	
	public static void main(String[] args) {
		int [] A = {1, 12, 10, 3, 14, 10, 5};
		System.out.println(solve(A, 8));
	}
}
