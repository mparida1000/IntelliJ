package com.scaler.prime;

public class Countofdivisors {
	
	public static int[] solve(int[] A) {
		int max = 0;
		int n = A.length;
		for(int i = 0 ; i < n ; i++) {
			if(A[i] > max) {
				max = A[i];
			}
		}
		int [] result = new int [max+1];
		int [] op = new int[n];
		result = buildSieveArray(result);
		for(int i = 0 ; i < n ; i++) {
			int countFactors = 1;
			int num = A[i];
			while(num > 1) {
				int f = result[num];
				int c = 0;
				while(num % f == 0) {
					num/=f;
					c++;
				}
				countFactors = countFactors*(c+1);
			}
			op[i] = countFactors;
		}
		return op;
    }
	
	public static int[] buildSieveArray(int [] arr) {
		arr[1] = 1;
		for(int i = 2 ; i <= arr.length-1 ; i++) {
			arr[i] = i;
		}
		for(int i = 2 ; i*i <= arr.length-1 ; i++) {
			if(arr[i] == i) {
				for(int j = i*i ; j <= arr.length-1 ; j+=i) {
					if(arr[j] == j) {
						arr[j] = i;
					}
				}
			}
		}
		return arr;
	}
	
	public static void main(String[] args) {
		int [] A = {2,3,4,5,50};
		int [] op = solve(A);
		for(int i : op) {
			System.out.print(i + " ");
		}
	}

}
