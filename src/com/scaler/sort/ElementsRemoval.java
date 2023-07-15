package com.scaler.sort;

import java.util.ArrayList;
import java.util.Collections;

public class ElementsRemoval {
	
	public static int solve(ArrayList<Integer> A) {
        Collections.sort(A);
        int totalSum = 0;
        for(Integer i : A) {
        	totalSum+=i;
        }
        int cost = 0;
        for(int i = A.size()-1 ; i >= 0 ; i--) {
        	cost+=totalSum;
        	totalSum-=A.get(i);
        }
        return cost;
    }
	
	public static void main(String[] args) {
		ArrayList<Integer> A = new ArrayList<>();
		A.add(1);
		A.add(3);
		A.add(6);
		A.add(2);
		System.out.println(solve( A));
	}

}
