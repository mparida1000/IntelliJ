package Top150;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        Map<Character, Integer> freq = new HashMap<>();
        int i=0, j = 0;
        int left = 0;
        int right = s.length() - 1;
        int min = s.length();
        boolean found = false;

        for (int k = 0; k < t.length(); k++) {
            char c = t.charAt(k);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        int count = freq.size();
        while (j < s.length()) {
            char endChar = s.charAt(j++);
            if (freq.containsKey(endChar)) {
                freq.put(endChar, freq.get(endChar) - 1);
                if (freq.get(endChar) == 0) count -= 1;
            }
            if (count > 0) continue;
            while (count == 0) {
                char startChar = s.charAt(i++);
                if (freq.containsKey(startChar)) {
                    freq.put(startChar, freq.get(startChar) + 1);
                    if (freq.get(startChar) > 0) count += 1;
                }
            }
            if ((j - i) < min) {
                left = i;
                right = j;
                min = j - i;
                found = true;
            }
        }
        return !found ? "" : s.substring(left - 1, right);

    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(new MinimumWindowSubstring().minWindow(s, t));
    }
}

