package com.scaler.recursion;

public class KthSymbol {
	
	public static int solve(int A, int B) {
		return kthElement( A,  B);
    }
	
	public static int kthElement(int n, int k) {
		if((n == 1 )) return 0;
		int mid = (1<<n-1)/2;
		if(k <= mid) {
			return kthElement(n-1, k);
		}else {
			return 1 ^ kthElement(n-1, k-mid);
		}
		
	}
	
	public static void main(String[] args) {
		
		System.out.println(solve(4, 5));
		//solve(5, 3);
		
	}

}
