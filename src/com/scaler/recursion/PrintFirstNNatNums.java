package com.scaler.recursion;

public class PrintFirstNNatNums {
	
	public static void sum(int A) {
		
		if(A ==1){
	        System.out.print(1 + " ");
	        return;
	        }

	        sum(A-1);
	        System.out.print(A + " ");
		
	}
	
	public static void main(String[] args) {
		sum(9);
	}

}
