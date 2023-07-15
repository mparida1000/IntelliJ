package com.ds;

public class BinarySearch {
	
	int mid = 0;
	int high = 0;
	int low = 0;
	
	private int binarySearchIterativeGetIndex(int[] arr, int value) {
		
		high = arr.length - 1;
		while(low <= high) {
			mid = (low + high) /2;
			if(arr[mid] == value) return mid;
			if(arr[mid] < value) {
				low = mid + 1;
			}else {
				high = mid-1;
			}
			
		}
		if(low > high) return -1;
		return -1;
		
	}
	
	private int binarySearchRecursiveGetIndex(int[] arr, int value, int l, int h) {
		if(l > h) return -1;
		mid = (l + h)/2;
		if(arr[mid] == value) {
			return mid;
		}
		if(arr[mid] > value)
			return binarySearchRecursiveGetIndex(arr, value, l, mid-1);
			return binarySearchRecursiveGetIndex(arr, value, mid+1, h);
	}
	
	private int binarySearchInfiniteArray(int[] arr, int value) {
		low = 0; high =1;
		while(arr[high] < value) {
			low = high;
			high = 2*high;
		}
		return binarySearchRecursiveGetIndex(arr, value, low, high);
		
	}
	
	private int binarySearchSortedRotatedArray(int[] arr, int value) {
		low = 0; high = arr.length-1;
		while(low <= high) {
			mid = (low + high)/2;
			if(arr[mid] == value) return mid;
			if(arr[mid] > arr[low]) {
				if(value >= arr[low] && value < arr[mid]) {
					high = mid-1;
				}else {
					low = mid+1;
				}
				
			}else {
				if(value > arr[mid] && value <= arr[high]) {
					low = mid+1;
				}else {
					high=mid-1;
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int[] arr = {-9,-8,-1,0,3,8,67,68,70,89,110};
		BinarySearch bs = new BinarySearch();
		int index = bs.binarySearchIterativeGetIndex(arr, 110);
		System.out.println(index);
		int index1 = bs.binarySearchRecursiveGetIndex(arr, 68, 0, arr.length-1);
		System.out.println(index1);
		
		//Infinite array
		int[] arr1 = {-9,-8,-1,0,3,8,67,68,70,89,110,111,112,113,115,118,123,124,125,145,146};
		//int index2 = bs.binarySearchInfiniteArray(arr1, 145);
		//System.out.println(index2);
		
		int[] arr2 = {20,30,40,50,2,4,5,8,10};
		int index3 = bs.binarySearchSortedRotatedArray(arr2, 10);
		System.out.println(index3);


	}

}
