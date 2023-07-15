package com.ds.string;

import java.util.Arrays;

public class ReplaceString {
	
	public static String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        int N = S.length();
        int[] match = new int[N]; // [-1,-1,-1,-1,-1,-1,...,-1]
        Arrays.fill(match, -1);
        
        for(int i=0; i<indexes.length; i++) {
            int ix = indexes[i]; // 0
            if(S.substring(ix, ix + sources[i].length()).equals(sources[i]))
                match[ix] = i;
        }
        
        StringBuilder answer = new StringBuilder();
        int index = 0;
        while(index < N) {
            if(match[index]>=0) {
                answer.append(targets[match[index]]);
                index += sources[match[index]].length();
            } else {
                answer.append(S.charAt(index++));
            }
        }
        
        return answer.toString();
    }
	
	public static void main(String[] args) {
		
		String s = "abcd";
		int[] indexes = {0,2};
		String[] sources = {"f","c"};
		String[] targets = {"ff","eeeee"};
		System.out.println(findReplaceString(s, indexes, sources, targets));
	}

}
