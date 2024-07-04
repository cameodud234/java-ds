package com.cameodud.camarray;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CamArrayTest {

    private CamArray<Integer> intArray;
    private CamArray<String> strArray;
    private CamArray<Integer> collectionArray;

    @Before
    public void setUp() {
        intArray = new CamArray<>(new Integer[]{1, 2, 3, 4});
        strArray = new CamArray<>(new String[]{"a", "b", "c"});
        collectionArray = new CamArray<>(Arrays.asList(5, 6, 7, 8));
    }

    @Test
    public void testCamArrayFromSize() {
        CamArray<Integer> arrayFromSize = new CamArray<>(10);
        assertEquals(0, arrayFromSize.getSize());
    }

    @Test
    public void testCamArrayFromArray() {
        assertEquals(4, intArray.getSize());
        assertEquals(3, strArray.getSize());
    }

    @Test
    public void testCamArrayFromCollection() {
        assertEquals(4, collectionArray.getSize());
    }

    @Test
    public void testEmptyArray() {
        CamArray<Integer> emptyArray1 = new CamArray<>(new Integer[]{});
        assertEquals(0, emptyArray1.getSize());
    }

    @Test
    public void testEmptyCollectionArray() {
        CamArray<Integer> emptyCollectionArray = new CamArray<>(Arrays.asList());
        assertEquals(0, emptyCollectionArray.getSize());
    }

    @Test
    public void testNullArrayThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CamArray<Integer>((Integer[]) null);
        });
        assertEquals("Input CamArray cannot be null", exception.getMessage());
    }

    @Test
    public void testNegativeSizeThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CamArray<Integer>(-1);
        });
        assertTrue(exception.getMessage().contains("needs to be a non-negative integer"));
    }
    
    @Test
    public void testToArray() {
        Object[] array = intArray.toArray();
        assertArrayEquals(new Integer[]{1, 2, 3, 4}, array);
    }

    @Test
    public void testToList() {
        List<Integer> list = collectionArray.toList();
        assertEquals(Arrays.asList(5, 6, 7, 8), list);
    }
    
}
