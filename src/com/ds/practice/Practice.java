package com.ds.practice;

public class Practice {
	
	public String MajorElement (int [] arr) {
		int count = 1;
		int majorElement = arr[0];
		for(int i = 1 ; i < arr.length ; i++) {
			if(arr[i] == majorElement) {
				count++;
			}else {
				count--;
			}
			if(count == 0) {
				majorElement = arr[i];
				count++;
			}
		}
		int countOfMajorElement = 0;
		for(int i = 0 ; i < arr.length ; i++) {
			if(arr[i] == majorElement) countOfMajorElement++;
		}
		if(countOfMajorElement > arr.length/2) {
			return "Majority Element is : "+majorElement;
		}else
			return "No majority Elemt Found";
	}
	
	public static void main(String[] args) {
		int [] arr = {1,1,1,1,2,3,4};
		Practice pr = new Practice();
		System.out.println(pr.MajorElement(arr));
	}

}
