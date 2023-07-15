package com.scaler.bit;

public class StrangeEquality {
	
	public static int solve(int A) {
		int X = 0;
		int i = 0;
		while(A > 0) {
			if((A&1) == 0) {
				X+=1<<i;
			}
			A>>=1;
			i++;
		}
		return X^1<<i;
    }
	
	public static void main(String[] args) {
		System.out.println(solve(19));
	}
	
}
