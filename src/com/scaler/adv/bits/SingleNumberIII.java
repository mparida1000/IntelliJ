package com.scaler.adv.bits;

import java.util.ArrayList;
import java.util.Arrays;

public class SingleNumberIII {
	public static ArrayList<Integer> solve(ArrayList<Integer> A) {
		int XOR = 0;
		for(Integer i : A) {
			XOR^=i;
		}
		int XOR1 = 0, XOR2 = 0, index = 0;
		for(int i = 0 ; i <=31 ; i ++) {
			if(checkBit(XOR, i)) {
				index = i;
				break;
			}
		}
		for(Integer i : A) {
			if(checkBit(i, index)) {
				XOR1^=i;
			}else {
				XOR2^=i;
			}
		}
		ArrayList<Integer> rs = new ArrayList<>();
		rs.add(XOR1);rs.add(XOR2);
		return rs;
    }
	
	public static boolean checkBit(int n , int i) {
		if (((n >> i) & 1) == 1) return true;
		else return false;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<>(Arrays.asList(1,1,5,4,4,7,7,6));
		ArrayList<Integer> res = new ArrayList<>();
		res = solve(al);
		for(Integer i : res) {
			System.out.println(i);
		}
		System.out.println(8^9);
		System.out.println(19683%11);
	}

}
