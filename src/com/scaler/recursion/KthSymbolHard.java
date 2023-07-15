package com.scaler.recursion;

public class KthSymbolHard {

	public static int solve(int A, long B) {
		return kthElement(A, B+1);
    }
	
	public static int kthElement(int n, long k) {
		if(n == 0  || k == 0 ) return 0;
		long mid = (1 << n-1)/2;
		if(k <=mid) {
			return kthElement(n-1, k);
		}else return 1 ^ kthElement(n-1, k-mid);
	}
	
	public static void main(String[] args) {
		System.out.println(kthElement(5, 5));
	}
}
