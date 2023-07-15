package com.scaler.array;

public class MaxSubArray {
	public static int maxSubarray(int A, int B, int[] C) {
        
        int maxSum =0;
        for(int i = 1; i < A ; i++){
            C[i] = C[i-1] + C[i];
        }
        for(int s = 0 ; s < A ; s++){
            int sum = 0;
            for(int e = s ; e < A ; e++){
                if(s == 0){
                    sum+=C[e];
                }else{
                    sum+=C[e] - C[s-1];
                }
                if(sum <= B) maxSum = Math.max(sum, maxSum);
            }
        }
        return maxSum;
    }
	
	public static void main(String[] args) {
		int [] C = {7, 1, 8, 5, 8, 5, 3, 3, 5};
		System.out.println(maxSubarray(9, 78, C));
	}

}
