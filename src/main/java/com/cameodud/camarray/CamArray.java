package com.cameodud.camarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class CamArray<T> implements Cloneable, java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private Object[] allocatedArray;
	private int size;
	private int allocatedSize;
	
	private Object[] EMPTY_ALLOCATED_ARRAY = {};
	
	public CamArray(int size) {
		parameterCheck(size);
		allocatedArray = new Object[size];
		allocatedSize = size;
		size = 0;
	}
	
	public CamArray(T[] Array) {
		parameterCheck(Array);
		allocatedArray = Arrays.copyOf(Array, Array.length);
		size = Array.length;
		allocatedSize = Array.length;
	}
	
	public CamArray(Collection<? extends T> elements) {
		Object[] items = elements.toArray();
		int size = items.length;
		if (size != 0) {
			allocatedArray = Arrays.copyOf(items, size, Object[].class);
		}
		else {
			allocatedArray = EMPTY_ALLOCATED_ARRAY;
		}
		this.size = size;
		allocatedSize = size;
	}
	
	public void add(T element) {
		if(size == allocatedSize) {
			addSpace();
		}
		allocatedArray[size] = element;
		size++;
	}
	
	public void insert(T element, int index) {
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException(String.format("index: %d, must be a positive integer.", index));
		}
		
		if(index == size) {
			add(element);
			return;
		}
		
		if(size == allocatedSize) {
			addSpace();
		}
		
		for(int i = size; i > index; i--) {
			allocatedArray[i] = allocatedArray[i - 1];
		}
		
		allocatedArray[index] = element;
		
		size++;
		
	}
	
//	public T pop() {
//		if (size == 0) {
//			
//		}
//	}
	
	public Object[] toArray() {
		return Arrays.copyOf(allocatedArray, size);
	}
	
	public List<T> toList() {
		try {
			T[] elements = Arrays.copyOf((T[]) allocatedArray, size);
			List<T> returnElements = new ArrayList<T>(size);
			for (int i = 0; i < elements.length; i++) {
				returnElements.add(elements[i]);
			}
			return returnElements;
		}
		catch (ClassCastException ce) {
			throw new ClassCastException("\"CamArray element cannot be cast to generic type.");
		}
	}

	public final int getSize() {
		return size;
	}

	private void parameterCheck(final int size) {
		if (size < 0) {
			String arg = String.format("size: %d, needs to be a non-negative integer.", allocatedArray);
			throw new IllegalArgumentException(arg);
		}
	}
	
	private void parameterCheck(T[] CamArray) {
        if (CamArray == null) {
            throw new IllegalArgumentException("Input CamArray cannot be null");
        }
    }
	
	private void addSpace() {
		int allocatedSize = 2 * this.allocatedSize;
		this.allocatedArray = Arrays.copyOf(this.allocatedArray, allocatedSize);
	}

}
