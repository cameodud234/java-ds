package com.cameodud.camarray;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

public class CamArrayTest {

    @Test
    public void testValidConstruction() {
        // Test that construction with a valid size works as expected
        CamArray camArray = new CamArray(10);
        assertEquals(10, camArray.getAllocatedSize());
        assertEquals(0, camArray.getSize()); 
    }

    @Test
    public void testZeroSizeConstruction() {
        // Test that construction with size 0 is allowed
        CamArray camArray = new CamArray(0);
        assertEquals(0, camArray.getAllocatedSize());
        assertEquals(0, camArray.getSize());
    }

    @Test
    public void testNegativeSizeConstruction() {
        // Test that construction with a negative size throws an exception
        assertThrows(IllegalArgumentException.class, () -> new CamArray(-5));
    }
    
    @Test 
    public void testAdd() {
        CamArray camArray = new CamArray(0);
        camArray.add(3);
    }
    
    @Test
    public void testAllocatedSizeChangeZero() {
        CamArray camArray = new CamArray(0);
        assertEquals(0, camArray.getAllocatedSize());
        camArray.add(1);
        assertEquals(2, camArray.getAllocatedSize());
    }
    
    @Test 
    public void testAllocatedSizeChange() {
        CamArray camArray = new CamArray(10);
        assertEquals(10, camArray.getAllocatedSize());
        for(int i = 0; i < 10; i++) {
            camArray.add(i);
        }
        assertEquals(10, camArray.getAllocatedSize());
        camArray.add(10);
        assertEquals(22, camArray.getAllocatedSize());
    }
    
    @Test
    public void testAddFront() {
        // Initialize the CamArray with a size of 3
        CamArray camArray = new CamArray(3);

        // Add elements to the front
        camArray.addFront(10);
        camArray.addFront(20);
        camArray.addFront(30);

        // Check if the elements are in the correct order
        int[] expectedArray1 = {30, 20, 10};
        assertArrayEquals(expectedArray1, camArray.getArr());

        // Add another element to trigger resize
        camArray.addFront(40);

        // After resizing, the elements should still be in the correct order
        int[] expectedArray2 = {40, 30, 20, 10};
        assertArrayEquals(expectedArray2, camArray.getArr());

        // Check if the size and current index are correct
        assertEquals(4, camArray.getSize());
        assertEquals(3, camArray.getCurrentIndex());
        assertTrue(camArray.getAllocatedSize() >= 4);
    }
    
    @Test
    public void testRemoveEmptyArray() {
        CamArray camArray = new CamArray(5);
        assertThrows(IllegalStateException.class, () -> {
            camArray.remove(); 
        });
    }

    @Test
    public void testRemoveSingleElement() {
        CamArray camArray = new CamArray(1); 
        camArray.add(5); 

        int removedValue = camArray.remove();
        assertEquals(5, removedValue);
        assertEquals(0, camArray.getSize()); 
    }

    @Test
    public void testRemoveMultipleElements() {
        CamArray camArray = new CamArray(3); 
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
        CamArray camArray = new CamArray(5);
        assertThrows(IllegalStateException.class, () -> {
            camArray.removeFront();
        });
    }

    @Test
    public void testRemoveFrontSingleElement() {
        CamArray camArray = new CamArray(1);
        camArray.add(5);

        int removedValue = camArray.removeFront();
        assertEquals(5, removedValue);
        assertEquals(0, camArray.getSize());
    }

    @Test
    public void testRemoveFrontMultipleElements() {
        CamArray camArray = new CamArray(3);
        camArray.add(3);
        camArray.add(7);
        camArray.add(1);

        int removedValue1 = camArray.removeFront();
        assertEquals(3, removedValue1);
        assertEquals(2, camArray.getSize());
        assertArrayEquals(new int[]{7, 1}, camArray.getArr()); // Check if elements shifted correctly

        int removedValue2 = camArray.removeFront();
        assertEquals(7, removedValue2);
        assertEquals(1, camArray.getSize());
        assertArrayEquals(new int[]{1}, camArray.getArr());
    }
    
    @Test
    public void testRemoveValueEmptyArray() {
        CamArray camArray = new CamArray(5);
        assertThrows(IllegalStateException.class, () -> {
            camArray.remove(10); // Attempt to remove any value
        });
    }

    @Test
    public void testRemoveValueRemoveFront() {
        CamArray camArray = new CamArray(3);
        camArray.add(3);
        camArray.add(7);
        camArray.add(1);

        int removedValue = camArray.remove(3); // Remove from front
        assertEquals(3, removedValue);
        assertEquals(2, camArray.getSize());
        assertArrayEquals(new int[]{7, 1}, camArray.getArr());
    }

    @Test
    public void testRemoveValueRemoveLast() {
        CamArray camArray = new CamArray(3);
        camArray.add(3);
        camArray.add(7);
        camArray.add(1);

        int removedValue = camArray.remove(1); // Remove last added
        assertEquals(1, removedValue);
        assertEquals(2, camArray.getSize());
        assertArrayEquals(new int[]{3, 7}, camArray.getArr());
    }

