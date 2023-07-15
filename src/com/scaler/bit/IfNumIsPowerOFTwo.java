package com.scaler.bit;

public class IfNumIsPowerOFTwo {
	
	public static boolean isPowOfTow(int num) {
		return (num != 0) && ((num & (num - 1)) == 0);
		
	}
	
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < 100000 ; i++) {
			if(isPowOfTow(i)) {
				sb.append("-").append(i);
			}
		}
		System.out.println(sb.toString());
	}

}
