package com.scaler.binarysearch;
/**
 * Problem Description
Given an integer A. Compute and return the square root of A.
If A is not a perfect square, return floor(sqrt(A)).
The value of A can cross the range of Integer.
NOTE: 
   Do not use the sqrt function from the standard library. 
   Users are expected to solve this in O(log(A)) time.
Problem Constraints
0 <= A <= 10^10
 * @author mp1863
 *
 */
public class SquareRootofInteger {
	
	public static int sqrt(int A) {
		long l = 1,h = A;
		long ans = 0;
        int m = 1000000007;
		while(l <= h) {
			long mid = (l+h)/2;
			if(mid*mid == (long)A) {
				return (int)mid%m;
			}else if(mid*mid > (long)A) {
				h = mid-1;
			}else {
				l = mid+1;
				ans = mid;
			}
			
		}
		return (int)ans%m;
    }
	
	public static void main(String[] args) {
		long s = System.currentTimeMillis();
		System.out.println(sqrt(50));
		long e = System.currentTimeMillis();
		System.out.println(e-s);
	}

}
