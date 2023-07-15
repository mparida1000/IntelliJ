package com.scaler.math;

public class PalindromeNum {
	
	public static void main(String[] args) {
		int num = 22322;
		int o = num;
		int rev = 0;
		while(num > 0) {
			int rem = num%10;
			num = num/10;
			rev  = rev*10+rem;
		}
		System.out.println(rev);
		if(o == rev) {
			
			System.out.println("Palindrome");
		}else
			System.out.println("Not Palindrome");
	}

}
