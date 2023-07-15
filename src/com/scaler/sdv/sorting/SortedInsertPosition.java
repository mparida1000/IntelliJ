package com.scaler.sdv.sorting;

public class SortedInsertPosition {
	
	public static int searchInsert(int[] A, int B) {
		int l = 0, h = A.length-1;
		int possibleInsertIdx = -1;
		while(l <= h) {
			int mid = (l+h)/2;
			if(A[mid] == B)return mid;
			if(A[mid] > B) {
				if(mid == l && B < A[l]) return l;
				else if(B < A[mid] && B > A[mid-1]) {
					possibleInsertIdx = mid;
					return possibleInsertIdx;
				}
				h = mid-1;
			}else {
				if(mid == h && B > A[mid]) return h+1;
				else if(B > A[mid] && B < A[mid+1]) {
					possibleInsertIdx = mid+1;
					return possibleInsertIdx;
				}
				l = mid+1;
			}
		}  
		return possibleInsertIdx;
    }
	
	public static void main(String[] args) {
		//int [] A = {5,6,9,11,18};
		int [] A = {1, 3, 5, 6};
		//int [] A = {17, 30, 32, 69, 94, 96, 106, 118, 127, 159, 169, 170, 178, 183, 209, 238, 242, 247, 253, 261, 265, 279, 288, 302, 305, 316, 352, 357, 374, 376, 392, 402, 410, 421, 439, 442, 444, 446, 454, 458, 464, 467, 468, 498, 500, 513, 523, 541, 545, 556, 575, 608, 616, 629, 631, 635, 669, 674, 682, 686, 693, 695, 719, 733, 754, 755, 756, 778, 802, 822, 824, 828, 835, 847, 848, 862, 864, 878, 883, 885, 904, 908, 928, 934};
		System.out.println(searchInsert(A, 4));
	}

}
