package com.scaler.dp;
/*
 * Problem Description
Given a number A, return number of ways you can draw A chords in a circle with 2 x A points such that no 2 chords intersect.

Two ways are different if there exists a chord which is present in one way and not in other.
Return the answer modulo 109 + 7.
 */
public class IntersectingChordsCircle {

	public int chordCnt(int A) {
	    int MOD = 1000000007;
	    int[] dp = new int[A + 1];
	    dp[0] = 1;
	    for (int i = 1; i <= A; i++) {
	        for (int j = 0; j < i; j++) {
	            dp[i] = (dp[i] + (dp[j] * dp[i - j - 1]) % MOD) % MOD;
	        }
	    }
	    return dp[A];
	}
	public static void main(String[] args) {
		System.out.println(new IntersectingChordsCircle().chordCnt(3));
	}
}
