package com.scaler.array;

public class CountIncreasingTriplets {
	
	public static int solve(int[] A) {
        int n = A.length;
        int count = 0;
        for(int i = 1 ; i < n-1 ; i++) {
        	int L = 0;
        	int R = 0;
        	for(int j = i-1; j >= 0 ; j--) {
        		if(A[j] < A[i]) L++;
        	}
        	for(int j = i+1; j < n ; j++) {
        		if(A[j] > A[i]) R++;
        	}
        	count = count + L*R;
        }
        return count;
    }
	
	public static void main(String[] args) {
		int [] A = {1, 2, 3, 4};
		System.out.println(solve(A));
	}

}
