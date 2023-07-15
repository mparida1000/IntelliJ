package com.ds;

public class LinkedList {
	
	LinkedNode head;
	
	public void push(int new_data) {
		LinkedNode new_node = new LinkedNode(new_data, null);
		new_node.next = head;
		head = new_node;
		
	}
	
	public void printLinkedList() {
		LinkedNode currNode = head;
		while(currNode != null) {
			System.out.print(currNode.data + " ");
			currNode = currNode.next;
		}
	}
	
	static public LinkedNode mergeTwoLists(LinkedNode l1, LinkedNode l2) {
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.data < l2.data) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }
	
	public static void main(String[] args) {
		LinkedList linkedList = new LinkedList();
		linkedList.push(5);
		linkedList.push(4);
		linkedList.push(3);
		linkedList.push(2);
		linkedList.push(1);
		
		System.out.println("Linked list is");
		linkedList.printLinkedList();
		
	
		
		System.out.println("Linked list2 is");
		//linkedList1.printLinkedList();
		
		
		
		
	}

}

class LinkedNode{
	int data;
	LinkedNode next;
	
	public LinkedNode(int data, LinkedNode next) {
		this.data = data;
		this.next = null;
	}
	
	
	
}
