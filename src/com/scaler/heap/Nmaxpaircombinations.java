package com.scaler.heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Problem Description
Given two integers arrays, A and B, of size N each.

Find the maximum N elements from the sum combinations (Ai + Bj) formed from elements in arrays A and B.
 * @author mp1863
 *
 */
public class Nmaxpaircombinations {

	public int[] solve(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int n = A.length;
        for(int i = n - 1 ; i >=0 ; i--) {
        	int a = A[i] + B[i];
        	int b = 0;
        	int c = 0;
        	if(i > 0) {
	        	b = A[i] + B[i - 1];
	        	c = A[i - 1] + B [i];
        	}
        	if(pq.size() < n) {
        		pq.offer(a);
        		pq.offer(b);
        		pq.offer(c);
        	}else
        		break;
        }
        int [] op = new int[n];
        int k = 0;
        while(k < n) {
        	op[k] = pq.poll();
        	k++;
        }
        return op;
    }
	
	public static void main(String[] args) {
		int [] A = {2, 4, 1, 1};
		int [] B = {-2, -3, 2, 4};
		Nmaxpaircombinations nm = new Nmaxpaircombinations();
		int [] op = nm.solve(A, B);
		for(int i : op) {
			System.out.print(i + " ");
		}
	}
}
