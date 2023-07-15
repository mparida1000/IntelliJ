package com.scaler.map;

//Java program to find number of subarrays
//with sum exactly equal to k.
import java.util.HashMap;
import java.util.Map;

public class GfG {
	static int findSubarraySum(int arr[], int n, int sum)
	{

		HashMap<Integer, Integer> prevSum = new HashMap<>();
		prevSum.put(0,1);
		int res = 0;
		int currSum = 0;

		for (int i = 0; i < n; i++) {
			currSum += arr[i];
			int removeSum=currSum-sum;
			if (prevSum.containsKey(removeSum))
				res += prevSum.get(removeSum);
			prevSum.put(currSum,prevSum.getOrDefault(currSum,0)+1);
		}

		return res;
	}

	public static void main(String[] args)
	{

		int arr[] = { -14, -9, 12, 7, -12, -4, 7, 25, -5, 48, 33, -49, 36, -31, -48, 36, 36, 17, 13, -47, -39, 37, -20, 35, 38, 26, -40, -43, 36, -48, -33, -30, 6, -28, 11, 27, 22, 18, -21, -11, -50, 34, -21, 44, -25, 17, 34, -12, 14, 26, 30 };
		int sum = 16;
		int n = arr.length;
		System.out.println(findSubarraySum(arr, n, sum));
	}
}


