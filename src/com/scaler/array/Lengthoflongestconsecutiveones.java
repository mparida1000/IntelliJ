package com.scaler.array;

public class Lengthoflongestconsecutiveones {
	
	public static int solve(String A) {
		int n = A.length();
		if(n > 0 && !A.contains("1")) return 0;
		int ones = 0;
		for(int i = 0 ; i < n ; i++) {
			if(A.charAt(i) == '1') ones++;
		}
		int max_len = 0;
		for(int i = 0 ; i < n ; i++) {
			if(A.charAt(i) == '0') {
				int l = 0;
				int r = 0;
				for(int j = i-1 ; j >= 0 ; j--) {
					if(A.charAt(j) == '1') {
						l++;
					}else break;
				}
				for(int j = i+1 ; j < n ; j++) {
					if(A.charAt(j) == '1') {
						r++;
					}else break;
				}
				int tot_len = (l+r) < ones ? (l+r+1) : (l+r);
				max_len = Math.max(max_len, tot_len);
			}
		}
		if(max_len == 0) return n;
		return max_len;
    }
	
	public static void main(String[] args) {
		String A = "000000000000000";
		System.out.println(solve(A));
		
	}

}
