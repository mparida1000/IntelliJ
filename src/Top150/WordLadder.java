package Top150;

import java.util.*;
/*
Leetcode HARD
A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words
beginWord -> s1 -> s2 -> ... -> sk such that:
Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation
sequence from beginWord to endWord, or 0 if no such sequence exists.
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.*/
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        Set<String> wordSet = new HashSet<>();
        wordSet.addAll(wordList);
        int level = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0 ; i < size ; i++){
                String word = queue.poll();
                for(int j = 0 ; j < word.length() ; j++){
                    char [] chars = word.toCharArray();
                    for(char c = 'a' ; c <= 'z'; c++ ){
                        char originalChar = c;
                        chars[j] = originalChar;
                        String newWord = new String(chars);
                        if(newWord.equals(endWord)){
                            return level+1;
                        }
                        if(wordSet.contains(newWord)){
                            queue.add(newWord);
                            wordSet.remove(newWord);
                        }
                        chars[j] = originalChar;
                    }
                }
            }
            level++;
        }
        return 0;
    }

    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println(new WordLadder().ladderLength("hit","cog", wordList));
    }
}
