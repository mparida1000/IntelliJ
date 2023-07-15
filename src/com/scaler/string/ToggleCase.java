package com.scaler.string;

public class ToggleCase {
	
	public String solve(String A) {
        StringBuffer sb = new StringBuffer();
        int n = A.length();
        for(int  i = 0 ; i < n ; i++){
            char c = A.charAt(i);
            if(c >= 65 && c <= 90){
            	c = (char)(c + 32);
            	sb.append(c);
            }else if(c >= 97 && c <= 122){
            	c = (char)(c - 32);
            	sb.append(c);
            }
        }
        return sb.toString();
    }

}
