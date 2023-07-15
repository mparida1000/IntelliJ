package com.scaler.stack;
/**
 * @author mp1863
 *Problem Description
Given an array of integers A.

value of a array = max(array) - min(array).

Calculate and return the sum of values of all possible subarrays of A modulo 109+7.
Problem Constraints
1 <= |A| <= 100000
1 <= A[i] <= 1000000

Input Format
The first and only argument given is the integer array A.

Output Format
Return the sum of values of all possible subarrays of A modulo 109+7.
 */

import java.util.Stack;
/*
 * Given an array of integers A.
value of a array = max(array) - min(array).
Calculate and return the sum of values of all possible subarrays of A modulo 109+7.
 */
public class MAXandMIN {
	public static int solve(int[] A) {
		int n = A.length;
		if(n == 1) return A[0];
		int [] ngl = new int[n];
		int [] ngr = new int[n];
		int [] nsl = new int[n];
		int [] nsr = new int[n];
		ngl = nearestGreaterLeft(A);
		ngr = nearestGreaterRight(A);
		nsl = nearestSmallerLeft(A);
		nsr = nearestSmallerRight(A);
		
		int mod = 1000000007;
		 long totalMax = 0l, totalMin = 0l;

	        for(int i = 0; i < n; i++){
	            //number of subarrays where A[i] will be maximum
	            long maxSubarrayCount = 1l*(ngr[i] - i)*(i - ngl[i]);
	            long currentMaxContri = (A[i]*maxSubarrayCount) % mod;

	            //number of subarrays where A[i] will be minimum
	            long minSubarrayCount = 1l*(nsr[i] - i)*(i - nsl[i]);
	            long currentMinContri = (A[i]*minSubarrayCount) % mod;

	            //generating total max contribution and minimum contribution
	            totalMax = ((currentMaxContri % mod) + (totalMax % mod)) % mod;
	            totalMin = ((currentMinContri % mod) + (totalMin % mod)) % mod;
	        }

	        int sum = (int)(totalMax - totalMin) % mod;
	        return (sum + mod) % mod;
		
    }
	public static int[] nearestGreaterLeft(int[] A) {
		Stack<Integer> stack = new Stack<>();
		int [] op = new int[A.length];
		for(int i = 0 ; i < A.length ; i++) {
			while(!stack.isEmpty() && A[stack.peek()] <= A[i]) {
				stack.pop();
			}
			if(stack.isEmpty()) {
				op[i] = -1;
			}else {
				op[i] = stack.peek();
			}
			stack.push(i);
		}
		return op;
    }
	
	public static int[] nearestGreaterRight(int[] A) {
		Stack<Integer> stack = new Stack<>();
		int [] op = new int[A.length];
		for(int i = A.length-1 ; i >= 0 ; i--) {
			while(!stack.isEmpty() && A[stack.peek()] <= A[i]) {
				stack.pop();
			}
			if(stack.isEmpty()) {
				op[i] = A.length;
			}else {
				op[i] = stack.peek();
			}
			stack.push(i);
		}
		return op;
    }
	
	public static int[] nearestSmallerLeft(int[] A) {
		Stack<Integer> stack = new Stack<>();
		int [] op = new int[A.length];
		for(int i = 0 ; i < A.length ; i++) {
			while(!stack.isEmpty() && A[stack.peek()] >= A[i]) {
				stack.pop();
			}
			if(stack.isEmpty()) {
				op[i] = -1;
			}else {
				op[i] = stack.peek();
			}
			stack.push(i);
		}
		return op;
    }
	
	public static int[] nearestSmallerRight(int[] A) {
		Stack<Integer> stack = new Stack<>();
		int [] op = new int[A.length];
		for(int i = A.length-1 ; i >= 0 ; i--) {
			while(!stack.isEmpty() && A[stack.peek()] >= A[i]) {
				stack.pop();
			}
			if(stack.isEmpty()) {
				op[i] = A.length;
			}else {
				op[i] = stack.peek();
			}
			stack.push(i);
		}
		return op;
    }
	
	public static void main(String[] args) {
		int [] A = {2,6,3,5,4,7};
		System.out.println(solve(A));
		//solve(A);
	}
	
	 static void print(int [] arr) {
		for(int i = 0 ; i < arr.length ; i++) {
			System.out.print(arr[i] + " ");
		}
	}

}
