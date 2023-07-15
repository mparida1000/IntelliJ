package com.scaler.LL;

import java.util.HashMap;
import java.util.Map;

public class CopyList {
	public static RandomListNode copyRandomList(RandomListNode head) {
		Map<RandomListNode, RandomListNode> map = new HashMap();
		RandomListNode newHead = head;
		map.put(head, newHead);
		RandomListNode curr = head.next;
		RandomListNode newCurr = newHead;
		while(curr != null) {
			RandomListNode newNode = new RandomListNode(curr.label);
			newCurr.next = newNode;
			newCurr = newNode;
			map.put(curr, newNode);
			curr = curr.next;
		}
		curr = head;
		newCurr = newHead;
		while(curr != null) {
			newCurr.random = map.get(curr.random);
			curr = curr.next;
			newCurr = newCurr.next;
		}
		return newHead;
    }
	
}
class RandomListNode {
      int label;
      RandomListNode next, random;
      RandomListNode(int x) { this.label = x; }
}
	 
