package com.scaler.array;

public class PickFromBothSidesArray {
	
	public static int solve(int[] A, int B) {
        int n = A.length;
        int leftMax = 0;
        for(int i = 0 ; i < B ; i++){
            leftMax+=A[i];
        }
        int rightMax = 0;
        int max = leftMax;
        for(int j = n-1; j >= n-B ; j--){
            rightMax+=A[j];
            leftMax = leftMax- A[j-(n-B)];
            max = Math.max(max, rightMax +leftMax);
        }
        return max;
    }
	
	public static void main(String[] args) {
		int [] A = {-1, 6, 2, 9, 1};
		for(int i = 0 ; i < A.length ; i++){
			for(int j=i ; j<A.length;j++){
				System.out.print (A[j]);
			}
			System.out.print("\n");
		System.out.println(solve(A, 3));
	}
	}

}
