package com.scaler.dp;
/**
 * Problem Description
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message denoted by string A containing digits, determine the total number of ways to decode it modulo 109 + 7.
 * @author mp1863
 *
 */
public class WaysToDecode {

	public int numDecodings(String s) {
		if(s == null || s.length() == 0)   return 0;
	    int n = s.length();
	    int m = 1000000007;
	    int[] dp = new int[n + 1];
	    dp[0] = 1;
	    dp[1] = s.charAt(0) != '0' ? 1 : 0;
	    for (int i = 2; i <= n; i++) {
	        int first = Integer.valueOf(s.substring(i-1, i));
	        int second = Integer.valueOf(s.substring(i-2, i));
	        if(first >= 1 && first <= 9)     dp[i] += dp[i-1]%m;  
	        if(second >= 10 && second <= 26) dp[i] += dp[i-2]%m;
	    }
	    return dp[n]%m;
    }
	public static void main(String[] args) {
		String s = "1242";
		System.out.println(new WaysToDecode().numDecodings(s));
	}
}
