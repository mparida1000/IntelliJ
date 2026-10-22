package Top150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatOfAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return res;

        int L = words[0].length();
        int m = words.length;
        int totalLen = L * m;
        if (totalLen > s.length()) return res;

// need[word] = required frequency
        Map<String, Integer> need = new HashMap<>();
        for (String w : words) need.put(w, need.getOrDefault(w, 0) + 1);

// We run L windows: start at 0..L-1
        for (int offset = 0; offset < L; offset++) {
            int left = offset;
            int count = 0; // how many words currently in window
            Map<String, Integer> seen = new HashMap<>();

            for (int right = offset; right + L <= s.length(); right += L) {
                String cur = s.substring(right, right + L);

// If word not in need, reset window
                if (!need.containsKey(cur)) {
                    seen.clear();
                    count = 0;
                    left = right + L;
                    continue;
                }

// add cur
                seen.put(cur, seen.getOrDefault(cur, 0) + 1);
                count++;

// If cur is over-counted, shrink from left
                while (seen.get(cur) > need.get(cur)) {
                    String lw = s.substring(left, left + L);
                    seen.put(lw, seen.get(lw) - 1);
                    count--;
                    left += L;
                }

// If we have m words, record answer
                if (count == m) {
                    res.add(left);

// move left by one word to continue
                    String lw = s.substring(left, left + L);
                    seen.put(lw, seen.get(lw) - 1);
                    count--;
                    left += L;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        String s = "barfoofoothefoobarman";
        String[] words = {"foo","bar"};
        System.out.println(new SubstringWithConcatOfAllWords().findSubstring(s, words));
    }
}
