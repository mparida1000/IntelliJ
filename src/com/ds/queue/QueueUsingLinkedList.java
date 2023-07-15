package com.ds.queue;

public class QueueUsingLinkedList {

	class Node{
		int data;
		Node next;
		
		public Node(int data) {
			this.data = data;
		}
	}
	
	Node front, rear;
	public void enque(int data) {
		Node newNode = new Node(data);
		if(front == null) {
			front = rear = newNode; return;	
		}
		rear.next = newNode;
		rear = newNode;
		
	}

	public int dequeue() throws Exception {
		if(front == null) {
			throw new Exception();
		}
		int data = front.data;
		front = front.next;
		return data;
	}
	
}
