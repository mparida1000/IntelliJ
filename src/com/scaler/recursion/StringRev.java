package com.scaler.recursion;

import java.util.Scanner;

public class StringRev {
	static Scanner sc = null;
    public static void main(String[] args) {

        // YOUR CODE GOES HERE
        // Please take input and print output to standard input/output (stdin/stdout)
        // DO NOT USE ARGUMENTS FOR INPUTS
        // E.g. 'Scanner' for input & 'System.out' for output

    	sc = new Scanner(System.in);
        String s = sc.next();
        StringBuilder sb = new StringBuilder(s);
        String revStr = reverse(sb, 0, s.length()-1);
        System.out.println(revStr); 
    }

    public static String reverse(StringBuilder sb, int i,  int j){
    	if(i > j) {
    		return sb.toString();
    	}
    	String l = sb.substring(i, i+1);
    	sb.replace(i, i+1, sb.substring(j, j+1));
    	sb.replace(j, j+1, l);
        return reverse(sb, i+1, j-1);
    }

}
