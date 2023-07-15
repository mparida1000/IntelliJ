package com.scaler.sort;

public class Water {
	
	static int[] arr = {3,0,2,4,1,1,0,2};
	public static void main(String[] args) {
		System.out.println(totalWaterCollected());
		
	}
	 static int totalWaterCollected() {
		int N = arr.length;
		int []leftMax = new int[N];
		int []rightMax = new int[N];
		leftMax[0] = arr[0];
		rightMax[N-1] = arr[N-1];
		for(int i=1; i<N ; i++) {
			leftMax[i] = Math.max(leftMax[i-1], arr[i]);
		}
		for(int j = N-2 ; j>=0 ; j--) {
			rightMax[j] = Math.max(rightMax[j+1], arr[j]);
		}
		int sum=0;
		for(int i = 0; i<N ; i++) {
			sum = sum + Math.min(leftMax[i], rightMax[i]) - arr[i];
		}
		return sum;
	}

}
