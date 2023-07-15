package com.scaler.array;

public class SingleNumberII {

	 public static int singleNumber(final int[] A) {
		 int n = A.length;
		 int ans = 0;
		 for(int i = 0 ; i < 32 ; i++) {
			 int sum  = 0;
			 for(int j = 0 ; j < n ; j++) {
				 if(checkBit(A[j], i)) {
					 sum++;
				 }
			 }
				 if(sum % 3 == 1) {
					 ans = ans + (1 << i);
				 }
			 
		 }
		 return ans;
	 }
	 
	 public static void main(String[] args) {
		int [] A = {1,2,1,1,3,3,3};
		System.out.println(singleNumber(A));
	}
	 public static boolean checkBit(int n, int i) {
		 if (((n>>i) & 1) == 1) return true;
		 else return false;
	 }

}
