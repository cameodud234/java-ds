package com.cameodud.camarray;

import java.util.Arrays;

public class CamArray {
	
	private int allocatedSize;
	private int currentIndex = -1;
	private int size = 0;
	private int[] arr;

	public CamArray(int size) {
		
		if(size < 0) {
			throw new IllegalArgumentException("Size must be nonnegative...");
		}
		
		allocatedSize = size;
		currentIndex = -1;
		arr = new int[allocatedSize];
	}
	
	public int getSize() {
		return size;
	}
	
	public int getCurrentIndex() {
		return currentIndex;
	}
	
	public int getAllocatedSize() {
		return allocatedSize;
	}
	
	public int[] getArr() {
		return Arrays.copyOf(arr, size);
	}
	
	public void add(int val) {
		if(needsResize()) {
			resize();
		}
		if(currentIndex == -1) {
			currentIndex = 0;
		}
		arr[currentIndex] = val;
		currentIndex++;
		size++;
	}
	
	public void addFront(int val) {
		// Need to make sure size is defined.
		if(needsResize()) {
			resize();
		}
		
		for(int i = size; i > 0; i--) {
			arr[i] = arr[i-1];
		}
		arr[0] = val;
		
		currentIndex++;
		size++;
		
	}
	
	// No value means remove last added element
	public int remove() throws EmptyCamArrayException {
		if(currentIndex == -1) {
			throw new EmptyCamArrayException("Cannot remove from empty array.");
		}
		
		currentIndex--;
		size--;
		return arr[currentIndex];
	}
	
	public int removeFront() throws EmptyCamArrayException {
		if(currentIndex == -1) {
			throw new EmptyCamArrayException("Cannot remove from empty array.");
		}
		
		int val = arr[0];
		for(int i = 0; i < size - 1; i++) {
			arr[i] = arr[i + 1];
		}
		
		currentIndex--;
		size--;
		return val;
	}
	
	public int remove(int val) throws EmptyCamArrayException {
		if(currentIndex == -1) {
			throw new EmptyCamArrayException("Cannot remove from empty array.");
		}
		
		else if(val == arr[0]) {
			return removeFront();
		}
		else if(val == arr[currentIndex - 1]) {
			return remove();
		}
		
		int index = -1;
		int returnVal = Integer.MIN_VALUE;
		for(int i = 0; i < size; i++) {
			if(arr[i] == val) {
				index = i;
				returnVal = arr[index];
				break;
			}
		}
		if(index == -1) return returnVal;
		
		if(needsResize()) {
			resize();
		}
		
		for(int i = index; i < size - 1; i++) {
			arr[i] = arr[i+1];
		}
		currentIndex--;
		size--;
		return returnVal;
	}
	
	private boolean needsResize() {
		if(size == allocatedSize) {
			return true;
		}
		return false;
	}

	private void resize() {
		int newAllocatedSize = (allocatedSize + 1) * 2;
		arr = Arrays.copyOf(arr, newAllocatedSize);
		allocatedSize = newAllocatedSize;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for(int i = 0; i < getSize(); i++) {
			if(i != getSize() - 1) {
				sb.append(arr[i]);
				sb.append(", ");
			}
			else {
				sb.append(arr[i]);
				sb.append("}");
				break;
			}
		}
		return sb.toString();
	}
	
}