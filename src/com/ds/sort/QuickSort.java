package com.ds.sort;

//Time
//Best case - O(nlogn)
//Worst case - N2
public class QuickSort {
	static int[] a = {4,6,2,5,7,9,1,3};
	public static int partition(int l, int h) {
		int pivot = a[l];
		int i = l, j = h;
		while(i < j) {
			while(a[i] <= pivot) i++;
			while(a[j] > pivot) j--;
			if(i < j) {
				swap(a, i, j);
			}
		}
		swap(a, j , l);
		return j;
	}
	
	public static void quickSort(int l, int h) {
		if(l < h) {
			int pivot = partition(l, h);
			quickSort(l, pivot-1);
			quickSort(pivot+1, h);
		}
		
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[j];
		arr[j] = a[i];
		a[i] = temp;
	}
	
	public static void main(String[] args) {
		quickSort(0, a.length-1);
		for(int i = 0 ; i <= a.length-1 ; i++) {
			System.out.print(a[i] + " ");
		}
	}

}
