package com.cameodud.camarray;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CamArrayTest {

    private CamArray<Integer> intArray;
    private CamArray<String> strArray;
    private CamArray<Integer> collectionArray;
    private CamArray<Integer> camArray;

    @Before
    public void setUp() {
        intArray = new CamArray<>(new Integer[]{1, 2, 3, 4});
        strArray = new CamArray<>(new String[]{"a", "b", "c"});
        collectionArray = new CamArray<>(Arrays.asList(5, 6, 7, 8));
        camArray = new CamArray<>(5);
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
    
    @Test
    public void testAdd() {
    	intArray.add(5);
    	List<Integer> list = intArray.toList();
    	assertEquals(Arrays.asList(1, 2, 3, 4, 5), list);
    }
    
    @Test
    public void testAddFromEmptyArray() {
    	CamArray<Integer> myArray = new CamArray(10);
    	myArray.add(5);
    	List<Integer> list = myArray.toList();
    	assertEquals(Arrays.asList(5), list);
    }
    
    @Test
    public void testAddNullElement() {
        intArray.add(null);
        List<Integer> list = intArray.toList();
        assertEquals(Arrays.asList(1, 2, 3, 4, null), list);
    }
    
    @Test
    public void testAddMultipleElements() {
        intArray.add(5);
        intArray.add(6);
        intArray.add(7);
        List<Integer> list = intArray.toList();
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), list);
    }
    
    @Test
    public void testAddOrderAfterResize() {
        CamArray<Integer> smallArray = new CamArray<>(2);
        smallArray.add(1);
        smallArray.add(2);
        smallArray.add(3); // This should trigger resizing
        smallArray.add(4);
        List<Integer> list = smallArray.toList();
        assertEquals(Arrays.asList(1, 2, 3, 4), list);
    }

    @Test
    public void testAddAtBoundary() {
        CamArray<Integer> boundaryArray = new CamArray<>(5);
        boundaryArray.add(1);
        boundaryArray.add(2);
        boundaryArray.add(3);
        boundaryArray.add(4);
        boundaryArray.add(5); // This should not trigger resizing
        List<Integer> list = boundaryArray.toList();
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), list);
    }

    @Test
    public void testGetSize() {
        CamArray<Integer> testArray = new CamArray<>(3);
        testArray.add(1);
        testArray.add(2);
        assertEquals(2, testArray.getSize());
        testArray.add(3);
        assertEquals(3, testArray.getSize());
        testArray.add(4); // This should trigger resizing
        assertEquals(4, testArray.getSize());
    }

    @Test
    public void testInsertAtBeginning() {
        intArray.insert(0, 0); // Insert 0 at index 0
        List<Integer> list = intArray.toList();
        assertEquals(Arrays.asList(0, 1, 2, 3, 4), list);
    }

    @Test
    public void testInsertAtEnd() {
        intArray.insert(5, intArray.getSize());
        List<Integer> list = intArray.toList();
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), list);
    }
    
    @Test
    public void testInsertInMiddle() {
        intArray.insert(99, 2); // Insert 99 at index 2
        List<Integer> list = intArray.toList();
        assertEquals(Arrays.asList(1, 2, 99, 3, 4), list);
    }

    @Test
    public void testInsertWithResize() {
        CamArray<Integer> smallArray = new CamArray<>(2);
        smallArray.add(1);
        smallArray.add(2);
        smallArray.insert(3, 1); // Insert 3 at index 1, causing resize
        List<Integer> list = smallArray.toList();
        assertEquals(Arrays.asList(1, 3, 2), list);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertNegativeIndex() {
        intArray.insert(99, -1); // Invalid index
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertIndexGreaterThanSize() {
        intArray.insert(99, intArray.getSize() + 1); // Invalid index
    }

    @Test
    public void testInsertIntoEmptyArray() {
        CamArray<Integer> emptyArray = new CamArray<>(10);
        emptyArray.insert(1, 0); // Insert 1 at index 0
        List<Integer> list = emptyArray.toList();
        assertEquals(Arrays.asList(1), list);
    }

    @Test
    public void testInsertMultipleElements() {
        intArray.insert(5, 0); // Insert 5 at index 0
        intArray.insert(6, 3); // Insert 6 at index 3
        intArray.insert(7, intArray.getSize()); // Insert 7 at the last index
        List<Integer> list = intArray.toList();
        assertEquals(Arrays.asList(5, 1, 2, 6, 3, 4, 7), list);
    }
    
    @Test
    public void testAddAllArrayToNonEmptyList() {
        Integer[] elements = {5, 6, 7};
        intArray.addAll(elements);
        List<Integer> list = intArray.toList();
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), list);
    }
    
    @Test
    public void testAddAllEmptyArray() {
        Integer[] elements = {};
        intArray.addAll(elements);
        List<Integer> list = intArray.toList();
        assertEquals(Arrays.asList(1, 2, 3, 4), list);
    }
    
    
    @Test
    public void testAddAllArrayWithNullElements() {
        Integer[] elements = {null, null};
        intArray.addAll(elements);
        List<Integer> list = intArray.toList();
        assertEquals(Arrays.asList(1, 2, 3, 4, null, null), list);
    }

    
    @Test
    public void testAddAllListToNonEmptyList() {
        List<Integer> elements = Arrays.asList(5, 6, 7);
        intArray.addAll(elements);
        List<Integer> list = intArray.toList();
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), list);
    }

    @Test
    public void testAddAllEmptyList() {
        List<Integer> elements = Collections.emptyList();
        intArray.addAll(elements);
        List<Integer> list = intArray.toList();
        assertEquals(Arrays.asList(1, 2, 3, 4), list);
    }

    @Test
    public void testAddAllListWithNullElements() {
        List<Integer> elements = Arrays.asList(null, null);
        intArray.addAll(elements);
        List<Integer> list = intArray.toList();
        assertEquals(Arrays.asList(1, 2, 3, 4, null, null), list);
    }
    @Test
    public void testAddAllArrayWithResize() {
        CamArray<Integer> smallArray = new CamArray<>(2);
        smallArray.add(1);
        smallArray.add(2);
        Integer[] elements = {3, 4, 5}; // This should trigger resizing
        smallArray.addAll(elements);
        List<Integer> list = smallArray.toList();
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), list);
    }

    @Test
    public void testAddAllListWithResize() {
        CamArray<Integer> smallArray = new CamArray<>(2);
        smallArray.add(1);
        smallArray.add(2);
        List<Integer> elements = Arrays.asList(3, 4, 5); // This should trigger resizing
        smallArray.addAll(elements);
        List<Integer> list = smallArray.toList();
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), list);
    }
    
    @Test
    public void testPopFromNonEmptyArray() {
        camArray.add(1);
        camArray.add(2);
        camArray.add(3);

        Integer element = camArray.pop();
        assertEquals(Integer.valueOf(3), element);
        assertEquals(2, camArray.getSize());
    }

    @Test
    public void testPopUntilEmpty() {
        camArray.add(1);
        camArray.add(2);

        camArray.pop();
        camArray.pop();

        assertEquals(0, camArray.getSize());
    }

    @Test
    public void testPopFromEmptyArrayThrowsException() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            camArray.pop();
        });

        String expectedMessage = "Cannot pop from an empty CamArray";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testPopWithTypeCastException() {
        CamArray<Object> camArrayWithDifferentTypes = new CamArray<>(5);
        camArrayWithDifferentTypes.add("String Element");
        camArrayWithDifferentTypes.add(100);

        camArrayWithDifferentTypes.pop(); // Remove integer, now a String remains

        Exception exception = assertThrows(ClassCastException.class, () -> {
            Integer element = (Integer) camArrayWithDifferentTypes.pop();
        });

        String expectedMessage = "Cannot cast element passed.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testPopWithEmptyInitialArray() {
        CamArray<Integer> emptyCamArray = new CamArray<>(0);
        
        emptyCamArray.add(1);
        Integer element = emptyCamArray.pop();
        
        assertEquals(Integer.valueOf(1), element);
        assertEquals(0, emptyCamArray.getSize());
    }

    @Test
    public void testPopFromCamArrayCreatedWithCollection() {
        List<Integer> elements = new ArrayList<>();
        elements.add(1);
        elements.add(2);
        elements.add(3);
        
        CamArray<Integer> camArrayFromCollection = new CamArray<>(elements);
        
        Integer element = camArrayFromCollection.pop();
        assertEquals(Integer.valueOf(3), element);
        assertEquals(2, camArrayFromCollection.getSize());
    }

    @Test
    public void testPopFromCamArrayCreatedWithArray() {
        Integer[] elements = {1, 2, 3};
        
        CamArray<Integer> camArrayFromArray = new CamArray<>(elements);
        
        Integer element = camArrayFromArray.pop();
        assertEquals(Integer.valueOf(3), element);
        assertEquals(2, camArrayFromArray.getSize());
    }
}
