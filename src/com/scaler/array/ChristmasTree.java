package com.scaler.array;

public class ChristmasTree {
	public static int solve(int[] A, int[] B) {
        int n = A.length;
        int min_cost = Integer.MAX_VALUE;
        for(int i = 1 ; i < n-1 ; i++){
        	int cost = 0;
            int min_l = Integer.MAX_VALUE;
            int min_r = Integer.MAX_VALUE;
            
            for(int j = i-1 ; j >=0 ; j--){
                if(A[j] < A[i]){
                    min_l = Math.min(min_l , B[j]);
                }
            }
            
            for(int j = i+1; j < n ; j++){
                if(A[j] > A[i]){
                    min_r = Math.min(min_r , B[j]);
                }
            }
            
            cost =  (min_l != Integer.MAX_VALUE && min_r != Integer.MAX_VALUE ) ? min_l + min_r + B[i] : Integer.MAX_VALUE;
            min_cost = Math.min(min_cost, cost);
        }
        if(min_cost == Integer.MAX_VALUE) return -1;
        return min_cost;
    }
	public static void main(String[] args) {
		int [] A = {100, 101, 100};
		int [] B = {2, 4, 5};
		System.out.println(solve(A, B));
	}

}
