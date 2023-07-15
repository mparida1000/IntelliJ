package com.scaler.map;

import java.util.HashSet;
import java.util.Set;

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
