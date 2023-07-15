package com.scaler.dp;

public class WaysToSendSignal {
	int [][] dp;
	 public int solve(int A) {
		 dp = new int [A+1][2];
		 return ways(A, 0);
	 }
	 
	 int ways(int i, int digit) {
		 if(i == 0)
			 return 1;
		 if(dp[i][digit] != 0)
			 return dp[i][digit];
		 int ans = 0;
		 if(digit == 0) {
			 ans = ways(i-1, 0) + ways(i-1, 1);
		 }else {
			 ans = ways(i-1, 0);
		 }
		 dp[i][digit] = ans;
		 return dp[i][digit];
	 }
	 
	 public static void main(String[] args) {
		System.out.println(new WaysToSendSignal().solve(3));
	}

}
