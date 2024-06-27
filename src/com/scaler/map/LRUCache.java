package com.scaler.map;

import java.util.HashMap;

/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity,
it should invalidate the least recently used item before inserting the new item.
The LRUCache will be initialized with an integer corresponding to its capacity.
Capacity indicates the maximum number of unique keys it can hold at a time.

Definition of "least recently used" : An access to an item is defined as a get or a set operation of the item.
"Least recently used" item is the one with the oldest access time.
 */

public class LRUCache {
	class Node {
        int key;
        int value;
        Node prev;
        Node next;
        
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
	private int capacity;
    private HashMap<Integer, Node> map;
    private Node head;
    private Node tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        this.head.next = this.tail;
        this.tail.prev = this.head;   
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        
        Node node = map.get(key);
        removeNode(node);
        addNode(node);
        
        return node.value;
    }

    //Update the value of the Key/node
    //Or insert the new node, if capacity is full, remove from tail
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addNode(node);
        } else {
            if (map.size() == capacity) {
                map.remove(tail.prev.key);
                removeNode(tail.prev);
            }
            
            Node node = new Node(key, value);
            map.put(key, node);
            addNode(node);
        }
    }
    //remove the latest node read
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    //Bring the latest node to the front
    private void addNode(Node node) {
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(4);

        // Initial state, cache capacity is 4
        System.out.println(cache.get(1));       // returns -1 (not found)

        // Adding elements to the cache
        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(3, 30);
        cache.put(4, 40);

        // Cache is now full
        System.out.println(cache.get(1));       // returns 10
        System.out.println(cache.get(2));       // returns 20
        System.out.println(cache.get(3));       // returns 30
        System.out.println(cache.get(4));       // returns 40

        // Adding another element should evict the least recently used (key 1)
        cache.put(5, 50);
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(5));       // returns 50

        // Accessing some elements to change their usage order
        System.out.println(cache.get(2));       // returns 20
        cache.put(6, 60);

        // This should evict key 3 (the least recently used after the last access)
        System.out.println(cache.get(3));       // returns -1 (not found)
        System.out.println(cache.get(6));       // returns 60

        // Adding more elements to test evictions
        cache.put(7, 70);
        cache.put(8, 80);

        // This should evict key 4 and key 5 (the least recently used after the last access)
        System.out.println(cache.get(4));       // returns -1 (not found)
        System.out.println(cache.get(5));       // returns -1 (not found)
        System.out.println(cache.get(7));       // returns 70
        System.out.println(cache.get(8));       // returns 80
    }
}
