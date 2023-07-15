package com.scaler.string;

public class CountOccurrences {
	
	public static int solve(String A) {
		int n = A.length();
		String s = "bob";
		int i = 0, j = 2;
		int count = 0;
		while(j < n) {
			if(s.equalsIgnoreCase(A.substring(i, j+1)))count++;
			i++;
			j++;
		}
		return count;
    }
	
	public static void main(String[] args) {
		System.out.println(solve("abobc"));
	}

}
