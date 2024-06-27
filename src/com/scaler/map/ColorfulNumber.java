package com.scaler.map;

import java.util.HashSet;
import java.util.Set;
/*
Problem Description
Given a number A, find if it is COLORFUL number or not.
If number A is a COLORFUL number return 1 else, return 0.
What is a COLORFUL Number:
A number can be broken into different consecutive sequence of digits.
The number 3245 can be broken into sequences like 3, 2, 4, 5, 32, 24, 45, 324, 245 and 3245.
This number is a COLORFUL number, since the product of every consecutive sequence of digits is different


Problem Constraints
1 <= A <= 2 * 109
 */
public class ColorfulNumber {
	
	public static int colorful(int A) {
		Set<Integer> numSet = new HashSet<>();
		numSet.add(A%10);
		int latestRem = A%10;
		int n = A/10;
		while(n > 0) {
			int rem = n%10;
			if(numSet.contains(rem))return 0;
			else numSet.add(rem);
			if(numSet.contains(rem*latestRem))return 0;
			else numSet.add(rem*latestRem);
			latestRem = rem;
			n/=10;
		}
		return 1;
    }
	
	public static void main(String[] args) {
		System.out.println(colorful(2634));
	}

}
