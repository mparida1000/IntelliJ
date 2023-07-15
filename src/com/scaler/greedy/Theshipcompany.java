package com.scaler.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Problem Description
The local ship renting service has a special rate plan:

It is up to a passenger to choose a ship.
If the chosen ship has X (X > 0) vacant places at the given moment, then the ticket for such a ship costs X.
The passengers buy tickets in turn, the first person in the queue goes first, then the second one, and so on up to A-th person.

You need to tell the maximum and the minimum money that the ship company can earn if all A passengers buy tickets.
First argument is a integer A denoting the number of passengers in the queue.
Second arugument is a integer B deonting the number of ships.
Third argument is an integer array C of size B where C[i] denotes the number of empty seats 
in the i-th ship before the ticket office starts selling tickets.
 * @author mp1863
 *
 */
public class Theshipcompany {

	public int[] solve(int A, int B, int[] C) {
		int n = C.length;
		Arrays.sort(C);
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		int maxMoney = 0;
		int minMoney = 0;
		int reqSumForTotPassen = 0;
		for(int i = 0 ; i < n ; i++) {
			maxHeap.offer(C[i]);
			if(reqSumForTotPassen < A) {
				minHeap.offer(C[i]);
				reqSumForTotPassen+=C[i];
			}			
		}
		int k = 0;
		while(k < A) {
			int meanRoot = minHeap.poll();
			minMoney+=meanRoot;
			if(meanRoot > 1)minHeap.offer(meanRoot - 1);
			int maxRoot = maxHeap.poll();
			maxMoney+=maxRoot;
			if(maxRoot > 1)maxHeap.offer(maxRoot - 1);
			k++;
		}
		int [] op = {maxMoney, minMoney};
		return op;
		
    }
	
	public static void main(String[] args) {
		int [] C = {4,2,7,9,5,10};
		Theshipcompany ts = new Theshipcompany();
		int [] op = ts.solve(8, 6, C);
		for(int i : op) {
			System.out.print(i + " ");
		}
	}
}
