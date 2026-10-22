package Top150;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinations {
    // Mapping of digits to letters as per telephone buttons
    private static final String[] mpping = {
            "",     // 0 (no mapping)
            "",     // 1 (no mapping)
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
    };
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if(digits.length() == 0 || digits == null) return result;
        StringBuilder sb = new StringBuilder();
        retrievCombination(result, sb, digits, 0);
        return result;
    }

    private void retrievCombination(List<String> result, StringBuilder combinations, String digits, int index){
        if(index == digits.length()){
            result.add(combinations.toString());
            return;
        }
        String letters = mpping[digits.charAt(index)-'0'];
        for(char c : letters.toCharArray()){
            combinations.append(c);
            retrievCombination(result, combinations, digits, index+1);
            combinations.deleteCharAt(combinations.length()-1);
        }
    }


    public static void main(String[] args) {
        LetterCombinations solution = new LetterCombinations();

        // Example 1
        String digits1 = "27";
        System.out.println("Input: " + digits1);
        System.out.println("Output: " + solution.letterCombinations(digits1));

        // Example 2
        String digits2 = "";
        System.out.println("Input: " + digits2);
        System.out.println("Output: " + solution.letterCombinations(digits2));

        // Example 3
        String digits3 = "2";
        System.out.println("Input: " + digits3);
        System.out.println("Output: " + solution.letterCombinations(digits3));
    }
}
