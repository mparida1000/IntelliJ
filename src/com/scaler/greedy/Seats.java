package com.scaler.greedy;

import java.util.ArrayList;
import java.util.Vector;

public class Seats {
	public int minMoves(String seats) {
		ArrayList<Integer> positions = new ArrayList<>();
		for(int i = 0 ; i < seats.length() ; i++) {
			if(seats.charAt(i) == 'x') {
				positions.add(i);
			}
		}
		if(positions.size() == 0) return 0;
		int medianIdx = (positions.size()-1)/2;
		int median = positions.get(medianIdx);
		int m = 1000000007;
		long hops = 0;
		for(int i = 0 ; i < positions.size() ; i++) {
			if(i != medianIdx) {
				/*abs(median - position[i) - abs(distance between median and index of the position)*/
				int contr = (Math.abs(median - positions.get(i))- Math.abs(medianIdx - i));
				hops = (hops%m + contr)%m;
			}
		}
		return (int)hops%m;
	}


public static void main(String[] args) {
	Seats s = new Seats();
	//System.out.println(s.minMoves("...x.x....xx..xxx"));//15
	System.out.println(s.minMoves(".....x..xxxxx.xxx.xxxxx.x.xx.x..x..xxxx..xxxxx..xxx....xxxxx.xxxxxx....xx...xx.....x...xxx...xxxx.xxx....xxx.xx.xxx.x.x.x...x.x..x.xx..xxx"));
}

}
