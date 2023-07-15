package com.ds.sort;

public class Sorting {
	
	static int[] a = {4,6,2,5,7,9,1,3};
	
	public static void insertionSort() {
		for(int i = 1 ; i < a.length ; i++) {
			int temp = a[i];
			int j = i-1;
			while(j >= 0 && a[j] > temp) {
				a[j+1] = a[j];
				j--;
			}
			a[j+1] = temp;
		}
	}
	
	public static void main(String[] args) {
		insertionSort();
		for(int i = 0 ; i <= a.length-1 ; i++) {
			System.out.print(a[i] + " ");
		}
		
	}

}
