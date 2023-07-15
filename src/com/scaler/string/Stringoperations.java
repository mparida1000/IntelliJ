package com.scaler.string;

public class Stringoperations {
	public static String solve(String A) {
		String vowel = "aeiou";
		String s = A.concat(A);
		int n = s.length();
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < n ; i++) {
			char c = s.charAt(i);
			if(c >= 65 && c <= 90)continue;
			if(vowel.contains(String.valueOf(c))) {
				sb.append('#');
			}else sb.append(c);
		}
		return sb.toString();
    }

	public static void main(String[] args) {
		System.out.println(solve("AbcaZeoB"));
	}
}
