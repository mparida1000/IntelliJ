package com.scaler.dp;

import java.util.Arrays;

public class CuttingRod {

	public int maxRodValue(int[] prices) {
	    // Create a memoization table of size n+1 and initialize all values to -1
		int n = prices.length;
	    int[] dp = new int[n+1];
	    Arrays.fill(dp, -1);
	    
	    // Call the helper function with the rod length, prices array, and memoization table
	    return helper(n, prices, dp);
	}

	private int helper(int n, int[] prices, int[] dp) {
	    // If n is 0, return 0 since there are no pieces to sell
	    if (n == 0) {
	        return 0;
	    }
	    
	    // Check if the value has already been computed in the memoization table
	    if (dp[n] != -1) {
	        return dp[n];
	    }
	    
	    // Loop through all possible cuts of the rod and compute the maximum value that can be obtained from each cut
	    int maxVal = 0;
	    for (int i = 1; i <= n; i++) {
	        // Compute the value that can be obtained from the current cut and add it to the price of the remaining rod
	        int val = prices[i-1] + helper(n-i, prices, dp);
	        // Take the maximum of all computed values
	        maxVal = Math.max(maxVal, val);
	    }
	    
	    // Store the maximum value in the memoization table
	    dp[n] = maxVal;
	    // Return the maximum value
	    return maxVal;
	}
	public static void main(String[] args) {
	    int[] prices = {1, 5, 8, 9, 10};
	    
	    // Create a new instance of the class and call the maxRodValue method
	    CuttingRod solution = new CuttingRod();
	    int maxVal = solution.maxRodValue(prices);
	    
	    // Print the maximum value
	    System.out.println("Maximum value that can be obtained by cutting up the rod: " + maxVal);
	}

}

/*
 To solve this problem using top-down approach in Java, 
 we can use memoization. We can create a helper function that takes the length of 
 the rod and the prices array, and returns the maximum value that can be obtained by 
 cutting up the rod and selling the pieces.

In this function, we can first check if the value has already been computed by checking if 
it exists in our memoization table. If it does, we can simply return the computed value.

If the value has not been computed, we can loop through all possible cuts of the rod and 
compute the maximum value that can be obtained from each cut. We can then take the maximum 
of these values and add it to the price of the current piece being considered. We can store 
this value in our memoization table and return it.
*/
