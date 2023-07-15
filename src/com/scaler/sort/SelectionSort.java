package com.scaler.sort;

public class SelectionSort {
	
	public static void main(String[] args) {
		int [] a = {1,2,3,4,9,6};
		int n = a.length;
		for(int i = 0 ; i< n-1 ; i++) {
			int min = i;
			for(int j = i+1 ; j < n ; j++) {
				if(a[j] < a[min]) {
					min = j;
				}
			}
			if(min != i) {
				swap(a, i, min);
			}
		}
		for(int i = 0 ; i< n ; i++) {
			System.out.print(a[i]+ " ");
		}
	}
	static void swap(int [] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

}
