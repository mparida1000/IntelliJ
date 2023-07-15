package com.scaler.sort;

public class ArrayMajorityElement {
	static int[] a = {1,2,3,1,2,1,4,5,1,1,5,1,1};
	public static void main(String[] args) {
		System.out.println(findMajorElementInArray());
	}
	
	//Moore's voting Algo
	static String findMajorElementInArray() {
		int majorIndex = 0;
		int count = 1;
		for(int i = 1 ; i < a.length ; i++) {
			if(a[i] == a[majorIndex]) {
				count++;
			}else {
				count--;
			}
			if(count == 0) {
				majorIndex = i;
				count = 1;
			}
		}
		int majorElement = a[majorIndex];
		int c = 0;
		for(int i = 0 ; i < a.length ; i++) {
			if(a[i] == majorElement) {
				c++;
			}
		}
		if(c > a.length/2) {
			return String.valueOf(majorElement);
		}else return "No Major Element Found";
	}
	

}
