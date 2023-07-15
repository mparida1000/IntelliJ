package com.scaler.string;

public class ReversetheString {
	
	public static String solve(String A) {
		int n = A.length();
		StringBuilder sb = new StringBuilder();
		String s = "";
		for(int i = 0 ; i < n ; i++) {
			if(A.charAt(i) != ' ') {
				char c = A.charAt(i);
				sb.append(c);
			}else {
				s = sb.append(' ').toString() + s;
				sb = new StringBuilder();
			}
		}
		s = s + sb.toString();
		return s.trim();
        
    }
	
	public static void main(String[] args) {
		String s = "the sky is blue";
		System.out.println(solve(s));
	}

}
