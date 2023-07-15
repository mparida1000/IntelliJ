package com.scaler.stack;

import java.util.Stack;

/*
 * 
Problem Description
Given an array of integers A.
A represents a histogram i.e A[i] denotes the height of the ith histogram's bar. Width of each bar is 1.
Find the area of the largest rectangle formed by the histogram.
Problem Constraints
1 <= |A| <= 100000
1 <= A[i] <= 1000000000
 */

public class LargestRectangleinHistogram {
	public static int largestRectangleArea(int[] A) {
		int n = A.length;
		int [] nsl = new int [n];
		int [] nsr = new int [n];
		int p1 = 0;
		int p2 = 0;
		int ans = Integer.MIN_VALUE;
		nsl = prevSmaller(A);
		nsr = aftreSmaller(A);
		int l = 0;
		for(int i = 0 ; i < n ; i++) {
			if(nsl[i] == -1) {
				p1 = 0;
			}else {
				p1 = nsl[i] + 1;
			}
			if(nsr[i] == -1) {
				p2 = n - 1;
			}else {
				p2 = nsr[i] - 1;
			}
			l = p2-p1+1;
			ans = Math.max(ans, l*A[i]);
		}
		return ans;
    }
	public static int[] prevSmaller(int[] A) {
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
	public static int[] aftreSmaller(int[] A) {
		Stack<Integer> stack = new Stack<>();
		int [] op = new int[A.length];
		for(int i = A.length - 1 ; i >= 0 ; i--) {
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
	
	public static void main(String[] args) {
		int [] A = {6, 2, 5, 4, 5, 1, 6};
		System.out.println(largestRectangleArea(A));
	}

}
