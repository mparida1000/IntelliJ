package com.scaler.string;

import java.util.Arrays;

public class Changecharacter {
	
	public static int solve(String A, int B) {
		int [] arr = new int[26];
		int n = A.length();
		if(n==1) return 1;
		for(int i = 0 ; i < n ; i++) {
			char c = A.charAt(i);
			arr[c-97] = arr[c-97] + 1;
		}
		int c = B;
		Arrays.sort(arr);
		int uniq_cnt = 0;
		int totalUniqChars = 0;
		for(int i = 0 ; i < 26 ; i++) {
			System.out.print(arr[i] + " ");
			if(arr[i] > 0)totalUniqChars++;
			if(arr[i] > 0 && arr[i] <= c) {
				totalUniqChars--;
				c-=arr[i];
			}
			
		}
		return totalUniqChars;
    }
	
	public static void main(String[] args) {
		int i = solve("a", 1);
		System.out.println();
		System.out.println(i);
	}

}
