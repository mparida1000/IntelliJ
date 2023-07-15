package com.scaler.tries;
/**
 * Problem Description
There is a dictionary A of N words, and ith word has a unique weight Wi.

Another string array B of size M contains the prefixes. For every prefix B[i], output atmost 5 words from the dictionary A that start with the same prefix.

Output the words in decreasing order of their weight.

NOTE: If there is no word that starts with the given prefix output -1.

Problem Constraints
1 <= T <= 5

1 <= N <= 20000
1 <= M <= 10000

1 <= Wi <= 106

1 <= length of any string either in A or B <= 30

Input Format
First line is an integer T denoting the number of test cases.
For each test case,

First line denotes two space seperated integer N and M.
Second line denotes N space seperated strings denoting the words in dictionary.
Third line denotes N space seperated integers denoting the weight of each word in the dictionary.
Fourth line denotes M space seperated integers denoting the prefixes.

Output Format
For every prefix B[i], print the space seperated output on a new line.

NOTE: After every output there should be a space.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws Exception{
		Main ac = new Main();
		Scanner sc = new Scanner(System.in);
		String test_cases = sc.nextLine();
		int _queries = Integer.parseInt(test_cases);
		int t = 1;
		while(t <= _queries) {
			String dictioanry_prefix = sc.nextLine();
			int dictioanry_size = Integer.parseInt(dictioanry_prefix.split(" ")[0]);
			int prefix_size = Integer.parseInt(dictioanry_prefix.split(" ")[1]);
			ArrayList<Pair> inputDictionary = new ArrayList<>();
			String words = sc.nextLine();
			String weights = sc.nextLine();
			String prefixes = sc.nextLine();
			String[] wordsArr = words.split(" " );
			String[] weightsArr = weights.split(" " );
			String[] prefixArr = prefixes.split(" ");
			if(dictioanry_size != wordsArr.length) {
				throw new Exception("Incorrect words length");
			}
			if(dictioanry_size != weightsArr.length) {
				throw new Exception("Incorrect weights length");
			}
			if(prefix_size != prefixArr.length) {
				throw new Exception("Incorrect prefix length");
			}
			for(int i = 0 ; i < wordsArr.length ; i++) {
				inputDictionary.add(new Pair(wordsArr[i], Integer.parseInt(weightsArr[i])));
			}			
			Node root = new Node();
			for(int i = 0; i < inputDictionary.size() ; i++) {
				ac.insert(root, inputDictionary.get(i).getWord(), inputDictionary.get(i).getWeightage());
			}
			ArrayList<ArrayList<Pair>> listOfWordsWithPrefix = new ArrayList<>();
			for(int i = 0 ; i < prefixArr.length ; i++) {
				ArrayList<Pair> wordsList = ac.searchStartsWith(root, prefixArr[i]);
				wordsList = ac.sortWords(wordsList);
				if(wordsList.size() > 5) {
					wordsList.subList(5, wordsList.size()).clear();;
				}
				listOfWordsWithPrefix.add(wordsList);
			}
			for(ArrayList<Pair> al : listOfWordsWithPrefix) {
				for(Pair pair : al) {
					System.out.print(pair.getWord() + " ");
				}
				System.out.println();
			}
			t++;
		}
	}
	
	private ArrayList<Pair> searchStartsWith(Node root, String prefix) {
		Node curr = root;
		ArrayList<Pair> result = new ArrayList<>();
		for(int i = 0 ; i < prefix.length() ; i++) {
			int index = prefix.charAt(i) - 'a';
			if(curr.child[index] == null) {
				result.add(new Pair("-1", 0));
				return result;
			}
			curr = curr.child[index];
		}
		findAllWords(curr, result, prefix);		
		return result;
	}
	
	private void findAllWords(Node root, ArrayList<Pair> result, String prefix) {
		if(root.isEnd) {
			int cnt = root.count;
			for(int i = 0 ; i < cnt ; i++) {
				result.add(new Pair(prefix, root.wt));
			}
		}
		for(int i = 0 ; i < 26 ; i++) {
			if(root.child[i] != null) {
				char c = (char)(i+'a');
				String newPrefix = prefix + c;
				findAllWords(root.child[i], result, newPrefix);
			}
		}
	}
	
	private void insert(Node root, String word, int weight) {
		Node curr = root;
		int n = word.length();
		for(int i = 0 ; i < n ; i++) {
			int index = word.charAt(i) - 'a';
			if(curr.child[index] == null) {
				curr.child[index] = new Node();
			}
			curr = curr.child[index];
		}
		curr.isEnd = true;
		curr.wt = weight;
		curr.count+=1;
	}
	
	private ArrayList<Pair> sortWords(ArrayList<Pair> words) {
		Collections.sort(words, new Comparator<Pair>() {
			@Override
			public int compare(Pair a, Pair b) {
				return (a.getWeightage() == b.getWeightage() ? 0 : a.getWeightage() < b.getWeightage() ? 1 : -1);
			}
		});
		return words;
    }

}
class Pair{
	String word;
	int weightage;
	public Pair(String word, int weightage) {
		this.word = word;
		this.weightage = weightage;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getWeightage() {
		return weightage;
	}
	public void setWeightage(int weightage) {
		this.weightage = weightage;
	}
}
