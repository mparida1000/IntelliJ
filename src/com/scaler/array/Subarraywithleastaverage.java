package com.scaler.array;

public class Subarraywithleastaverage {
	public static int solve(int[] A, int B) {
        int n = A.length;
        int sum = 0;
        int finalIndex = 0;
        for(int i = 0 ; i <= B-1; i++){
            sum+=A[i]; 
        }
        int s = 1, e = B, minSum = sum;
        while(e < n){
            sum = sum - A[s-1]+A[e];
            if(sum < minSum){
                minSum = sum;
                finalIndex = s;
            }
            s++;
            e++;
        }
        return finalIndex;
    }
	
	public static void main(String[] args) {
		int [] A = {18, 11, 16, 19, 11, 9, 8, 15, 3, 10, 9, 20, 1, 19};
		System.out.println(solve(A,1));
	}

}
