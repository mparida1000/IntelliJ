package com.scaler.modulo;

public class PrimeModuloInverse {
	
	static int m = 1000000007;
	public static int solve(int A, int B) {
        long r = powMod(A, B-2);
        return (int)r%B;
    }
	
	public static int powMod(int a, int n) {
		if(n == 0) return 1;
		long p = powMod(a, n/2);
		if(n % 2 == 0) return (int)(p*p)%m;
		else 
			return (int)((p*p)%m * a%m)%m;
		
	}
	
	public static void main(String[] args) {
		System.out.println(solve(5582,7));
	}

}
