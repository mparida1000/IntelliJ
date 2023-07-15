package com.ds.sort;

public class ArrayMaxRangeSum {//KADANE'S ALGO
	static int[] arr = {5,3,4,-2,-2,1,-6,2,5,-4,-8,-1,2,1,5};
	public static void main(String[] args) {
		System.out.println(maxRangeSum());
	}
	static int maxRangeSum() {
		int currSum = 0;
		int maxSum = 0;
		for(int i = 0 ; i < arr.length ; i++) {
			currSum = currSum + arr[i];
			if(currSum > maxSum) {
				maxSum = currSum;
			}
			if(currSum < 0) {
				currSum = 0;
			}
		}
		
		return maxSum;
	}

}
