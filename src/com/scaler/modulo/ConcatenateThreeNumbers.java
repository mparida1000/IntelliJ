package com.scaler.modulo;

import java.util.ArrayList;
import java.util.Collections;

public class ConcatenateThreeNumbers {
	
	public static int solve(int A, int B, int C) {
        ArrayList<Integer> al = new ArrayList<>();
        al.add(A);al.add(B);al.add(C);
        Collections.sort(al);
        String s = "";
        for(Integer i : al) {
        	s+=i;
        }
        return Integer.parseInt(s);
    }
	
	public static void main(String[] args) {
		
		System.out.println(solve(28, 97, 32));
		
	}

}
