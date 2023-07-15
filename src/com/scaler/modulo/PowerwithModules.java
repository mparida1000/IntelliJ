package com.scaler.modulo;

import java.math.BigInteger;

public class PowerwithModules {
	
	public static int solve(int A, int B, int C) {
		BigInteger  a = BigInteger.valueOf(A);
		BigInteger b = BigInteger.valueOf(B);
		BigInteger c = BigInteger.valueOf(C);
		BigInteger modPow = a.modPow(b, c);
        System.out.println(modPow);
        return modPow.intValue();
    }
	
	public static void main(String[] args) {
		System.out.println(solve(136,173,93360));
	}

}
