package com.scaler.sort;

public class SubArrayWithGivenSum {
	
	public static void main(String[] args) {
		int a[] = {42, 9, 38, 36, 48, 33, 36, 50, 38, 8, 13, 37, 33, 38, 17, 25, 50, 50, 41, 29, 34, 18, 16, 6, 49, 16, 21, 29, 41, 7, 37, 14, 5, 30, 35, 26, 38, 35, 9, 36, 34, 39, 9, 4, 41, 40, 3, 50, 27, 17, 14, 5, 31, 42, 5, 39, 38, 38, 47, 24, 41, 5, 50, 9, 29, 14, 19, 27, 6, 23, 17, 1, 22, 38, 35, 6, 35, 41, 34, 21, 30, 45, 48, 45, 37};
		System.out.println(getSubArray(a,100));
	}
	
	static String getSubArray(int[] a, int num) {
		int sum = a[0];
		int firstIndex = 0;
		for(int i = 1 ; i < a.length ; i++) {
			sum+=a[i];
			while(sum > num) {
				sum = sum-a[firstIndex];
				firstIndex++;
			}
			if(sum == num) {
				return String.valueOf(firstIndex)+String.valueOf(i);
			}
		}
		return "Not Found";
	}

}
