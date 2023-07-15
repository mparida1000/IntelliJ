package com.scaler.greedy;
/*
 * Problem Description
N children are standing in a line. Each child is assigned a rating value.
You are giving candies to these children subjected to the following requirements:
Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum number of candies you must give?
 */
import java.util.Arrays;

public class DistributeCandy {

	public int candy(int[] A) {
		int n = A.length;
		int [] arr = new int[n];
		Arrays.fill(arr, 1);
		int sum = 0;
		for(int i = 1 ; i < n ; i++) {
			if(A[i] > A[i-1] ) {
				arr[i]=arr[i-1]+1;
			}
		}
		for(int i = n - 2 ; i >= 0 ; i--) {
			if(A[i] > A[i+1] && arr[i] <= arr[i+1]) {
				arr[i]=arr[i+1]+1;
			}
		}
		for(int i = 0 ; i < n ; i++) {
			sum+=arr[i];
		}
		return sum;
    }
	public static void main(String[] args) {
		int [] A = {11,15,1,7,13,8,20,17,4,14};//{9,14,18,7,11,3,17,20,10,1};
		DistributeCandy can = new DistributeCandy();
		System.out.println(can.candy(A));
	}
}
