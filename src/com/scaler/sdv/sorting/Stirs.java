package com.scaler.sdv.sorting;

public class Stirs {
	public static int solve(int A) {
		if(A==0)
		return 0;
		        long l=0,r=A,ans=0;
		       
		        while(l<=r){
		           long mid=(l+r)/2;

		            if((mid*(mid+1))/2<=(long)A){
		                ans=mid;
		                l=mid+1;
		            }
		            else
		            r=mid-1;
		        }
		        return (int)ans;

		    }
	public static void main(String[] args) {
		System.out.println(solve(10));
	}

}
