package com.scaler.recursion;

public class Test {
	
	public static void main(String[] args) {
		String s = "Scaler";
		StringBuilder sb = new StringBuilder(s);
		String l = sb.substring(s.length()-1, s.length());
		System.out.println(l);
		sb.replace(0, 1, l);
		System.out.println(sb.toString());
	}

}
