package com.ds.queue;

public class QueueUsingArray {
	
		int a[];
		int capacity;
		int rear;
		
		public QueueUsingArray(int n) {
			capacity = n;
			a = new int[n];
			rear = -1;
		}
	
	public void enque(int data) throws Exception {
		QueueUsingArray q = new QueueUsingArray(data);
		if(rear == a.length-1) {
			throw new Exception();
		}
		rear++;
		a[rear] = data;
	}
	
	public int deque() throws Exception {
		if (rear == -1) {
			throw new Exception();
		}
		int data = a[0];
		for(int i = 0 ; i < a.length ; i++) {
			a[i] = a[i+1];
		}
		rear--;
		return data;
	}
	
	public int getFront() throws Exception {
		if(rear == -1) {
			throw new Exception();
		}
		return a[0];
	}

}
