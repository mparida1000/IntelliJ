package com.scaler.gcd;

public class LargestCoprimeDivisor {
	
	public static int cpFact(int A, int B) {
		int gcd = 1;
		for(int  i = A ; i > 0 ; i/=gcd) {
			gcd = gcd(i, B);
			if(gcd == 1) return i;
		}
		return 1;
    }
	static int  gcd(int a, int b){
        if(b==0) return a;
        return gcd(b, a%b);
    }
	
	public static void main(String[] args) {
		System.out.println(cpFact(22, 38));
	}

}
