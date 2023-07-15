package com.scaler.prime;

import java.util.Arrays;

public class NumberOfOpenDoors {
	public static int solve(int A) {
		boolean [] sf = new boolean[A+1];
		Arrays.fill(sf, Boolean.TRUE);
		for(int i = 2 ; i*i <= A ; i++) {
			for(int j = i ; j <=A ; j+=i) {
				sf[j] = !sf[j];
			}
		}
		int countOpen = 0;
		for(int i = 1 ; i <=A ; i++) {
			if(sf[i]) {
				countOpen++;
			}
		}
		return countOpen;
    }
	
	public static void main(String[] args) {
		System.out.println(solve(9507565));
	}

}
