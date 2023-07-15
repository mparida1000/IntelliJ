package com.scaler.sdv.sorting;

import java.util.HashMap;
import java.util.Map;

public class MaximumUnsortedSubarray {

	public static int[] subUnsort(int[] A) {
		int n = A.length;
		Map<Integer, Integer> map = new HashMap();
		int i = 0, j = n-1;
		while(i < n-1) {
			if(A[i] <= A[i+1]) {
				map.put(A[i], i);
				i++; 
			}else if(A[i] > A[i+1]){
				if(map.containsKey(A[i+1])) {
					map.put(A[i], i);
					i=map.get(A[i+1])+1;
					break;
				}
			}else{
				break;
			}
		}
		while(j > 0) {
			if(j!=i && A[j] <= A[i]) {
				if(A[j-1] < A[j])j--;
				break;
			}else {
				j--;
			}
		}
		int [] op;
		if(i == n-1 || j == 0) {
			op = new int[1];
			op[0] = -1;
			return op;
		}
		op = new int[2];
		op[0] = i;
		op[1] = j;
		return op;
    }
//	else if(A[j] == A[i] && i != j) {
//		break;
//	}
	public static void main(String[] args) {
		int [] A = {1, 2, 3, 5, 6, 13, 15, 16, 17, 13, 13, 15, 17, 17, 17, 17, 17, 19, 19};//6,11
		//int [] A = {1, 1, 2, 3, 3, 4, 8, 9, 11, 9, 11, 12, 12, 11, 9, 14, 19, 20, 20};//8,14
		//int [] A = {3, 3, 4, 5, 5, 9, 11, 13, 14, 15, 15, 16, 15, 20, 16}; //11,14
		
		//int [] A = {1, 1, 10, 10, 15, 10, 15, 10, 10, 15, 10, 15}; //4, 10
		//int [] A = {4, 15, 4, 4, 15, 18, 20};//1,3

		int op [] = subUnsort(A);
		for(int i : op) {
			System.out.print(i + " ");
		}
	}
	/*int n = A.length;
	int i = 0, j = n-1;
	int rightIndex = n-1, leftIndex = 0;
	int rightMinVal = A[n-1];
	int leftMaxVal = A[0];
	
	int [] op;
	while(i < j) {
		if(A[i] <= rightMinVal) {
			if(A[j] < rightMinVal)rightIndex = j;
			if(A[i] > leftMaxVal)leftIndex = i;
			rightMinVal = Math.min(A[j], rightMinVal);
			i++;
			j--;
		}else {
			break;
		}
	}
	if(j<=i) {
		op = new int[1];
		op[0] = -1;
		return op;
	}
	op = new int[2];
	op[0] = i;
	op[1] = rightIndex;
	return op;*/
}
