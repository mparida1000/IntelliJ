package com.scaler.recursion;

public class ImplementPowerFunction {
	
	public static int pow(int A, int B, int C) {
        if(A == 0)return 0;
		if(B == 0) return 1;
        long p = pow(A, B/2, C);
        if(B % 2 == 0){
        	int r = (int)Math.floorMod(p*p, C);
            return r ;
        }else{
        	int r = (int)Math.floorMod(( (p*p)%C * A%C), C);
            return r ;
        }
    }
	
	public static void main(String[] args) {
		System.out.println(pow(-1, 1, 20));
		System.out.println(Math.floorMod(-1, 20));;
	}

}
