package com.scaler.bit;

public class Unsetxbitsfromright {
	public static long solve(long A, int B) {
        long n = B;
        long num = A;
        long zeroComplement = ~0;
        while(n > 0){
        	zeroComplement = zeroComplement<<1;
        	num = num & zeroComplement;
        	n--;
        }
        return num;
    }

	
	public static void main(String[] args) {
		
		System.out.println(solve(37,3));
		System.out.println((1<<1)&10);
	}

}