    @Test
    public void testRemoveValueRemoveMiddle() {
        CamArray camArray = new CamArray(3);
        camArray.add(3);
        camArray.add(7);
        camArray.add(1);

        int removedValue = camArray.remove(7); // Remove from middle
        assertEquals(7, removedValue);
        assertEquals(2, camArray.getSize());
        assertArrayEquals(new int[]{3, 1}, camArray.getArr());
    }
    
    
    @Test
    public void testRemoveValueRemoveMiddleMore() {
        CamArray camArray = new CamArray(7);
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
        assertArrayEquals(new int[]{3, 3, 7, 3, 7, 1}, camArray.getArr());
    }
    
    @Test
    public void testRemoveValueRemoveMiddleEvenMore() {
        CamArray camArray = new CamArray(7);
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
        assertArrayEquals(new int[]{1, 2, 3, 5, 6, 7}, camArray.getArr());
    }
    
    @Test
    public void testRemoveValueRemoveMiddleEvenMoreMultiple() {
        CamArray camArray = new CamArray(7);
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
        assertArrayEquals(new int[]{1, 2, 3, 5, 6, 7}, camArray.getArr());
        
        int removedValue2 = camArray.remove(5); // Remove from middle
        assertEquals(5, removedValue2);
        assertEquals(5, camArray.getSize());
        assertArrayEquals(new int[]{1, 2, 3, 6, 7}, camArray.getArr());
    }
    
    @Test
    public void testRemoveValueRemoveValueEncapFront() {
        CamArray camArray = new CamArray(7);
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
        assertArrayEquals(new int[]{2, 3, 4, 5, 6, 7}, camArray.getArr());
        
    }
    
    @Test
    public void testRemoveValueRemoveValueEncapBack() {
        CamArray camArray = new CamArray(7);
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
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, camArray.getArr());
        
    }

    @Test
    public void testRemoveValue_ValueNotFound() {
        CamArray camArray = new CamArray(3);
        camArray.add(3);
        camArray.add(7);
        camArray.add(1);

        int removedValue = camArray.remove(5); // Value not present
        assertEquals(Integer.MIN_VALUE, removedValue);
        assertEquals(3, camArray.getSize());
        assertArrayEquals(new int[]{3, 7, 1}, camArray.getArr()); // Array unchanged
    }

    @Test
    public void testGetValidIndex() {
        CamArray camArray = new CamArray(5);
        camArray.add(10);
        camArray.add(20);
        camArray.add(30);
        camArray.add(40);
        camArray.add(50);

        assertEquals(10, camArray.get(0));
        assertEquals(30, camArray.get(2));
        assertEquals(50, camArray.get(4));
    }

    @Test
    public void testGetInvalidNegativeIndex() {
        CamArray camArray = new CamArray(5);
        camArray.add(10);
        camArray.add(20);
        camArray.add(30);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            camArray.get(-1);
        });
        assertEquals("Index not in range of array.", exception.getMessage());
    }
    
    @Test
    public void testAddAllFromArray() {
        CamArray camArray = new CamArray(5);
        int[] valuesToAdd = {10, 20, 30, 40, 50};

        camArray.addAll(valuesToAdd);

        // Verify that the elements were added correctly
        assertArrayEquals(valuesToAdd, camArray.getArr());
        assertEquals(5, camArray.getSize());
    }

    @Test
    public void testAddAllFromArray_EmptyArray() {
        CamArray camArray = new CamArray(5);
        int[] emptyValues = {};

        camArray.addAll(emptyValues);

        // Verify that nothing was added
        assertEquals(0, camArray.getSize());
        assertArrayEquals(new int[]{}, camArray.getArr());
    }

    @Test
    public void testAddAllFromList() {
        CamArray camArray = new CamArray(5);
        List<Integer> valuesToAdd = Arrays.asList(10, 20, 30, 40, 50);

        camArray.addAll(valuesToAdd);

        // Verify that the elements were added correctly
        assertArrayEquals(new int[]{10, 20, 30, 40, 50}, camArray.getArr());
        assertEquals(5, camArray.getSize());
    }

    @Test
    public void testAddAllFromList_EmptyList() {
        CamArray camArray = new CamArray(5);
        List<Integer> emptyValues = Arrays.asList();

        camArray.addAll(emptyValues);

        // Verify that nothing was added
        assertEquals(0, camArray.getSize());
        assertArrayEquals(new int[]{}, camArray.getArr());
    }

    @Test
    public void testAddAllFromArray_Resizing() {
        CamArray camArray = new CamArray(2); // Start with small capacity
        int[] valuesToAdd = {10, 20, 30, 40, 50};

        camArray.addAll(valuesToAdd);

        // Verify that the elements were added correctly and resizing occurred
        assertArrayEquals(valuesToAdd, camArray.getArr());
        assertEquals(5, camArray.getSize());
        assertTrue(camArray.getAllocatedSize() >= 5); // Ensure resizing occurred
    }

    @Test
    public void testAddAllFromList_Resizing() {
        CamArray camArray = new CamArray(2); // Start with small capacity
        List<Integer> valuesToAdd = Arrays.asList(10, 20, 30, 40, 50);

        camArray.addAll(valuesToAdd);

        // Verify that the elements were added correctly and resizing occurred
        assertArrayEquals(new int[]{10, 20, 30, 40, 50}, camArray.getArr());
        assertEquals(5, camArray.getSize());
        assertTrue(camArray.getAllocatedSize() >= 5); // Ensure resizing occurred
        
    }
    
}

    
