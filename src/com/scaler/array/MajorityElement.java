package com.scaler.array;

public class MajorityElement {
	
	public static int majorityElement(final int[] A) {
		int n = A.length;
		if (n == 1) return A[0];
		int count = 1;
		int majorElement = A[0];
		for(int i = 1 ; i < n ; i++) {
			if(A[i] == majorElement) {
				count++;
			}else if(count > 0) {
				count--;
			}else {
				majorElement = A[i];
				count++;
			}
		}
		if(count > 0) {
			int majCnt = 0;
			for(int i = 0 ; i < n ; i++) {
				if(A[i] == majorElement) {
					majCnt++;
				}
			}
			if(majCnt > n/2) return majorElement;
		}
		return -1;
    }
	
	public static void main(String[] args) {
		int [] A = {1,1,2};
		System.out.println(majorityElement(A));
	}

}
