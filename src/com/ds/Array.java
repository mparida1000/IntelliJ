package com.ds;

import java.util.ArrayList;
import java.util.Arrays;

public class Array { 
	
	static int[] arr = {3,7,4,8,0,1,2}; //=> 
	
	static void rotateArrayByNLeft(int k) {
		int rotated = 1;
		while(rotated <= k) {
			int first = arr[0];
			for(int i=0 ; i<arr.length-1 ; i++) {
				arr[i] = arr[i+1];
			}
			arr[arr.length-1]=first;
			rotated++;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(3%8);
		rotateArrayByNLeft(2);
		for(int i = 0 ; i <= arr.length-1 ; i++) {
			System.out.print(arr[i] + " ");
		}
		int  towDA [][] = new int [2][2];
		for(int i = 0 ; i < towDA.length ; i++) {
			System.out.println(towDA[i][0]);
			System.out.println(towDA[i][1]);

		}
		
		ArrayList<String> al = new ArrayList<>(Arrays.asList("apple", "banana", "mango", "orange", "papaya", "plum", "carrot","grape"));
		al.subList(5, al.size()).clear();
		System.out.println(al.size());
	}

}
