package com.cameodud.array;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayTest {

    @Test
    public void testValidIntegerArrayCreation() {
        Array<Integer> intArray = new Array<>(10, Integer.class);
        assertNotNull(intArray);
    }

    @Test
    public void testValidDoubleArrayCreation() {
        Array<Double> doubleArray = new Array<>(10, Double.class);
        assertNotNull(doubleArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidStringArrayCreation() {
        Array<String> stringArray = new Array<>(10, String.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidBooleanArrayCreation() {
        Array<Boolean> booleanArray = new Array<>(10, Boolean.class);
    }

    @Test
    public void testArrayInitialization() {
        int size = 10;
        Array<Integer> intArray = new Array<>(size, Integer.class);
        assertEquals(size, intArray.getAllocatedSize());
        assertEquals(0, intArray.getSize());
    }
}
