package com.cameodud.camList;

import java.util.List;

public interface CamList {
	public int getSize();
	
	// adds element to the end
	public void add(int val);
	
	// adds multiple elements to the end
	public void addAll(int[] vals);
	
	// adds multiple elements to the end
	public void addAll(List<Integer> vals);
	
	// adds element to the front
	public void addFront(int val);
	
	// Removes element at the end
	public int remove();
	
	// Removes multiple elements from the end.
	public int[] removeAll(int[] vals);
	
	// Removes multiple elements from the end.
	public List<Integer> removeAll(List<Integer> vals);
	
	// Removes element from the front.
	public int removeFront();
	
	// Removes element of specified value
	public int remove(int val);
	
	public String toString();
}