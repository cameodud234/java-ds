package com.cameodud.camList;

import java.util.List;

public interface CamList<T> {
	
	public int getSize();
	
	// adds element to the end
	public void add(T val);
	
	// adds multiple elements to the end
	public void addAll(T[] vals);
	
	// adds multiple elements to the end
	public void addAll(List<T> vals);
	
	// adds element to the front
	public void addFront(T val);
	
	// Removes element at the end
	public T remove();
	
	// Removes multiple elements from the end.
	public T[] removeAll(T[] vals);
	
	// Removes multiple elements from the end.
	public List<T> removeAll(List<T> vals);
	
	// Removes element from the front.
	public T removeFront();
	
	// Removes element of specified value
	public T remove(int val);
	
	public String toString();
}