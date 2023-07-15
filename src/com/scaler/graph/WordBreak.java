package com.scaler.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
	List<String> ans = new ArrayList();
    public List<String> wordBreak(String s, List<String> wordDict) {
    	List<List<String>> dp = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordDict);
 
        for (int i = 0; i < s.length() + 1; i++) {
            dp.add(new ArrayList<>());
        }
        
        for(int i = 1; i < s.length() + 1; i++) {
            for (int j = 0; j <= i; j++) {
                if (wordSet.contains(s.substring(j, i))) {
                    if (j == 0) {
                        dp.get(i).add(s.substring(j, i));
                    } else if (dp.get(j).size() > 0) {
                        for (String sub : dp.get(j)) {
                            dp.get(i).add(sub + " " + s.substring(j, i));
                        }
                    }
                }
            }
        }
        return dp.get(s.length()); 
    }
    
    
    void dfs(int s, int e, String str, HashSet<String> w, String a) {
        if (s >= str.length()) {
            ans.add(a.substring(0, a.length()-1));
            return;
        }
        if (e > str.length()) return;
        String s1 = str.substring(s,e);
        if (w.contains(s1)) {
            dfs(e, e, str, w, a + s1 + " ");
        }
        dfs(s,e+1, str, w, a);
    }
    
    public static void main(String[] args) {
		List<String> wordDict = new ArrayList<>();
		wordDict.add("hi");
		wordDict.add("his");
		wordDict.add("am");
		wordDict.add("sam");

		
		List<String> op = new WordBreak().wordBreak("hisam", wordDict);
		for(String s : op) {
			System.out.println(s);
		}
	}
}
