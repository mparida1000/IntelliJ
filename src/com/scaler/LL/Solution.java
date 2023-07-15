package com.scaler.LL;

import java.util.Arrays;

public class Solution {
	public static Node solve(Node A, int B) {
		Node curr = A;
        Node prev = A;
        while(curr!=null){
        	if(curr.data % B != 0) {
        		curr = curr.next;
        	}else if(prev.data % B != 0 && prev != curr){
        		prev.data = curr.data;
        		prev = prev.next;
        	}else {
        		curr = curr.next;
        		prev = prev.next;
        	}
        }
        return A;
    }
	
	public static void main(String[] args) {
		Node head = new Node(1);
		 head.next = new Node(3);
		 head.next.next = new Node(3);
		 head.next.next.next = new Node(5);
		 head.next.next.next.next = new Node(2);
		 head.next.next.next.next.next = new Node(2);
		 head.next.next.next.next.next.next = new Node(2);
		 head.next.next.next.next.next.next.next = new Node(10);
		
		head = solve(head, 3);
		System.out.println();
		while(head != null) {
			 System.out.print(head.data + " ");
			 head = head.next;
		 }
	}

}
