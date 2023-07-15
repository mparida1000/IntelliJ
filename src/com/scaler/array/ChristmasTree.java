package com.scaler.array;

/**
 * Problem Description
 * You are given an array A consisting of heights of Christmas trees and an array B of the same size consisting of the cost of each of the trees
 * (Bi is the cost of tree Ai, where 1 ≤ i ≤ size(A)), and you are supposed to choose 3 trees (let's say, indices p, q, and r),
 * such that Ap < Aq < Ar, where p < q < r.
 * The cost of these trees is Bp + Bq + Br.
 * * You are to choose 3 trees such that their total cost is minimum. Return that cost.
 * * If it is not possible to choose 3 such trees return -1.
 */
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
