package com.scaler.map;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    public int solve(int[] A, int B) {
        int n = A.length;
        Map<Integer,Integer> map = new HashMap();
        int count = 0;
        int sum = 0;
        map.put(0,1);
        for(int i = 0 ; i < n ; i++){
            sum+=A[i];
            if(map.containsKey(sum - B)){
                count+= map.get(sum - B);
            }
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return count;
    }

    public static void main(String[] args) {
        int [] A = {1,0,1};
        System.out.println(new SubarraySumEqualsK().solve(A, 1));
    }
}
