package com.scaler.array;

import java.util.ArrayList;
import java.util.List;

public class RangeSumQuery {

	public static int[] solve(int[] A, int[][] B){
        int m = B.length;
        int sum = 0;
        List<Integer> al = new ArrayList<Integer>();
        for(int i = 0 ; i < m ; i++){
        	sum = 0;
            int start = B[i][0];
            int end = B[i][1];
            for(int j=start-1 ; j<end ; j++){
                sum+=A[j];
            }
            al.add(sum);
        }
        int[] arr = al.stream().mapToInt(i -> i).toArray();
        return arr;
    }
	
	public static void main(String[] args) {
		
		int [] A = {6, 3, 3, 6};
		int [][] B = {
				{1, 4},
				{1, 3}
		};
		
		int[] arr = solve(A,B);
		for(int i = 0 ; i < arr.length ; i++){
			System.err.println(arr[i]);
		}
		
	}
}
