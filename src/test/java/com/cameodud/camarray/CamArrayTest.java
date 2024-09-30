package com.cameodud.camarray;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CamArrayTest {

    @Test
    public void testZeroSizeConstruction() {
        // Test that construction with size 0 is allowed
        CamArray<Integer> camArray = new CamArray<>(0);
        assertEquals(0, camArray.getSize());
    }

    @Test
    public void testNegativeSizeConstruction() {
        // Test that construction with a negative size throws an exception
        assertThrows(IllegalArgumentException.class, () -> new CamArray<Integer>(-5));
    }
    
    @Test 
    public void testAdd() {
        CamArray<Integer> camArray = new CamArray<>(0);
        camArray.add(3);
    }
    
    @Test
    public void testAddFront() {
        // Initialize the CamArray with a size of 3
        CamArray<Integer> camArray = new CamArray<>(3);

        // Add elements to the front
        camArray.addFront(10);
        camArray.addFront(20);
        camArray.addFront(30);

        // Check if the elements are in the correct order
        Integer[] expectedArray1 = {30, 20, 10};
        assertArrayEquals(expectedArray1, camArray.getArr());

        // Add another element to trigger resize
        camArray.addFront(40);

        // After resizing, the elements should still be in the correct order
        Integer[] expectedArray2 = {40, 30, 20, 10};
        assertArrayEquals(expectedArray2, camArray.getArr());

        // Check if the size and current index are correct
        assertEquals(4, camArray.getSize());
        assertTrue(camArray.getSize() >= 4);
    }
    
    @Test
    public void testAddFrontArray() {
        CamArray<Integer> camArray = new CamArray<>(5);
        Integer[] valuesToAdd = {10, 20, 30, 40, 50};

        camArray.addAll(valuesToAdd);

        // Verify that the elements were added correctly
        assertArrayEquals(valuesToAdd, camArray.getArr());
        assertEquals(5, camArray.getSize());
    }
    
    @Test
    public void testAddFrontArrayMultiple() {
        CamArray<Integer> camArray = new CamArray<>(5);
        camArray.add(37);
        camArray.add(69);
        Integer[] valuesToAdd = {10, 20, 30, 40, 50};

        camArray.addFrontAll(valuesToAdd);

        // Verify that the elements were added correctly
        assertArrayEquals(new Integer[] {10,20,30,40,50,37,69}, camArray.getArr());
        assertEquals(7, camArray.getSize());
    }
    
    @Test
    public void testAddFrontArrayMultipleEmpty() {
        CamArray<Integer> camArray = new CamArray<>(5);
        Integer[] valuesToAdd = {10, 20, 30, 40, 50};

        camArray.addFrontAll(valuesToAdd);

        // Verify that the elements were added correctly
        assertArrayEquals(new Integer[] {10,20,30,40,50}, camArray.getArr());
        assertEquals(5, camArray.getSize());
    }
    
    @Test
    public void testRemoveEmptyArray() {
        CamArray<Integer> camArray = new CamArray<>(5);
        assertThrows(IllegalStateException.class, () -> {
            camArray.remove(); 
        });
    }

    @Test
    public void testRemoveSingleElement() {
        CamArray<Integer> camArray = new CamArray<>(1); 
        camArray.add(5); 

        int removedValue = camArray.remove();
        assertEquals(5, removedValue);
        assertEquals(0, camArray.getSize()); 
    }

    @Test
    public void testRemoveMultipleElements() {
        CamArray<Integer> camArray = new CamArray<>(3); 
        camArray.add(3);
        camArray.add(7);
        camArray.add(1);

        int removedValue1 = camArray.remove();
        assertEquals(1, removedValue1);
        assertEquals(2, camArray.getSize());

        int removedValue2 = camArray.remove();
        assertEquals(7, removedValue2);
        assertEquals(1, camArray.getSize());
    }
    
    @Test
    public void testRemoveFront_EmptyArray() {
        CamArray<Integer> camArray = new CamArray<>(5);
        assertThrows(IllegalStateException.class, () -> {
            camArray.removeFront();
        });
    }

    @Test
    public void testRemoveFrontSingleElement() {
        CamArray<Integer> camArray = new CamArray<>(1);
        camArray.add(5);

        int removedValue = camArray.removeFront();
        assertEquals(5, removedValue);
        assertEquals(0, camArray.getSize());
    }

    @Test
    public void testRemoveFrontMultipleElements() {
        CamArray<Integer> camArray = new CamArray<>(3);
        camArray.add(3);
        camArray.add(7);
        camArray.add(1);

        int removedValue1 = camArray.removeFront();
        assertEquals(3, removedValue1);
        assertEquals(2, camArray.getSize());
        assertArrayEquals(new Integer[]{7, 1}, camArray.getArr()); // Check if elements shifted correctly

        int removedValue2 = camArray.removeFront();
        assertEquals(7, removedValue2);
        assertEquals(1, camArray.getSize());
        assertArrayEquals(new Integer[]{1}, camArray.getArr());
    }
    
    @Test
    public void testRemoveValueEmptyArray() {
        CamArray<Integer> camArray = new CamArray<>(5);
        assertThrows(IllegalStateException.class, () -> {
            camArray.remove(10); // Attempt to remove any value
        });
    }

    @Test
    public void testRemoveValueRemoveFront() {
        CamArray<Integer> camArray = new CamArray<>(3);
        camArray.add(3);
        camArray.add(7);
        camArray.add(1);

        int removedValue = camArray.remove(3); // Remove from front
        assertEquals(3, removedValue);
        assertEquals(2, camArray.getSize());
        assertArrayEquals(new Integer[]{7, 1}, camArray.getArr());
    }

    @Test
    public void testRemoveValueRemoveLast() {
        CamArray<Integer> camArray = new CamArray<>(3);
        camArray.add(3);
        camArray.add(7);
        camArray.add(1);

        int removedValue = camArray.remove(1); // Remove last added
        assertEquals(1, removedValue);
        assertEquals(2, camArray.getSize());
        assertArrayEquals(new Integer[]{3, 7}, camArray.getArr());
    }

    @Test
    public void testRemoveValueRemoveMiddle() {
        CamArray<Integer> camArray = new CamArray<>(3);
        camArray.add(3);
        camArray.add(7);
        camArray.add(1);

        int removedValue = camArray.remove(7); // Remove from middle
        assertEquals(7, removedValue);
        assertEquals(2, camArray.getSize());
        assertArrayEquals(new Integer[]{3, 1}, camArray.getArr());
    }
    
    
    @Test
    public void testRemoveValueRemoveMiddleMore() {
        CamArray<Integer> camArray = new CamArray<>(7);
        camArray.add(3);
        camArray.add(7);
        camArray.add(3);
        camArray.add(7);
        camArray.add(3);
        camArray.add(7);
        camArray.add(1);

        int removedValue = camArray.remove(7); // Remove from middle
        assertEquals(7, removedValue);
        assertEquals(6, camArray.getSize());
        assertArrayEquals(new Integer[]{3, 3, 7, 3, 7, 1}, camArray.getArr());
    }
    
    @Test
    public void testRemoveValueRemoveMiddleEvenMore() {
        CamArray<Integer> camArray = new CamArray<>(7);
        camArray.add(1);
        camArray.add(2);
        camArray.add(3);
        camArray.add(4);
        camArray.add(5);
        camArray.add(6);
        camArray.add(7);

        int removedValue = camArray.remove(4); // Remove from middle
        assertEquals(4, removedValue);
        assertEquals(6, camArray.getSize());
        assertArrayEquals(new Integer[]{1, 2, 3, 5, 6, 7}, camArray.getArr());
    }
    
    @Test
    public void testRemoveValueRemoveMiddleEvenMoreMultiple() {
        CamArray<Integer> camArray = new CamArray<>(7);
        camArray.add(1);
        camArray.add(2);
        camArray.add(3);
        camArray.add(4);
        camArray.add(5);
        camArray.add(6);
        camArray.add(7);

        int removedValue1 = camArray.remove(4); // Remove from middle
        assertEquals(4, removedValue1);
        assertEquals(6, camArray.getSize());
        assertArrayEquals(new Integer[]{1, 2, 3, 5, 6, 7}, camArray.getArr());
        
        int removedValue2 = camArray.remove(5); // Remove from middle
        assertEquals(5, removedValue2);
        assertEquals(5, camArray.getSize());
        assertArrayEquals(new Integer[]{1, 2, 3, 6, 7}, camArray.getArr());
    }
    
    @Test
    public void testRemoveValueRemoveValueEncapFront() {
        CamArray<Integer> camArray = new CamArray<>(7);
        camArray.add(1);
        camArray.add(2);
        camArray.add(3);
        camArray.add(4);
        camArray.add(5);
        camArray.add(6);
        camArray.add(7);

        int removedValue = camArray.remove(1);
        assertEquals(1, removedValue);
        assertEquals(6, camArray.getSize());
        assertArrayEquals(new Integer[]{2, 3, 4, 5, 6, 7}, camArray.getArr());
        
    }
    
    @Test
    public void testRemoveValueRemoveValueEncapBack() {
        CamArray<Integer> camArray = new CamArray<>(7);
        camArray.add(1);
        camArray.add(2);
        camArray.add(3);
        camArray.add(4);
        camArray.add(5);
        camArray.add(6);
        camArray.add(7);

        int removedValue = camArray.remove(7);
        assertEquals(7, removedValue);
        assertEquals(6, camArray.getSize());
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6}, camArray.getArr());
        
    }

    @Test
    public void testRemoveValue_ValueNotFound() {
        CamArray<Integer> camArray = new CamArray<>(3);
        camArray.add(3);
        camArray.add(7);
        camArray.add(1);

        Integer removedValue = camArray.remove(5); // Value not present
        assertEquals(null, removedValue);
        assertEquals(3, camArray.getSize());
        assertArrayEquals(new Integer[]{3, 7, 1}, camArray.getArr()); // Array unchanged
    }
    
    @Test
    public void testRemoveAll() {
    	CamArray<Integer> camArray = new CamArray<>(7);
        camArray.add(1);
        camArray.add(2);
        camArray.add(3);
        camArray.add(2);
        camArray.add(5);
        camArray.add(2);
        camArray.add(7);
        
        camArray.removeAll(2);
        assertArrayEquals(new Integer[]{1, 3, 5, 7}, camArray.getArr());
    }
    
 // Test where the value to remove is not in the array
    @Test
    public void testRemoveAllValueNotPresent() {
        CamArray<Integer> camArray = new CamArray<>(7);
        camArray.add(1);
        camArray.add(3);
        camArray.add(5);
        camArray.add(7);

        camArray.removeAll(2);  // 2 is not in the array
        assertArrayEquals(new Integer[]{1, 3, 5, 7}, camArray.getArr());
        assertEquals(4, camArray.getSize()); // size should remain the same
    }

    // Test where the array is empty
    @Test
    public void testRemoveAllFromEmptyArray() {
        CamArray<Integer> camArray = new CamArray<>(0);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
        	camArray.removeAll(2);
        });

        assertArrayEquals(new Integer[]{}, camArray.getArr());
        assertEquals(0, camArray.getSize()); // size should still be 0
    }

    // Test where all elements in the array match the value to remove
    @Test
    public void testRemoveAllElementsMatch() {
        CamArray<Integer> camArray = new CamArray<>(5);
        camArray.add(2);
        camArray.add(2);
        camArray.add(2);
        camArray.add(2);
        camArray.add(2);

        camArray.removeAll(2);
        assertArrayEquals(new Integer[]{}, camArray.getArr());
        assertEquals(0, camArray.getSize()); // size should be 0 after removing all elements
    }

    // Test where the array has only one element and it matches the value to remove
    @Test
    public void testRemoveAllSingleElement() {
        CamArray<Integer> camArray = new CamArray<>(1);
        camArray.add(2);

        camArray.removeAll(2);
        assertArrayEquals(new Integer[]{}, camArray.getArr());
        assertEquals(0, camArray.getSize()); // size should be 0 after removing the only element
    }

    // Test where the array has only one element and it does not match the value to remove
    @Test
    public void testRemoveAllSingleElementNoMatch() {
        CamArray<Integer> camArray = new CamArray<>(1);
        camArray.add(1);

        camArray.removeAll(2);  // 2 is not in the array
        assertArrayEquals(new Integer[]{1}, camArray.getArr());
        assertEquals(1, camArray.getSize()); // size should remain the same
    }
    

    @Test
    public void testGetValidIndex() {
        CamArray<Integer> camArray = new CamArray<Integer>(5);
        camArray.add(10);
        camArray.add(20);
        camArray.add(30);
        camArray.add(40);
        camArray.add(50);
        
        Integer[] ans = {10, 30, 50};

        assertEquals(ans[0], camArray.get(0));
        assertEquals(ans[1], camArray.get(2));
        assertEquals(ans[2], camArray.get(4));
    }

    @Test
    public void testGetInvalidNegativeIndex() {
        CamArray<Integer> camArray = new CamArray<>(5);
        camArray.add(10);
        camArray.add(20);
        camArray.add(30);

        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            camArray.get(-1);
        });
        String msg = String.format("Index out of range: %d", -1);
        assertEquals(msg, exception.getMessage());
    }
    
    @Test
    public void testAddAllFromArray() {
        CamArray<Integer> camArray = new CamArray<>(5);
        Integer[] valuesToAdd = {10, 20, 30, 40, 50};

        camArray.addAll(valuesToAdd);

        // Verify that the elements were added correctly
        assertArrayEquals(valuesToAdd, camArray.getArr());
        assertEquals(5, camArray.getSize());
    }
    

    @Test
    public void testAddAllFromArray_EmptyArray() {
        CamArray<Integer> camArray = new CamArray<>(5);
        Integer[] emptyValues = {};

        camArray.addAll(emptyValues);

        // Verify that nothing was added
        assertEquals(0, camArray.getSize());
        assertArrayEquals(new Integer[]{}, camArray.getArr());
    }

    @Test
    public void testAddAllFromList() {
        CamArray<Integer> camArray = new CamArray<>(5);
        List<Integer> valuesToAdd = Arrays.asList(10, 20, 30, 40, 50);

        camArray.addAll(valuesToAdd);

        // Verify that the elements were added correctly
        assertArrayEquals(new Integer[]{10, 20, 30, 40, 50}, camArray.getArr());
        assertEquals(5, camArray.getSize());
    }

    @Test
    public void testAddAllFromList_EmptyList() {
        CamArray<Integer> camArray = new CamArray<>(5);
        List<Integer> emptyValues = new ArrayList<>();

        camArray.addAll(emptyValues);

        // Verify that nothing was added
        assertEquals(0, camArray.getSize());
    }
    
}

    
