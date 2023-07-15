package com.scaler.bit;


public class DivideIntegers {
	public static int divide(int A, int B) {
        boolean isAnyNumNeg  = (A >= 0) == (B >= 0) ? true : false;;
        int ans = 0;
        A = Math.abs(A);
        B = Math.abs(B);
        for(int i = 31 ; i >= 0 ; i--) {
        	if((B<<i) <= A) {
        		A-=(B<<i);
        		ans+=1<<i;
        	}
        }
        if(isAnyNumNeg) return (-1*ans);
        		else return ans;
    }
	
	public static void main(String[] args) {
		System.out.println(divide(96, 7));
	}

}
