package com.cameodud.camarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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


}
