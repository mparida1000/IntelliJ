package com.ds.string;

//Java code to find the first repeating character in a
//string
import java.util.*;

public class GFG {
public static int INT_MAX = 2147483647;

//Function to find left most repeating character.
public static char firstRep(String s)
{
	HashMap<Character, Integer> map
	= new HashMap<Character, Integer>();
	char c = '#';
	int index = INT_MAX;

	// single traversal of string.
	for (int i = 0; i < s.length(); i++) {
	char p = s.charAt(i);

	if (!map.containsKey(p)) {
		map.put(p, i);
	}
	else {
		if (map.get(p) < index) {
		index = map.get(p);
		c = p;
		}
	}
	}

	return c;
}

//Main function.
public static void main(String[] args)
{

	// Input string.
	String s = "abccba";
	System.out.print(firstRep(s));
	System.out.print("\n");
}
}

//This code is contributed by Aarti_Rathi

