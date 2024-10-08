package com.cameodud.camlinkedlist;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import java.util.List;

public class CamLinkedListTest {

    @Test
    public void testInsertHead() {
        CamLinkedList list = new CamLinkedList();
        list.insertHead(10);
        list.insertHead(20);

        List<Integer> values = list.getValues();
        assertEquals(2, values.size());
        assertEquals(20, (int) values.get(0));  // Newest element at head
        assertEquals(10, (int) values.get(1));  // Previous head element shifted down
    }

    @Test
    public void testInsertTail() {
        CamLinkedList list = new CamLinkedList();
        list.insertTail(10);
        list.insertTail(20);

        List<Integer> values = list.getValues();
        assertEquals(2, values.size());
        assertEquals(10, (int) values.get(0));  // First element
        assertEquals(20, (int) values.get(1));  // Last inserted element at tail
    }

    @Test
    public void testGet() {
        CamLinkedList list = new CamLinkedList();
        list.insertHead(10);
        list.insertTail(20);
        list.insertTail(30);

        assertEquals(10, list.get(0));  // Head
        assertEquals(20, list.get(1));  // Middle
        assertEquals(30, list.get(2));  // Tail

        assertEquals(-1, list.get(3));  // Out of bounds should return -1
    }

    @Test
    public void testRemove() {
        CamLinkedList list = new CamLinkedList();
        list.insertHead(10);
        list.insertTail(20);
        list.insertTail(30);

        assertTrue(list.remove(1));  // Remove middle element (20)
        List<Integer> values = list.getValues();
        assertEquals(2, values.size());
        assertEquals(10, (int) values.get(0));  // Head should remain
        assertEquals(30, (int) values.get(1));  // Tail should shift up

        assertTrue(list.remove(0));  // Remove head element (10)
        values = list.getValues();
        assertEquals(1, values.size());
        assertEquals(30, (int) values.get(0));  // Only tail remains

        assertTrue(list.remove(0));  // Remove last element (30)
        values = list.getValues();
        assertEquals(0, values.size());  // List should now be empty

        assertFalse(list.remove(0));  // Removing from an empty list should return false
    }

    @Test
    public void testGetValues() {
        CamLinkedList list = new CamLinkedList();
        list.insertHead(10);
        list.insertTail(20);
        list.insertTail(30);

        List<Integer> values = list.getValues();
        assertEquals(3, values.size());
        assertEquals(10, (int) values.get(0));  // Head
        assertEquals(20, (int) values.get(1));  // Middle
        assertEquals(30, (int) values.get(2));  // Tail
    }

    @Test
    public void testRemoveOutOfBounds() {
        CamLinkedList list = new CamLinkedList();
        list.insertHead(10);
        list.insertTail(20);

        assertFalse(list.remove(5));  // Out of bounds remove should return false
        assertFalse(list.remove(-1)); // Negative index should return false
    }
}