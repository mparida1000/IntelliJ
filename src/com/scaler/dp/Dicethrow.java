package com.scaler.dp;
/**
 * You have n dice, and each die has k faces numbered from 1 to k.

Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) to roll the dice, so the sum of the 
face-up numbers equals target. 
Since the answer may be too large, return it modulo 109 + 7.
 * @author mp1863
 *
 */
public class Dicethrow {

	public int numRollsToTarget(int n, int m, int target) {
		int [][] dp = new int[n+1][target+1];
        return 0;
    }
	
	public static void main(String[] args) {
		System.out.println(new Dicethrow().numRollsToTarget(3, 6, 8));
	}
}
