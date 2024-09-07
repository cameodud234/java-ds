package com.cameodud.camarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cameodud.camList.CamList;

public class CamArray<T> implements CamList<T> {
	
	private T[] arr;
	private int size;
	private int allocatedSize;

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public void add(T val) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addAll(T[] vals) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAll(List<T> vals) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFront(T val) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T[] removeAll(T[] vals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> removeAll(List<T> vals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T removeFront() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T remove(int val) {
		// TODO Auto-generated method stub
		return null;
	}
	
}