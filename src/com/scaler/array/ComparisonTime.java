package com.scaler.array;

public class ComparisonTime {
	
	public static void main(String[] args) {
		long start_time = System.currentTimeMillis();
		int count = 0;
		for(int i = 1 ; i <= 1000000000 ; i++) {
			if((i&1) == 0)count++;
		}
		System.out.println(count);
		long end_time = System.currentTimeMillis();
		long difference = end_time-start_time;
		System.out.println(difference);
	}

}
