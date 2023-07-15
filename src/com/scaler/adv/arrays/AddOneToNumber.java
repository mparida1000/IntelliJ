package com.scaler.adv.arrays;

public class AddOneToNumber {
	public static int[] plusOne(int[] A) {
		int n = A.length;
		int forwardZeros = 0;
		int carry = 1;
		for(int i = n-1; i >=0 ; i--) {
			if(A[i] != 0 && forwardZeros > 0)forwardZeros--;
			int sum = A[i]+carry;
			if(sum < 10) {
				A[i] = sum;
				carry = 0;
			}else {
				carry = sum/10;
				A[i] = 0;
			}
			if(A[i] == 0)forwardZeros++;
		}
		if(A[0] == 0 && carry == 0) {
			int [] op = new int[A.length-forwardZeros];
			for(int i = 0 ; i < op.length ; i++) {
				op[i] = A[i+forwardZeros];
			}
			return op;
		}else if(carry == 1){
			int [] op = new int[A.length+1];
			op[0] = carry;
			for(int i = 1 ; i < op.length ; i++) {
				op[i] = A[i-1];
				return op;
			}
		}
		return A;
    }
	
	public static void main(String[] args) {
		int [] A = {5};
		int [] op = plusOne(A);
		for(int i = 0 ; i < op.length ; i++) {
			System.out.print(op[i] + " ");
		}
	}

}
