package com.scaler.array;

public class JosephusProblem {
	
	public static int solve(int A) {
        int d = A;
        int powOf2 = 1;
        while(d > 1){
            d/=2;
           powOf2*=2;
        }
        if(powOf2 == A) return 1;
        else return 2*(A - powOf2)+1;

    }
	
	public static void main(String[] args) {
		System.out.println(solve(3));
	}

}
