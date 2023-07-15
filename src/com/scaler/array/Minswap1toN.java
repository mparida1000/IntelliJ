package com.scaler.array;

public class Minswap1toN {
	    public static int solve(int[] A) {
	        int count=0;
	        int N=A.length;
	        int s=0;
	        int _ite = 0;
	        while(s<N){
	        	_ite++;
	            if(A[s]!=s){
	                count++;
	                int temp=A[A[s]];
	                A[A[s]]=A[s];
	                A[s]=temp;
	            }
	            else s++;
	        }
	        System.out.println(_ite);
	        return count;
	    }
	
	public static void main(String[] args) {
		int [] A = {2,5,4,0,1,3};
		System.out.println(solve(A));
	}


}
