package com.scaler.prime;

import java.util.ArrayList;
import java.util.Arrays;

public class PrimeSum {
	
	public static ArrayList<Integer> primesum(int A) {
        ArrayList<Integer> countList = new ArrayList<Integer>();
        boolean prime[] = new boolean[A+1];
        Arrays.fill(prime, Boolean.TRUE);
        prime[0] = prime[1] = false;
        for(int i = 2; i*i <= A; i++){
            if(prime[i]){
                for(int j = i*i ; j <= A ; j+=i){
                    prime[j] = false;
                }
            }
        }
        for(int i = 2 ; i < A ; i++){
            if(prime[i]){
                if(prime[A-i]) {
                    countList.add(i);
                    countList.add(A-i);
                    break;
                }
            }
        }
        return countList;
    }
	
	public static void main(String[] args) {
		ArrayList<Integer> countList = primesum(184);
		System.out.println(countList.toString());
	}

}
