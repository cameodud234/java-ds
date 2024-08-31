package com.cameodud.main;

import com.cameodud.camarray.CamArray;

public class Main {
	public static void main(String[] args) {
		CamArray camArray = new CamArray(0);
		camArray.add(3);
		camArray.add(6);
		camArray.addFront(1);
		System.out.println(camArray);
	}
}
