package com.scaler.array;

public class Subarraywithgivensumandlength {
	
	public static int solve(int[] A, int B, int C) {
        int n = A.length;
        int sum = 0;
        for(int i = 0 ; i < B ; i++){
            sum+=A[i];
        }
        if(sum == C)return 1;
        int s = 1, e = B;
        while(e < n){
            sum = sum - A[s-1] + A[e];
            if(sum == C) return 1;
            s++;
            e++;
        }
        return 0;
    }
	
	public static void main(String[] args) {
		int [] A = {43, 38, 72, 11, 43, 47};
		System.out.println(solve(A, 4, 43));
	}

}
