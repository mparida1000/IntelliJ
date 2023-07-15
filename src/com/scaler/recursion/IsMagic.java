package com.scaler.recursion;

public class IsMagic {
	
	public static int solve(int A) {
		return isMagic(A);
    }
	
	public static int isMagic(int n) {
		if(n == 1) return 1;
		if(n > 1 && n <=9) return 0;
		int sum = 0;
		int i = n;
		while(i > 0) {
			sum+=i%10;
			i/=10;
		}
		return isMagic(sum);
	}
	
	public static void main(String[] args) {
		
		System.out.println(solve(1291));
		
	}

}
