package com.scaler.array;

public class SpecialIndex {
	
	public static int solve(int[] A) {
        int n = A.length;
        int [] pf_e = new int [n];
        int [] pf_o = new int [n];
        pf_e[0] = A[0];
		for(int i = 1 ; i < n ; i++) {
			if(i%2 == 0) {
				pf_e[i]+=pf_e[i-2]+A[i];
			}else {
				pf_e[i] = pf_e[i-1];
			}
		}
        pf_o[0] = A[0];
		pf_o[1] = A[1];
		for(int i = 2 ; i < n ; i++) {
			if(i%2 != 0) {
				pf_o[i]+=pf_o[i-2]+A[i];
			}else {
				pf_o[i] = pf_o[i-1];
			}
		}
		
		int count = 0;
		for(int i = 0 ; i < n ; i++) {
			int sum_e = 0, sum_o = 0;
			if(i == 0) {
				sum_e = pf_o[n-1] - pf_o[i];
				sum_o = pf_e[n-1] - pf_e[i];
			}else {
				sum_e = pf_e[i-1] + (pf_o[n-1] - pf_o[i]);
				sum_o = pf_o[i-1] + (pf_e[n-1] - pf_e[i]);
			}
			if(sum_e == sum_o) count++;
		}
		return count;
    }
	
	public static void main(String[] args) {
		int [] A = {4,3,2,7,6,-2};
		System.out.println(solve(A));
	}

}
