package com.scaler.array;

public class Maximumpositivity {
	
	 public static int[] solve(int[] A) {
	        int n = A.length;
	        int max_len = 0;
	        int min_index = n;
	        int end = 0;
	        int len = 0;
	        int latest_negetive_i = 0;
	        for(int i = 0 ; i < n ; i++) {
	        	if(A[i] < 0) {
	        		latest_negetive_i = i;
	        		end = i;
	        		len = 0;
	        		for(int j = i-1 ; j >=0 ; j--) {
	        			if(A[j] >= 0) {
	        				len++;
	        			}else{
	        				break;
	        			}
	        		}
	        	}
	        	if(len > max_len)min_index = end-len;
	    		max_len = Math.max(max_len, len);
	        }
	        if(n - 1 - latest_negetive_i > max_len) {
	        	max_len = (n-1) - latest_negetive_i;
	        	min_index = latest_negetive_i+1;
	        }
	        int [] res = new int[max_len];
	        System.arraycopy(A, min_index, res, 0, max_len);
	        return res;
	 }
	 
	 public static void main(String[] args) {
		 // {-4970494, -664439, -3649300, -3123623, -6310530, 5544106, -4558683, -2209137, -6016890, 112968};
		int [] A = {1, -9, 7, -6, 9, 6, 7, 8, 9, 3};
		int [] B = solve(A);
		for(int i = 0 ; i < B.length ; i++) {
			System.out.print(B[i]+" ");
		}
	}

}
