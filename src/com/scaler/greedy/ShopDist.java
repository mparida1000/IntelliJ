package com.scaler.greedy;

import java.util.HashMap;
import java.util.Map;

public class ShopDist {
	public int maxDistance(int[] A) {
		int n = A.length;
	    int maxDist = -1;
	    int firstColorIndex = -1;
	    
	    // iterate through the array and find the first index of each color
	    Map<Integer, Integer> colorIndices = new HashMap<>();
	    for (int i = 0; i < n; i++) {
	        int color = A[i];
	        if (!colorIndices.containsKey(color)) {
	            colorIndices.put(color, i);
	        }
	    }
	    
	    // iterate through the array again and calculate the maximum distance
	    for (int i = 0; i < n; i++) {
	        int color = A[i];
	        int firstIndex = colorIndices.get(color);
	        if (firstIndex == firstColorIndex) {
	            continue; // skip if this is the first occurrence of this color
	        }
	        int dist = Math.abs(i - firstIndex);
	        if (dist > maxDist) {
	            maxDist = dist;
	        }
	    }
	    
	    return maxDist == -1 ? -1 : maxDist;
	}
	
	public static void main(String[] args) {
		int [] A = {36, 26, 22, 24};
		System.out.println(new ShopDist().maxDistance(A));
	}
}
