package com.scaler.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LargestNumber {
	
	public static String largestNumber(ArrayList<Integer> A) {
		List <String> al = new ArrayList();
		for(int i = 0 ; i < A.size() ; i++) {
			al.add(String.valueOf(A.get(i)));
		}
		Collections.sort(al,new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				String ab = a+b;
				String ba = b+a;
				return ab.compareTo(ba) > 0 ? -1 : 1;
			}
		});
		StringBuffer sb = new StringBuffer();
		for(String s : al) {
			sb.append(s);
		}
		String str = sb.toString();
		int ind = 0;
		int cnt_0 = 0;
		for (int i = 0; i < str.length(); i++) {
			char p = str.charAt(i);
			if(p == '0')cnt_0++;
			if (p != '0') {
				ind = i;
				break;
			}
		}
		if(cnt_0 == str.length()) return "0";
		return str.substring(ind, str.length());
    }
	
	public static void main(String[] args) {
		int [] A = {0,0,0,0,1};//9 5 34 3 30
		List<Integer> list = IntStream.of(A)
                .boxed()
                .collect(Collectors.toList());
		ArrayList<Integer> arraylist = new ArrayList<>(list);
		System.out.println(largestNumber(arraylist));
	}
	
	public class Sort{
		int num;
		
	}

}


