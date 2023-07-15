package com.scaler.dp;

import java.util.Arrays;
import java.util.Scanner;

public class FibonacciNumber {
	static int [] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		dp = new int[n+1];
		Arrays.fill(dp, -1);
		dp[0] = 0;
		dp[1] = 1;
		int fib = fibonacci(n);
		System.out.println(fib);
	}
	
	static int fibonacci(int n) {
		if(dp[n] != -1)
			return dp[n];
		
		dp[n] = fibonacci(n-1)+fibonacci(n-2);
		return dp[n];
	}
}
