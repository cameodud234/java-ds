package com.cameodud.array;

public class Array<T> {
	
	private T[] allocatedArray;
	private Class<T> type;
	private int size;
	private int maxSize;
	
	public Array(int size, Class<T> type) {
		this.type = type;
		this.parameterCheck(size, type);
		this.allocatedArray = (T[]) new Object[size];
		this.maxSize = size;
		this.size = 0;
	}
	
	public final Class<T> getType() {
		return type;
	}

	public final int getSize() {
		return size;
	}

	public final int getAllocatedSize() {
		return maxSize;
	}

	private void parameterCheck(final int size, final Class<T> type) {
		if ((!type.equals(Integer.class)) && (!type.equals(Double.class))) {
			throw new IllegalArgumentException("Only Integer and Double types are supported.");
		}
		if (size < 0) {
			String arg = String.format("size: %d, needs to be a positive integer.", allocatedArray);
			throw new IllegalArgumentException(arg);
		}
	}

}
