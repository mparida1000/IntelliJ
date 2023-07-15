package com.scaler.recursion;

public class SumNNatNums {
	
	public static int fact(int n) {
		if (n==0) {
			return 1;
		}
		return fact(n-1)*n;
	}
	
	public static void main(String[] args) {
		System.out.println(fact(4));
		System.out.println(null == null);
	}

}
