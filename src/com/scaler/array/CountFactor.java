package com.scaler.array;

public class CountFactor {
	
	public static void main(String[] args) {
		System.out.println(solve(10));
	}
	
	public static int solve(int A) {
        int count = 0;
        if(A==1)return 0;
        for(int i = 3 ; i<=A ;i++){
            if(isPrime(i)) count++;
        }
        return count+1;
    }
	static boolean isEven(int i) {
		return i%2==0;
	}
    public static boolean isPrime(int i){
        for(int j = 2; j*j <= i; j++){
            if(i%j == 0) return false;
        }
        return true;
    }

}
