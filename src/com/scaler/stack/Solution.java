package com.scaler.stack;

import java.util.Arrays;
import java.util.Stack;

public class Solution {
    int mod = 1000000007;
    public int solve(int[] A) {
        int n = A.length;
        if(n==1) return A[0];

        int nsl[] = nearestSmallerLeft(A);
        int nsr[] = nearestSmallerRight(A);
        int ngl[] = nearestGreaterLeft(A);
        int ngr[] = nearestGreaterRight(A);
        
        System.out.println("ngl:");
		print(ngl);
		System.out.println("\nngr:");
		print(ngr);
		System.out.println("\nnsl:");
		print(nsl);
		System.out.println("\nnsr:");
		print(nsr);
		System.out.println();

        long totalMax = 0l, totalMin = 0l;

        for(int i = 0; i < n; i++){
            //number of subarrays where A[i] will be maximum
            long maxSubarrayCount = 1l*(ngr[i] - i)*(i - ngl[i]);
            long currentMaxContri = (A[i]*maxSubarrayCount) % mod;

            //number of subarrays where A[i] will be minimum
            long minSubarrayCount = 1l*(nsr[i] - i)*(i - nsl[i]);
            long currentMinContri = (A[i]*minSubarrayCount) % mod;

            //generating total max contribution and minimum contribution
            totalMax = ((currentMaxContri % mod) + (totalMax % mod)) % mod;
            totalMin = ((currentMinContri % mod) + (totalMin % mod)) % mod;
        }

        int sum = (int)(totalMax - totalMin) % mod;
        return (sum + mod) % mod;
    }

    public int[] nearestSmallerLeft(int[] a){
        Stack<Integer> cl = new Stack<>();
        int[] ans = new int[a.length];
        Arrays.fill(ans, -1);

        for(int i=0; i<a.length; i++){
            while(!cl.isEmpty() && a[cl.peek()]>=a[i]){
                cl.pop();
            }
            if(!cl.isEmpty()){
                ans[i] = cl.peek();
            }
            cl.push(i);
        }

        return ans;
    }

    public int[] nearestSmallerRight(int[] a){
        int n = a.length;
        Stack<Integer> cl = new Stack<>();
        int[] ans = new int[a.length];
        Arrays.fill(ans, n);

        for(int i=a.length-1; i>=0; i--){
            while(!cl.isEmpty() && a[cl.peek()]>=a[i]){
                cl.pop();
            }
            if(!cl.isEmpty()){
                ans[i] = cl.peek();
            }
            cl.push(i);
        }

        return ans;
    }

    public int[] nearestGreaterLeft(int[] a){
        Stack<Integer> cl = new Stack<>();
        int[] ans = new int[a.length];
        Arrays.fill(ans, -1);

        for(int i=0; i<a.length; i++){
            while(!cl.isEmpty() && a[cl.peek()]<=a[i]){
                cl.pop();
            }
            if(!cl.isEmpty()){
                ans[i] = cl.peek();
            }
            cl.push(i);
        }

        return ans;
    }

    public int[] nearestGreaterRight(int[] a){
        int n = a.length;
        Stack<Integer> cl = new Stack<>();
        int[] ans = new int[a.length];
        Arrays.fill(ans, n);

        for(int i=a.length-1; i>=0; i--){
            while(!cl.isEmpty() && a[cl.peek()]<=a[i]){
                cl.pop();
            }
            if(!cl.isEmpty()){
                ans[i] = cl.peek();
            }
            cl.push(i);
        }

        return ans;
    }
    public static void main(String[] args) {
		int [] A = {2,6,3,5,4,7};
		Solution s = new Solution();
		System.out.println(s.solve(A));
		//solve(A);
	}
    
    static void print(int [] arr) {
		for(int i = 0 ; i < arr.length ; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}	
