package com.cameodud.camarray;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.cameodud.camList.CamList;

@SuppressWarnings("unchecked")
public class CamArray<T> implements CamList<T>, Iterable<T> {
	
	private T[] arr;
	private int size;
	private int allocatedSize;
	
	public CamArray(int allocatedSize) {
		if(allocatedSize < 0) {
			throw new IllegalArgumentException("Array allocation must be at least 0.");
		}
		arr = (T[]) new Object[allocatedSize];
		size = 0;
		this.allocatedSize = allocatedSize;
	}

	@Override
	public int getSize() {
		return size;
	}
	
	public T get(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index);
		}
		return arr[index];
	}
	
	public T[] getArr() {
		return Arrays.copyOf(arr, size);
	}

	@Override
	public void add(T val) {
		if(needsResize()) {
			resize();
		}
		arr[size] = val;
		size++;
	}

	private void resize() {
		int newAllocatedSize = (allocatedSize + 1) * 2;
		T[] newArr = (T[]) new Object[newAllocatedSize];
		for(int i = 0; i < size; i++) {
			newArr[i] = arr[i];
		}
		arr = newArr;
	}

	private boolean needsResize() {
		if(size == allocatedSize) return true;
		return false;
	}

	@Override
	public void addAll(T[] vals) {
		for(T val: vals) {
			add(val);
		}
	}

	@Override
	public void addAll(List<T> vals) {
		for(T val: vals) {
			add(val);
		}
	}

	@Override
	public void addFront(T val) {
		shiftElements(1);
		arr[0] = val;
		size++;
	}
	
	@Override
	public void addFrontAll(T[] vals) {
		shiftElements(vals.length);
		for(int i = 0; i < vals.length; i++) {
			arr[i] = vals[i];
		}
		size+=vals.length;
	}

	@Override
	public void addFrontAll(List<T> vals) {
		shiftElements(vals.size());
		for(int i = 0; i < vals.size(); i++) {
			arr[i] = vals.get(i);
		}
		size+=vals.size();
	}
	
	private void shiftElements(int shiftLength) {
		int newAllocatedSize = allocatedSize;
		while(size + shiftLength - 1 >= newAllocatedSize) {
			newAllocatedSize = (newAllocatedSize + 1) * 2;
		}
		T[] newArr = (T[]) new Object[newAllocatedSize];
		for(int i = 0; i < size; i++) {
			newArr[i] = arr[i];
		}
		
		arr = newArr;
	
		for(int i = size - 1; i >= 0; i--) {
			arr[i + shiftLength] = arr[i];
		}
	}

	@Override
	public T remove() {
		if(size == 0) {
			throw new IllegalStateException("Cannot remove from empty array.");
		}
		size--;
		return arr[size];
	}

	@Override
	public T removeAll(T val) {
		if(size == 0) {
			throw new IllegalStateException("Cannot remove values from empty array.");
		}
		
		int slow = 0;
		int fast = 0;
		
		while(fast < size) {
			if(!arr[fast].equals(val)) {
				arr[slow] = arr[fast];
				slow++;
			}
			fast++;
		}
		
		size = slow;
		
		return val;
		
	}

	@Override
	public T removeFront() {
		if(size == 0) {
			throw new IllegalStateException("Cannot remove first value from empty array.");
		}
		
		T valueOfInterest = arr[0];
		
		for(int i = 0; i < size - 1; i++) {
			arr[i] = arr[i + 1];
		}
		
		size--;
		
		return valueOfInterest;
	}

	@Override
	public T remove(T val) {
		if(size == 0) {
			throw new IllegalStateException("Cannot remove, val:" + val + ", from empty array.");
		}
		int indexOfInterest = -1;
		T valueOfInterest = null;
		for(int i = 0; i < size; i++) {
			if(arr[i].equals(val)) {
				indexOfInterest = i;
				break;
			}
		}
		
		if(indexOfInterest >= 0) {
			valueOfInterest = arr[indexOfInterest];
			
			for(int i = indexOfInterest; i < size - 1; i++) {
				arr[i] = arr[i + 1];
			}
			size--;
		}

		return valueOfInterest;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		if(size > 1000) {
			releaseMemory();
		}
		size = 0;
	}

	private void releaseMemory() {
		arr = (T[]) new Object[0];
	}
	
}