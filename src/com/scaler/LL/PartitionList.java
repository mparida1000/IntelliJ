package com.scaler.LL;

public class PartitionList {
	
	public Node partition(Node A, int B) {
		Node sPartitionHead = new Node(-1);
		Node sNode = sPartitionHead;
		Node bPartitionHead = new Node(-1);
		Node bNode = bPartitionHead;
		
		Node curr = A;
		while(curr != null) {
			Node next = curr.next;
			if(curr.data < B) {
				sNode.next = curr;
				sNode = sNode.next;
			}else {
				bNode.next = curr;
				bNode = bNode.next;
			}
			curr = next;
		}
		bNode.next = null;
		sNode.next = bPartitionHead.next;
		
		return sPartitionHead.next;
    }
	
	public static void main(String[] args) {
		Node head = new Node(1);
		head.next = new Node(4);
		head.next.next = new Node(3);
		head.next.next.next = new Node(2);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(2);
		
		head = new PartitionList().partition(head, 3);
		while(head != null) {
			System.out.print(head.data +" ");
			head= head.next;
		}
	}

}
