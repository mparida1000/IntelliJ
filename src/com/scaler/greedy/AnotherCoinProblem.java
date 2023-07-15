package com.scaler.greedy;
/**
 * Problem Description
The monetary system in DarkLand is really simple and systematic. The locals-only use coins. The coins come in different values. The values used are:

 1, 5, 25, 125, 625, 3125, 15625, ...
Formally, for each K >= 0 there are coins worth 5K.

Given an integer A denoting the cost of an item, find and return the smallest number of coins necessary to pay exactly 
the cost of the item (assuming you have a sufficient supply of coins of each of the types you will need).
 * @author mp1863
 *
 */
public class AnotherCoinProblem {

	public int solve(int A) {
		int count= 0 ;
		int n = A;
		int div = 1;
		for(int i = 1 ; i < Math.pow(5, 32) ; i*=5) {
			if(n >=5 && n%i == n) {
				count = count + n/div;
				n = n%div;
				i = 1;
				
			}else if (n < 5){
				count = count + n;
				break;
			}else {
				div = i;
			}
		}
		return count;
    }
	
	public static void main(String[] args) {
		AnotherCoinProblem ap = new AnotherCoinProblem();
		System.out.println(ap.solve(91));
	}
}
