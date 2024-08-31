package com.cameodud.camarray;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class CamArrayTest {
	
//	@Before

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
        assertTrue(camArray.getAllocatedSize() >= 4);  // Because of resizing
    }
    
    @Test
    public void testRemove_EmptyArray() {
        CamArray camArray = new CamArray(5); // Initialize with some capacity
        assertThrows(EmptyCamArrayException.class, () -> {
            camArray.remove(); 
        });
    }

    @Test
    public void testRemove_SingleElement() throws EmptyCamArrayException {
        CamArray camArray = new CamArray(1); 
        camArray.add(5); 

        int removedValue = camArray.remove();
        assertEquals(5, removedValue);
        assertEquals(0, camArray.getSize()); 
    }

    @Test
    public void testRemove_MultipleElements() throws EmptyCamArrayException {
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
        assertThrows(EmptyCamArrayException.class, () -> {
            camArray.removeFront();
        });
    }

    @Test
    public void testRemoveFront_SingleElement() throws EmptyCamArrayException {
        CamArray camArray = new CamArray(1);
        camArray.add(5);

        int removedValue = camArray.removeFront();
        assertEquals(5, removedValue);
        assertEquals(0, camArray.getSize());
    }

    @Test
    public void testRemoveFront_MultipleElements() throws EmptyCamArrayException {
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
    public void testRemoveValue_EmptyArray() {
        CamArray camArray = new CamArray(5);
        assertThrows(EmptyCamArrayException.class, () -> {
            camArray.remove(10); // Attempt to remove any value
        });
    }

    @Test
    public void testRemoveValue_RemoveFront() throws EmptyCamArrayException {
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
    public void testRemoveValue_RemoveLast() throws EmptyCamArrayException {
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
    public void testRemoveValue_RemoveMiddle() throws EmptyCamArrayException {
        CamArray camArray = new CamArray(4);
        camArray.add(3);
        camArray.add(7);
        camArray.add(1);

        int removedValue = camArray.remove(7); // Remove from middle
        assertEquals(7, removedValue);
        assertEquals(2, camArray.getSize());
        assertArrayEquals(new int[]{3, 1}, camArray.getArr());
    }
    
    
    @Test
    public void testRemoveValue_RemoveMiddleMore() throws EmptyCamArrayException {
        CamArray camArray = new CamArray(4);
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
    public void testRemoveValue_ValueNotFound() throws EmptyCamArrayException {
        CamArray camArray = new CamArray(3);
        camArray.add(3);
        camArray.add(7);
        camArray.add(1);

        int removedValue = camArray.remove(5); // Value not present
        assertEquals(Integer.MIN_VALUE, removedValue);
        assertEquals(3, camArray.getSize());
        assertArrayEquals(new int[]{3, 7, 1}, camArray.getArr()); // Array unchanged
    }

}