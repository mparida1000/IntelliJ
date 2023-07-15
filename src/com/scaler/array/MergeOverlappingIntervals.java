package com.scaler.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/*
Problem Description
Given a collection of intervals, merge all overlapping intervals.
Problem Constraints
1 <= Total number of intervals <= 100000.
 */
public class MergeOverlappingIntervals {
	public static ArrayList<Interval> merge(ArrayList<Interval> intervals) {
		Collections.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval a, Interval b) {
				return (a.start < b.start ? -1 : a.start > b.start ? 1 : 0);
			}
		});
		ArrayList<Interval> op = new ArrayList<>();
		int s = intervals.get(0).start;
		int e = intervals.get(0).end;
		for(int i = 1 ; i < intervals.size() ; i++) {
			if(intervals.get(i).start <= e) {
				e = Math.max(intervals.get(i).end, e);
			}else {
				op.add(new Interval(s, e));
				s = intervals.get(i).start;
				e = intervals.get(i).end;
			}
		}
		op.add(new Interval(s,e));
		return op;
    }
	
	public static void main(String[] args) {
		ArrayList<Interval> al = new ArrayList<>();
		Interval i1 = new Interval(2, 4);
		Interval i2 = new Interval(3, 6);
		Interval i2_ = new Interval(3, 5);
		Interval i3 = new Interval(8, 10);
		Interval i4 = new Interval(10, 14);
		Interval i5 = new Interval(16, 18);
		al.add(i1);
		al.add(i2);
		al.add(i2_);
		al.add(i3);
		al.add(i4);
		al.add(i5);
		ArrayList<Interval> al1 = new ArrayList<>();
		al1 = merge(al);
		for(Interval i : al1) {
			System.out.println(i.start +" - " + i.end);
		}
		
	}

}

class Interval {
  int start;
  int end;
  Interval() { start = 0; end = 0; }
  Interval(int s, int e) { start = s; end = e; }
}
