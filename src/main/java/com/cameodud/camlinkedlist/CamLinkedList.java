package com.cameodud.camlinkedlist;

import java.util.ArrayList;
import java.util.List;

// Code for a doubly linked-list
public class CamLinkedList {
	
	private Node head;
	private Node tail;
	
	
	public CamLinkedList() {
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.prev = head;
	}
	
	public int get(int i) {
		int count = 0;
		Node current = head.next;
		while(current != null && current != tail) {
			if(count == i) {
				return current.val;
			}
			current = current.next;
			count++;
		}
		return -1;
	}
	
	public void insertHead(int val) {
		Node begin = head;
		Node afterBegin = head.next;
		Node newNode = new Node(val, begin, afterBegin);
		begin.next = newNode;
		afterBegin.prev = newNode;
	}
	
	public void insertTail(int val) {
		Node end = tail;
		Node beforeEnd = tail.prev;
		Node newNode = new Node(val, beforeEnd, end);
		end.prev = newNode;
		beforeEnd.next = newNode;
	}
	
	public boolean remove(int i) {
		Node current = head.next;
		int count = 0;
		
		while(current != null && current != tail) {
			if(count == i) {
				current.prev.next = current.next;
				current.next.prev = current.prev;
				return true;
			}
			current = current.next;
			count++;
		}
		
		return false;
		
	}
	
	public List<Integer> getValues() {
		Node current = head.next;
		List<Integer> values = new ArrayList<>();
		while(current != null && current != tail) {
			values.add(current.val);
			current = current.next;
		}
		return values;
	}
	
	private class Node {
		
		public int val;
		public Node prev;
		public Node next;
		
		public Node() {
			val = Integer.MIN_VALUE;
			prev = null;
			next = null;
		}
		
		public Node(int val) {
			this.val = val;
			prev = null;
			next = null;
		}
		
		public Node(int val, Node prev, Node next) {
			this.val = val;
			this.prev = prev;
			this.next = next;
		}
		
	}
	
}