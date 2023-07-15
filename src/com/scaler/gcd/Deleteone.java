package com.scaler.gcd;

public class Deleteone {
	public static int solve(int[] A) {
        int n = A.length;
        int [] pf = new int [n];
        int [] sf = new int [n];
        pf[0] = A[0];
        sf[n-1] = A[n-1];
        for(int i = 1 ; i < n ; i++) {
        	pf[i] = gcdR(pf[i-1], A[i]);
        }
        for(int i = n-2; i >=0 ; i--) {
        	sf[i] = gcdR(sf[i+1], A[i]);
        }
        int ans = Math.max(sf[1], pf[n-2]);
        for(int i = 1 ; i < n-1; i ++) {
        	int currGcd = gcdR(pf[i-1],  sf[i+1]);
        	ans = Math.max(ans, currGcd);
        }
        return ans;
    }

    public static int gcdR(int a, int b){
        if(b == 0) return a;
        if(a == 0) return b;
        return gcdR(b, a%b);
    }
    
    public static void main(String[] args) {
		int [] A = {4,8,3,12,16};
		System.out.println(solve(A));
	}

}
