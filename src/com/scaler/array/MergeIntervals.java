package com.scaler.array;

import java.util.ArrayList;
/*
Problem Description
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
You may assume that the intervals were initially sorted according to their start times.
Problem Constraints
0 <= |intervals| <= 105
 */
public class MergeIntervals {

	public static ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        int n = intervals.size();
        ArrayList<Interval> al = new ArrayList();
        for(int i = 0 ; i < n ; i++){
            Interval currInterval = intervals.get(i);
            if(currInterval.end < newInterval.start){
                al.add(currInterval);
            }else if(currInterval.start > newInterval.end){
                al.add(newInterval);
                for(int j = i ; j < n ; j++){
                    currInterval = intervals.get(j);
                    al.add(currInterval);
                }
                return al;
            }else {
            	newInterval.start = Math.min(currInterval.start, newInterval.start);
                newInterval.end = Math.max(currInterval.end, newInterval.end);
            }
            
        }
        al.add(newInterval);
        return al;
    }
	
	public static void main(String[] args) {
		ArrayList<Interval> al = new ArrayList<>();
		Interval i1 = new Interval(2, 4);
		Interval i2 = new Interval(8, 10);
		Interval i3 = new Interval(11, 14);
		Interval i4 = new Interval(17, 20);
		Interval i5 = new Interval(19, 24);
		Interval i6 = new Interval(30, 35);
		Interval i7 = new Interval(36, 39);
		al.add(i1);
		al.add(i2);
		al.add(i3);
		al.add(i4);
		al.add(i5);
		al.add(i6);
		al.add(i7);
		Interval I = new Interval(12, 22);
		ArrayList<Interval> al1 = new ArrayList<>();
		al1 = insert(al, I);
		for(Interval i : al1) {
			System.out.println(i.start +" - " + i.end);
		}
	}
}
