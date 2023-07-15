package com.scaler.dp;

public class LongestPalindrome {
	public static int longestPalindromeSubstring(String str) {
	    int n = str.length();
	    boolean[][] dp = new boolean[n][n];
	    int maxLength = 1;
	    
	    // All substrings of length 1 are palindromes
	    for (int i = 0; i < n; i++) {
	        dp[i][i] = true;
	    }
	    
	    // Check for substrings of length 2 and greater
	    for (int len = 1; len < n; len++) {
	        for (int i = 0, j = len; i < n - len; i++, j++) {
	            //int j = i + len - 1;
	            if (len == 1) {
	                dp[i][j] = (str.charAt(i) == str.charAt(j));
	            } else {
	                dp[i][j] = (str.charAt(i) == str.charAt(j)) && dp[i+1][j-1];
	            }
	            if (dp[i][j]) {
	                maxLength = Math.max(maxLength, j-i+1);
	            }
	        }
	    }
	    
	    return maxLength;
	}
	
	public static void main(String[] args) {
		System.out.println(new LongestPalindrome().longestPalindromeSubstring("KANAK"));
		//aycxaccba
	}
}
