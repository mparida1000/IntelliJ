package com.scaler.bit;

public class SubarrayswithBitwiseOR1 {
	
	public static long solve(int A, int[] B) {
		int i = 0;
		long continuousZeros = 0;
		long totalZeroSubArr = 0;
		while(i < A) {
			if(B[i] == 0) {
				continuousZeros++;
			}else if(B[i] == 1){
				if(continuousZeros > 0) {
					totalZeroSubArr+=continuousZeros*(continuousZeros+1)/2;
				}
				continuousZeros = 0;
			}
			i++;
		}
		if(continuousZeros > 0) {
			totalZeroSubArr+=continuousZeros*(continuousZeros+1)/2;
		}
		return ((long)A*((long)A+1)/2) - totalZeroSubArr;
	}
	
	public static void main(String[] args) {
		int [] B = {1,1,0,1,0,0};
		System.out.println(solve(6, B));
	}

}
