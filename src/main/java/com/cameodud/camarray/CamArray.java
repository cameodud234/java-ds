package com.cameodud.camarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class CamArray<T> implements Cloneable, java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Object[] allocatedArray;
    private int size;
    private int allocatedSize;

    private static final Object[] EMPTY_ALLOCATED_ARRAY = {};

    public CamArray(int size) {
        parameterCheck(size);
        if(size == 0) this.allocatedArray = EMPTY_ALLOCATED_ARRAY;
        else allocatedArray = new Object[size];
        allocatedSize = size;
        this.size = 0;
    }

    public CamArray(T[] array) {
        parameterCheck(array);
        allocatedArray = Arrays.copyOf(array, array.length);
        this.size = array.length;
        this.allocatedSize = array.length;
    }

    public CamArray(Collection<? extends T> elements) {
        Objects.requireNonNull(elements, "Collection cannot be null");
        Object[] items = elements.toArray();
        int size = items.length;
        if (size != 0) {
            allocatedArray = Arrays.copyOf(items, size, Object[].class);
        } else {
            allocatedArray = EMPTY_ALLOCATED_ARRAY;
        }
        this.size = size;
        this.allocatedSize = size;
    }

    public void add(T element) {
        if (size == allocatedSize) {
            addSpace();
        }
        allocatedArray[size] = element;
        size++;
    }

    public void addAll(T[] elements) {
        for (T element : elements) {
            add(element);
        }
    }

    public void addAll(List<T> elements) {
        for (T element : elements) {
            add(element);
        }
    }

    public void insert(T element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(String.format("Index: %d, must be a positive integer within bounds.", index));
        }

        if (index == size) {
            add(element);
            return;
        }

        if (size == allocatedSize) {
            addSpace();
        }

        for (int i = size; i > index; i--) {
            allocatedArray[i] = allocatedArray[i - 1];
        }

        allocatedArray[index] = element;
        size++;
    }

    public T pop() {
        if (size == 0) {
            throw new IllegalStateException("Cannot pop from an empty CamArray");
        }
        try {
        	T element = (T) allocatedArray[size - 1];
            allocatedArray[size - 1] = null;
            size--;
            return element;
        }
        catch(ClassCastException ce) {
        	throw new ClassCastException("Cannot cast element passed.\n\n" + ce.getMessage());
        }
    }

    public Object[] toArray() {
        return Arrays.copyOf(allocatedArray, size);
    }

    public List<T> toList() {
        try {
            T[] elements = Arrays.copyOf((T[]) allocatedArray, size);
            List<T> returnElements = new ArrayList<>(size);
            for (T element : elements) {
                returnElements.add(element);
            }
            return returnElements;
        } catch (ClassCastException ce) {
            throw new ClassCastException("CamArray element cannot be cast to generic type.");
        }
    }

    public final int getSize() {
        return size;
    }

    private void parameterCheck(final int size) {
        if (size < 0) {
            String arg = String.format("Size: %d, needs to be a non-negative integer.", size);
            throw new IllegalArgumentException(arg);
        }
    }

    private void parameterCheck(T[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
    }

    private void addSpace() {
    	if (this.allocatedSize == 0) {
    		this.allocatedArray = new Object[1];
    		this.allocatedSize++;
    		return;
    	}
        int newAllocatedSize =  2 * this.allocatedSize;
        this.allocatedArray = Arrays.copyOf(this.allocatedArray, newAllocatedSize);
        this.allocatedSize = newAllocatedSize;
    }
}
