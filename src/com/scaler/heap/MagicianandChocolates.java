package com.scaler.heap;

import java.util.Collections;
import java.util.PriorityQueue;

/*
 * Given N bags, each bag contains Bi chocolates. There is a kid and a magician.
In a unit of time, the kid can choose any bag i, and eat Bi chocolates from it, 
then the magician will fill the ith bag with floor(Bi/2) chocolates.
Find the maximum number of chocolates that the kid can eat in A units of time.
NOTE:
floor() function returns the largest integer less than or equal to a given number.
Return your answer modulo 109+7
 */
public class MagicianandChocolates {
	public int nchoc(int A, int[] B) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for(int i = 0 ; i < B.length ; i++) {
			pq.add(B[i]);
		}
		int m = 1000000007;
		long sumTotal = 0;
		for(int i = 0 ; i < A ; i++) {
			int top = pq.poll();
			sumTotal = (sumTotal%m + top%m)%m; 
			pq.add(top/2);
		}
		
		return (int)(sumTotal%m);
    }
	public static void main(String[] args) {
		//System.out.println(Integer.MAX_VALUE);
		//int [] B = {2, 4, 6, 8, 10, 22, 12, 13, 28, 18};
		//884848962
		int [] B = {2147468674,2147469859,2147464180,2147481363,2147482021,2147470896,2147463891,2147474518,2147465858,2147470389};
		MagicianandChocolates mc = new MagicianandChocolates();
		System.out.println(mc.nchoc(6, B));
	}
}
