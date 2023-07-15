package com.scaler.sdv.sorting;

public class SearchforaRange {
	public static int[] searchRange(final int[] A, int B) {		
		int [] op = new int[2];
		int minAns = binarySearch(A, true, B);
		int maxAns = binarySearch(A, false, B);
		op[0] = minAns < Integer.MAX_VALUE ? minAns : -1;
		op[1] = maxAns > Integer.MIN_VALUE ? maxAns : -1;
		return op;
		
    }
	
	public static int binarySearch(int [] A, boolean left, int B) {
		int l = 0, h = A.length-1;
		int minAns = Integer.MAX_VALUE; int maxAns = Integer.MIN_VALUE;
		while(l <= h) {
			int mid = (l+h)/2;
			if(left && A[mid] == B) {
					minAns = mid;
					h = mid -1;
			}else if(!left && A[mid] == B) {
					maxAns = mid;
					l = mid + 1;
			}else if(A[mid] > B) {
				h = mid - 1;
			}else {
				l = mid +1;
			}
		}
		return left ? minAns : maxAns;
	}
	
	public static void main(String[] args) {
		int [] A = {5, 6, 7, 7, 7, 7, 9, 11, 17};
		int op [] = searchRange(A, 7);
		for(int i : op) {
			System.out.print(i+ " ");
		}
	}

}
