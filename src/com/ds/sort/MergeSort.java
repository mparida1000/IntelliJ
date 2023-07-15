package com.ds.sort;

// Time  - O(nlogn)
public class MergeSort { 
	
	static int a[] = {9,4,7,6,3,1,5};
	static int b[] = new int[a.length];
	
	static void mergeSort(int[] a, int l, int r) {
		if(l<r) {
			int mid = (l+r)/2;
			mergeSort(a, l, mid);
			mergeSort(a, mid+1, r);
			merge(a,l,mid,r);
		}
		
	}
	
	static void merge(int[] a, int l, int mid, int r) {
		
		int i = l;
		int j = mid+1;
		int k = l;
		while(i<=mid && j<=r) {
			if(a[i] < a[j]) {
				b[k] =a[i];
				i++;
			}else {
				b[k] = a[j];
				j++;
			}
			k++;
		}
		if(i>mid) {
			while(j<=r) {
				b[k] = a[j];
				k++;j++;
			}
		}else {
			while(i<=mid) {
				b[k] = a[i];
				k++;
				i++;
			}
		}
		for(k=l;k<=r;k++) {
			a[k]=b[k];
		}
	}
	public static void main(String[] args) {
		
		mergeSort(a, 0, a.length-1);
		for(int i = 0 ; i <= a.length-1 ; i++) {
			System.out.print(a[i] + " ");
		}
	}

}
