package com.ds.linear;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class LinkedList {
	
	static void printList(Node node){
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }
	
	//Merge 2 sorted list...
	static public Node mergeTwoLists(Node h1, Node h2) {
        if (h1 == null) {
            return h2;
        }
        else if (h2 == null) {
            return h1;
        }
        else if (h1.data < h2.data) {
            h1.next = mergeTwoLists(h1.next, h2);
            
            return h1;
        }
        else if(h1.data > h2.data){
            h2.next = mergeTwoLists(h1, h2.next);
            
            return h2;
        }
        
        return null;

    }
	
	static Node removeDupsUnsorted(Node head) { //Remove duplicate node from unsorted LL
		Node curr = head;
		Node prev = null;
		Map<Integer, Integer> countMap = new HashMap();
		while(curr != null) {
			if(!countMap.containsKey(curr.data)) {
				countMap.put(curr.data, 1);
			}else {
				Node temp = curr.next;
				prev.next = temp;
			}
			prev = curr;
			curr = curr.next;
			
		}
		
		return head;
		
	}
	
	static Node mergeNodesbetweenZeros(Node head) {
		Node curr = head;
		Node frontzero = null, prevNode = null;
		int sum = 0;
		while(curr!=null) {
			if(frontzero == null && curr.data == 0) {
				frontzero = curr;
			}else if(frontzero !=null && curr.data == 0) {
				if(prevNode.data != 0) {
					Node mergedNode = new Node(sum);
					frontzero.next = mergedNode;
					mergedNode.next = curr;
				}
				frontzero = curr;
				sum=0;
			}
			if(frontzero !=null) {
					sum+= curr.data;
			}
			prevNode = curr;
			curr = curr.next;
		}
		return head;
	}
	
	
	static Node deleteMiddleWithFastPointer(Node head) {
		
		Node slowPointer = head, fastPointer = head, prev = null;
		if(head == null || head.next == null) {
			return head;
		}
		while(fastPointer!=null && fastPointer.next!=null) {
			prev = slowPointer;
			slowPointer = slowPointer.next;
			fastPointer = fastPointer.next.next;
			
		}
		prev.next = slowPointer.next;
		return head;
	}

	//Reverse LinkedList
	static Node reverseList(Node head) {
		if(head == null) {
			return null;
		}
		if(head!=null && head.next == null) {
			return head;
		}
		Node curr = head;
		Node prev = null;
		Node next = null;
		while(curr != null) {
			next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
		}
        return prev;
	}
	
	static Node deleteNode(Node head, int pos) {
		if(pos==0) {
			head = head.next;
			return head;
		}
		Node prev = head;
		for(int i=0 ; i<pos-1 ; i++) {
			prev=prev.next;
		}
		prev.next = prev.next.next;
		return head;
		
	}
	
	//Floyd's Cycle Algo
	public static Node detectCycle(Node head) {
		Node slowPointer = head , fastPointer = head;
		while(fastPointer != null && fastPointer.next != null) {
			slowPointer = slowPointer.next;
			fastPointer = fastPointer.next.next;
			if(slowPointer == fastPointer) {
				return slowPointer;
			}
		}
		return null;
	}
	public static Node detectFirstNodeinCycle(Node head) {
		Node meet = detectCycle(head);
		Node start = head;
		while(meet != start) {
			start = start.next;
			meet = meet.next;
		}
		return start;
	}
	
	public static Node reverseBetween(Node A, int B, int C) {
		Node nodeB = A;
		Node nodeC = A;
		Node prev1 = null; Node prev2 = null;
		int i = 1;
		while(i<=C) {
			if(i<B) {
				prev1 = nodeB;
				nodeB = nodeB.next;
			}
			prev2 = nodeC;
			nodeC = nodeC.next;
			i++;
		}
		prev2 = reverseList(nodeB, prev2, B, C);
		if(prev1!=null) {
			prev1.next = prev2;
		}else {
			A = prev2;
		}
		nodeB.next = nodeC;
		return A;
	}
	static Node reverseList(Node A, Node B, int start, int end) {
		Node curr = A;
		Node prev = null;
		Node next = null;
		while(start <= end) {
			next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            start++;
		}
        return prev;
	}
	
	public static Node reverseList(Node A, int B) {
		if(A != null && A.next == null) return A;
		Node nodeB = A;
		Node p1 = A; Node end = null; Node start = A;
		int i = 1;
		int cycle = 1;
		while(nodeB != null) {
			while(i <= B) {
				end = nodeB;
				if(nodeB != null) {
					nodeB = nodeB.next;
				}else
					break;
				i++;
			}
			Node last = null;
			if(i == B+1) {
				last = reverseList(start, end, 1, B);
			}else if(i <= B) {
				last = reverseList(start, start, 1, i-1);
				//nodeB = nodeB.next;
			}
			
			i = 1;
			if(cycle == 1) {
				A = end;
			}else {
				p1.next = last;
				p1 = start;
			}
			start = nodeB;
			cycle++;
		}
		return A;
	}
	
	public void reorderList(Node head) {
		Node sp = head;
		Node fp = head;
		while(fp != null && fp.next != null) {
			sp = sp.next;
			fp = fp.next.next;
		}
		Node rev = reverseList(sp.next);
		sp.next = null;
		Node p1 = head, p2 = rev;
		while(p2 != null) {
			Node temp1  = p1.next;
			Node temp2 = p2.next;
			p1.next = p2;
			p2.next = temp1;
			p1 = temp1;
			p2 = temp2;
		}
	}
	
	public static Node addTwoNumbers(Node A, Node B) {
		Node head = new Node(0);
		Node curr = head;
        int carry = 0;
        while(A != null || B!= null){
            int v1 = A!=null ? A.data : 0;
            int v2 = B!=null ? B.data : 0;
            int sum = v1+v2+carry;
            carry = (sum)/10;
            curr.next = new Node(sum%10);
            curr = curr.next;
            A = (A != null) ? A.next : null;
            B = (B != null) ? B.next : null;
        }
        if(carry > 0){
            curr.next = new Node(carry);
        }
        return head.next;
    }
	
	public static int lPalin(Node A) {
		Node sp = A;
		Node fp = A;
		Node prev = null;
		while(fp != null && fp.next != null) {
			prev = sp;
			sp = sp.next;
			fp = fp.next.next;
		}
		Node curr = prev.next;
		prev.next = null;
		Node head = null;
		Node next = null;
		while(curr != null) {
			next = curr.next;
            curr.next = head;
            head = curr;
            curr = next;
		}
		Node p1 = head;
		Node p2 = A;
		while(p1 != null && p2 != null) {
			if(p1.data != p2.data)return 0;
			p1 = p1.next;
			p2 = p2.next;
		}
		return 1;
    }
	
	public static int longestOddLengthPalindrome(Node head) {
		if(head == null)return 0;
        if(head.next == null)return 1;
        Node curr = head;
        Node next = null, prev = null;
        int maxLen = 1;
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            maxLen = Math.max(maxLen, 2*calLen(prev, next)+1);
            maxLen = Math.max(maxLen, 2*calLen(curr, next));
            prev = curr;
            curr = next;
        }
        return maxLen;
    }
	public static int calLen(Node a, Node b){
        int len = 0;
        while(a != null & b!= null){
            if(a.data == b.data){
                len++;
            }else{
                break;
            }
            a = a.next;
            b = b.next;
        }
        return len;
    }
	
	
	
	public static void main(String[] args) {
		Node head1 = new Node(1);
        head1.next = new Node(30);
        head1.next.next = new Node(55);
        System.out.println("Print L1");
        printList(head1);
        
        Node head2 = new Node(0);
        head2.next = new Node(4);
        head2.next.next = new Node(20);
        
        System.out.println("\nPrint L2");
        
        printList(head2);
        System.out.println();
        Node merged = mergeTwoLists(head1, head2);
        System.out.println("Print Merged List...");
        printList(merged);
        System.out.println("\n");
        
        Node headDel = new Node(5);
        headDel.next = new Node(9);
        headDel.next.next = new Node(3);
        headDel.next.next.next = new Node(50);
        headDel.next.next.next.next = new Node(3);
        headDel.next.next.next.next.next= new Node(15);
        headDel.next.next.next.next.next.next = new Node(16);
        //System.out.println("Dups unsorted...");
        //Node delHead = removeDupsUnsorted(headDel);
        System.out.println("After Deleting \n");
        Node delHead = deleteNode(headDel,3);
        printList(delHead);
        
        System.out.println();
        Node mergeBetZero = new Node(100);
        mergeBetZero.next = new Node(0);
        mergeBetZero.next.next = new Node(3);
        mergeBetZero.next.next.next = new Node(6);
        mergeBetZero.next.next.next.next = new Node(0);
        mergeBetZero.next.next.next.next.next= new Node(9);
        mergeBetZero.next.next.next.next.next.next = new Node(15);
        mergeBetZero.next.next.next.next.next.next.next = new Node(0);
        System.out.println("Print List...Before Merge between Zeros");
        printList(mergeBetZero);
        System.out.println("\nPrint List...after Merge between Zeros");
        Node mergedHead = mergeNodesbetweenZeros(mergeBetZero);
        printList(mergedHead);
        
        System.out.println("\nReversed List::");
        Node revLsit = reverseList(mergeBetZero);
        printList(revLsit);
        
        
        System.out.println("\n\n\n\n");
        System.out.println("***********************Cycle in Node....\n");
        Node cycleNode = new Node(1);
        cycleNode.next = new Node(2);
        Node cycle = cycleNode.next.next = new Node(3);
        cycleNode.next.next.next = new Node(4);
        cycleNode.next.next.next.next = new Node(5);
        cycleNode.next.next.next.next.next= new Node(6);
        cycleNode.next.next.next.next.next.next = new Node(7);
        cycleNode.next.next.next.next.next.next.next = cycle;
        //System.out.println(detectCycle(cycleNode).data);
        System.out.println("Cycle Node:"+detectFirstNodeinCycle(cycleNode).data); 
        //printList(cycleNode);
        
        Node removeDupUnsorted = new Node(1);
        removeDupUnsorted.next = new Node(2);
        removeDupUnsorted.next.next = new Node(1);
        Node result = removeDupsUnsorted(removeDupUnsorted);
        System.out.println();
        System.out.println("Remove dupls node");
        printList(result);
        System.out.println();
        System.out.println("LL Partially reversed...");
        Node rev2 = new Node(1);
        rev2.next = new Node(2);
        rev2.next.next = new Node(3);
        rev2.next.next.next = new Node(4);
        rev2.next.next.next.next = new Node(5);
        rev2.next.next.next.next.next= new Node(6);
        rev2.next.next.next.next.next.next = new Node(7);
        rev2.next.next.next.next.next.next.next = new Node(8);
        rev2.next.next.next.next.next.next.next.next = new Node(9);
        rev2.next.next.next.next.next.next.next.next.next = new Node(10);
        //rev2 = reverseBetween(rev2, 1,8);
        rev2 = reverseList(rev2, 2);
        printList(rev2);
        
        System.out.println();
        System.out.println("Adding 2 nodes....");
        System.out.println();
        Node add = new Node(1);
        //add.next = new Node(2);
        //add.next.next = new Node(3);
        Node add1 = new Node(9);
        add1.next = new Node(9);
        add1.next.next = new Node(9);
        Node res = addTwoNumbers(add, add1);
        printList(res);
        
        Node palin = new Node(1);
        palin.next = new Node(2);
        palin.next.next = new Node(3);
        palin.next.next.next = new Node(2);
        palin.next.next.next.next= new Node(1);
        int i = lPalin(palin);
        System.err.println("Is palindrome List ? " + i);
        
        System.out.println();
        
        Node longpalin = new Node(7);
        longpalin.next = new Node(1);
        longpalin.next.next = new Node(2);
        longpalin.next.next.next = new Node(1);
        longpalin.next.next.next.next = new Node(4);
        longpalin.next.next.next.next.next= new Node(5);
        int l = longestOddLengthPalindrome(longpalin);
        System.out.println("Longest Plaindrome:" + l);
	}
}

class Node{
	int data;
	Node next;
	Node down;
	
	public Node(int data) {
		this.data = data;
	}
}
