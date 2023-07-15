package com.scaler.recursion;

import java.util.ArrayList;

public class Josephus {
	
	public static int solve(int A, int B) {
        ArrayList<Integer> al = new ArrayList();
        for(int i = 0 ; i < A ; i++) {
        	al.add(i+1);
        }
        return Josephus(al, 0, B);
    }

    public static int Josephus(ArrayList<Integer> al, int s, int k){
        if(al.size() == 1) return al.get(0);
        int killIndex = (s+k-1)%al.size();
        al.remove(killIndex);
        s = killIndex;
        return Josephus(al, s, k);
    }
    
    public static void main(String[] args) {
		System.out.println(solve(6, 5));
	}

}
