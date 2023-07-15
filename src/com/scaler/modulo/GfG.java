package com.scaler.modulo;

import java.util.*;  
public class GfG { 
  
    // Function to calculate the sum 
    public static int sum(int a[], int n) 
    { 
        // Map to keep a count of occurrences 
        Map<Integer,Integer> cnt = new HashMap<Integer,Integer>();  
          
        // Traverse in the list from start to end 
        // number of times a[i] can be in a pair and 
        // to get the difference we subtract pre_sum 
        int ans = 0, pre_sum = 0; 
        for (int i = 0; i < n; i++) { 
            ans += (i * a[i]) - pre_sum; 
            pre_sum += a[i]; 
      
            // If the (a[i]-1) is present then subtract 
            // that value as f(a[i], a[i]-1) = 0 
            if (cnt.containsKey(a[i] - 1)) 
                ans -= cnt.get(a[i] - 1); 
  
            // If the (a[i]+1) is present then 
            // add that value as f(a[i], a[i]-1)=0 
            // here we add as a[i]-(a[i]-1)<0 which would  
            // have been added as negative sum, so we add  
            // to remove this pair from the sum value 
            if (cnt.containsKey(a[i] + 1)) 
                ans += cnt.get(a[i] + 1); 
  
            // keeping a counter for every element 
            if(cnt.containsKey(a[i])) { 
                cnt.put(a[i], cnt.get(a[i]) + 1); 
            } 
            else { 
                cnt.put(a[i], 1); 
            } 
        } 
        return ans; 
    } 
  
    // Driver code 
    public static void main(String args[]) 
    { 
        int a[] = { 1, 2, 3}; 
        int n = a.length; 
        System.out.println(sum(a, n)); 
    } 
} 
