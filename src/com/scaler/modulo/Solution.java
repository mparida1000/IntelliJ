package com.scaler.modulo;

import java.util.HashMap;
import java.util.Map;

public class Solution {
	public static int solve(int[] A, int B) {
        Map<Integer,Integer> mp = new HashMap<>();
        long ans = 0;
        int mod = 1000000007;
        for(int i = 0;i<A.length;i++){
            int rem = A[i]%B;
            int complRem = B-rem;
            if(rem ==0 && mp.containsKey(0)){
                ans = (ans+mp.get(0))%mod;
                mp.put(0,mp.get(0)+1);
                continue;
            }
            if(mp.containsKey(complRem)){
                ans = (ans+mp.get(complRem))%mod;
            }
            mp.put(rem,mp.getOrDefault(rem,0)+1);
        }
        return (int)ans;
    }
	
	public static void main(String[] args) {
		int []  A = {4,7,6,5,5,3};
		System.out.println(solve(A, 3));
		
	}

}
