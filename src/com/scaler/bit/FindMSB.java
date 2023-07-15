package com.scaler.bit;

public class FindMSB {
	
	public static void main(String[] args) {
		int n = 1025;
		int cnt = 0;
		while(n > 0) {
			cnt++;
			n = n>>1;
		}
		System.out.println(1<<cnt-1);
	}

}
