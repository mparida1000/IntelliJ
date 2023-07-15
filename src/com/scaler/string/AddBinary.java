package com.scaler.string;

public class AddBinary {
	
	public static String addBinary(String A, String B) {
		String A_ = reverse(A);
		String B_ = reverse(B);
		char car = '0';
		StringBuilder sb = new StringBuilder();
		int len = A.length() > B.length() ? A.length() : B.length();
		for(int i = 0 ; i < len ; i++) {
			String sum = addSum(i<A_.length() ? A_.charAt(i) : '0', i<B_.length() ? B_.charAt(i) : '0', car);
			car = getCarry(i<A_.length() ? A_.charAt(i) : '0', i<B_.length() ? B_.charAt(i) : '0', car);
			sb.append(sum);
		}
		
		return (car == '1'? reverse(sb.append("1").toString()) : reverse(sb.toString()));
    }
	
	public static String reverse(String s) {
		StringBuffer sb = new StringBuffer();
        int n = s.length();
        for(int i = n-1 ; i >=0 ; i--){
            sb.append(s.charAt(i));
        }
        return sb.toString();
	}
	public static String addSum(char a, char b, char c) {
		int r = Integer.parseInt(String.valueOf(a)) + Integer.parseInt(String.valueOf(b)) + Integer.parseInt(String.valueOf(c));
		return ((r == 0 || r == 2) ? "0" : "1");
	}
	public static char getCarry(char a, char b, char c) {
		int r = Integer.parseInt(String.valueOf(a)) + Integer.parseInt(String.valueOf(b)) + Integer.parseInt(String.valueOf(c));
		return ((r == 0 || r == 1) ? '0' : '1');
	}
	
	public static void main(String[] args) {
		System.out.println(addBinary("100001110000", "1111111111111100000000"));
	}

}
