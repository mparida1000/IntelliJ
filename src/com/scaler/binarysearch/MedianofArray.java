package com.scaler.binarysearch;

import java.util.ArrayList;
import java.util.List;
/**
 * Problem Description
There are two sorted arrays A and B of sizes N and M respectively.
Find the median of the two sorted arrays ( The median of the array formed by merging both the arrays ).
NOTE:
The overall run time complexity should be O(log(m+n)).
IF the number of elements in the merged array is even, then the median is the average of (n/2)th and (n/2+1)th element. 
For example, if the array is [1 2 3 4], the median is (2 + 3) / 2.0 = 2.5.
Problem Constraints
1 <= N + M <= 2*106
 * @author mp1863
 *
 */
public class MedianofArray {

	public static double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {
		int n1 = a.size(), n2 = b.size();
		if((n1+n2) == 1) {
			return n1==0 ? b.get(0) : a.get(0);
		}
		if(n1 > n2) {
			return findMedianSortedArrays(b, a);
		}
		int l = 0, h = n1;
		while(l<=h) {
			int mid1 = (l + h)/2;
			int mid2 = (n1+n2+1)/2 - mid1;
			int l1 = mid1 == 0 ? Integer.MIN_VALUE : a.get(mid1-1);
			int l2 = mid2 == 0 ? Integer.MIN_VALUE : b.get(mid2-1);
			int r1 = mid1 == n1 ? Integer.MAX_VALUE : a.get(mid1);
			int r2 = mid2 == n2 ? Integer.MAX_VALUE : b.get(mid2);
			if(l1 <= r2 && l2 <= r1) {
				if( (n1+n2)%2 == 0 ) {
					return (Math.max((double)l1, (double)l2) + Math.min((double)r1, (double)r2))/2;
				}else {
					return Math.max((double)l1, (double)l2);
				}
			}else if(l1 > r2) {
				h = mid1-1;
			}else {
				l = mid1+1;
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		List <Integer> a = new ArrayList<>();
		List <Integer> b = new ArrayList<>();
		a.add(1);
		a.add(2);
		a.add(6);
		a.add(8);
		b.add(3);
		b.add(4);
		b.add(5);
		b.add(7);
		b.add(9);
		b.add(10);
		double median = findMedianSortedArrays(a, b);
		System.out.println(median);
	}
}
