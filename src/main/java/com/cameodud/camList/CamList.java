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
	
	// adds multiple elements to the front
	public void addFrontAll(T[] vals);
	
	// adds multiple elements to the front
	public void addFrontAll(List<T> vals);
	
	// Removes element at the end
	public T remove();
	
	// Removes all occurances of input value
	public T removeAll(T val);
	
	// Removes element from the front.
	public T removeFront();
	
	// Removes element of specified value
	public T remove(T val);
	
	// Removes all elements
	public void clear();
	
	public String toString();
	
}