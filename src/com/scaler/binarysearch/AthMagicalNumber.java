package com.scaler.binarysearch;
/**
 * Problem Description
You are given three positive integers, A, B, and C.
Any positive integer is magical if divisible by either B or C.
Return the Ath smallest magical number. Since the answer may be very large, return modulo 109 + 7.
Note: Ensure to prevent integer overflow while calculating.
Problem Constraints
1 <= A <= 109
2 <= B, C <= 40000
 * @author mp1863
 *
 */
public class AthMagicalNumber {
	
	public static int solve(int A, int B, int C) {
		int  m = 1000000007;
		long a = A, b = B, c =C;
		long l = Math.min(C, B), h = a*Math.min(C, B);
		long ans = 0;
		long gcd = gcd(b, c);
		long lcm = b*c/gcd(b, c);
		while(l <= h) {
			long mid = (l+h)/2;
			long p = (mid/b + mid/c) - mid/lcm;
			if(p < a) {
				l = mid+1;
			}else {
				ans = mid;
				h = mid-1;
			}
		}
		return (int)(ans%m);
    }

    public static long gcd(long B, long C){
        if(C == 0) return B;
        return gcd(C, B%C);
    }
    
    public static void main(String[] args) {
		System.out.println(solve(807414236, 3788,38141));
	}

}
