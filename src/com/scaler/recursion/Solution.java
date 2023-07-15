package com.scaler.recursion;

public class Solution {
	 public static int solve(int A, long B) {
	        if(A == 0||B == 0){
	            return 0;
	        }
	        int val = solve(A-1,B/2);
	        if(B%2 == 0){
	            return val;
	        }
	        else{
	            return 1-val;
	        }
	    }
	 
	 public static void main(String[] args) {
		System.out.println(solve(5, 5));
	}

}
