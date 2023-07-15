package com.scaler.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SortbyColor {
	
	public static void main(String[] args) {
		int [] A = {0,1,2,0,1,2};//9 5 34 3 30
		List<Integer> list = IntStream.of(A)
                .boxed()
                .collect(Collectors.toList());
		ArrayList<Integer> arraylist = new ArrayList<>(list);
		ArrayList<Integer> arraylist1 = sortColors(arraylist);
		for(Integer i : arraylist1) {
			System.out.print(i + " ");
		}
	}
	
	public static ArrayList<Integer> sortColors(ArrayList<Integer> A) {
		Collections.sort(A, new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return (a==b ? 0 : a > b ? 1 : -1);
			}
		});
		return A;
    }

}
