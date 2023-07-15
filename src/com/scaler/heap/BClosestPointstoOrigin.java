package com.scaler.heap;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * Problem Description
We have a list A of points (x, y) on the plane. Find the B closest points to the origin (0, 0).
Here, the distance between two points on a plane is the Euclidean distance.
You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in.)
NOTE: Euclidean distance between two points P1(x1, y1) and P2(x2, y2) is sqrt( (x1-x2)2 + (y1-y2)2 ).
 */
public class BClosestPointstoOrigin {
	public int[][] solve(int[][] A, int K) {
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
	    for (int[] p : A) {
	        pq.offer(p);
	        if (pq.size() > K) {
	            pq.poll();
	        }
	    }
	    int[][] res = new int[K][2];
	    System.out.println(K);
	    while (K > 0) {
	    	K--;
	        res[K] = pq.poll();
	    }
	    return res;
    }
	
	public static void main(String[] args) {
		int [][] A = {
				{1,1},
				{2,-2},
				{3,3},
				{4,4},
				{5,5}
		};
		BClosestPointstoOrigin bc = new BClosestPointstoOrigin();
		int [][] op = bc.solve(A, 3);
		for(int i = 0 ; i < op.length ; i++) {
			System.out.print(op[i][0] + ", "+op[i][1]);
			System.out.println();
		}
	}
}
