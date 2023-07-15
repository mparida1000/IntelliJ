package com.ds.linear;

import java.util.Scanner;


	public class TestScript {
	static Scanner sc = null;
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		Node head = constructList();
		//Node deletedBetZero = LinkedList.mergeNodesbetweenZeros(head);
		//LinkedList.printList(deletedBetZero);
		//Node afterDelMiddleNode = LinkedList.deleteMiddleNode(head);
		System.out.println();
		//LinkedList.printList(afterDelMiddleNode);

		
	}
	
	static Node constructList() {
		System.out.println("Enter data..");
		int data = sc.nextInt();
		if(data == -1) return null;
		Node head = new Node(data);
		head.next = constructList();
		return head;
	}

}
