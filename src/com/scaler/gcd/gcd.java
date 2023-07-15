package com.scaler.gcd;

public class gcd {
	
	static int  gcd(int a, int b){
        if(b==0) return a;
        return gcd(b, a%b);
    }
	
	public static void main(String[] args) {
		System.out.println(gcd(1876, 7430));
		System.out.println(411753753%7430);
		System.out.println(411753753%1876);
	}

}
