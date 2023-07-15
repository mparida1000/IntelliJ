package com.scaler.array;

public class SearchIn2DSortedMatrix {
	
	 public static int solve(int[][] A, int B) {
		 int n = A.length;
		 int sum = Integer.MAX_VALUE;
		 for(int  i = 0 ; i < n ; i++) {
			 int j = getIndexAfterBinarySearch(A[i], B);
			 if(j != -1) {
				 int prod = (1009 * (i+1) + j+1);
				 if(prod < sum) {
					 sum = prod;
				 }
			 }
		 }
		 if(sum == Integer.MAX_VALUE) return -1;
		 return sum;
	 }
	 
	 public static int getIndexAfterBinarySearch(int arr[], int k) {
		 int high = arr.length-1;
		 int low = 0;
		 while(low <= high) {
			 int mid = (low+high)/2;
			 if(mid == 0 && arr[mid] == k)return 0;
			 if((arr[mid] == k && arr[mid-1] != k)) {
				 return mid;
			 }else if(k < arr[mid]) {
				 low = mid + 1;
			 }else {
				 high = mid-1; 
			 }
		 }
		 if (low > high) return -1;
		 return -1;
	 }
	 
	 public static void main(String[] args) {
		int [][] A = {
				{0}
		};
		System.out.println(solve(A, -200000));
	}

}
