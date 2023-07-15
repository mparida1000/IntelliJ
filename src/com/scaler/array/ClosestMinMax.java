package com.scaler.array;

public class ClosestMinMax {
	public static int solve(int[] A) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int n = A.length;
        for(int i = 0 ; i < n ; i++){
            if(A[i] < min) min = A[i];
            if(A[i] > max) max = A[i];
        }
        if(min == max) return 1;
        int minDist = Integer.MAX_VALUE;
        int maxIndex = 0;
        int minIndex = 0;
        for(int j = n-1 ; j >=0 ; j--){
            if(A[j] == min) {
            	minIndex = j;
            	if(minIndex < maxIndex && Math.abs(minIndex - maxIndex )< minDist) minDist = Math.abs(minIndex - maxIndex);
            }
            if(A[j] == max) {
            	maxIndex = j;
            	if(maxIndex < minIndex && Math.abs(minIndex - maxIndex )< minDist) minDist = Math.abs(minIndex - maxIndex);
            }
        }
        return minDist+1;
    }
	
	public static void main(String[] args) {
		int A[] = {613, 116, 420, 672, 426};
		System.out.println(solve(A));
	}

}
