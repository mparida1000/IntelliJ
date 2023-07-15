package com.scaler.bt;

import java.util.HashMap;
import java.util.Map;

public class WordBreak {
	
	public int wordBreak(String A, String[] B) {
		Map<String, Boolean> map = new HashMap<>();
		for(String s : B) {
			map.put(s, true);
		}
		if(canSegemntWord(A, map))return 1;
		return 0;
    }
	
	boolean canSegemntWord(String word, Map<String, Boolean> map) {
		int len = word.length();
		if(len == 0) {
			return true;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < Math.min(20, len) ; i++) {
			sb.append(word.charAt(i));
			if(map.containsKey(sb.toString()) && canSegemntWord(word.substring(i+1), map)) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		String [] B = {"bbba", "aaaa", "abaa", "aba", "aabaaa", "baabbaab", "bbbabbbaaa", "abaabbbbba", "abaa", "aba", "bbabbbbabb", "aab", "baaabbbaaa", "b", "baba", "aaba", "baaba", "abb", "aaaa", "baaabbbaa", "ab"};
		String A = "babbbbaabbaabaabaabaaabaababaaaabbbbbabbaabbabbbbababaabbabbbabbbaaabaababaaaababbbbabbbbbbaaaabaaabaabbbaaaabaaabbbaabababbbaaaabbabbbabaaabbbabaaabbbaaaaaabaabbabbaabbbbbaababbbbabbabbaabbbabaababaaaabbaabbbaabaabbbbbbaabbbaaaabbaaaaaabaabbaababbbabbbbbbaabbaabbbabbbaabbbaaaabbbaaaabbbabbaababaaabbababbaabbabbabaabbbbaaaabbaababababbbbbabbbbabbaaabbaabaaaaabbaaaaaaaaaaababaaabbbaababbbbbbbabbababbaabbaaaababbbabbaaabbbbbabbbaabbaaaaabbbbbbabbbbbabbabbbabbabbababbabaabaabbabababbababaababbaababbabaabbaaaabbbaa";
		WordBreak wb = new WordBreak();
		System.out.println(wb.wordBreak(A, B));
		System.out.println(A.substring(11));
	}

}
