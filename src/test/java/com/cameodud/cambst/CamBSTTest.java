package com.cameodud.cambst;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CamBSTTest {

    private CamBST tree;

    @BeforeEach
    public void setup() {
        tree = new CamBST();
    }

    @Test
    public void testAddAndSearch() {
        tree.add(50);
        tree.add(30);
        tree.add(70);

        assertTrue(tree.search(50));
        assertTrue(tree.search(30));
        assertTrue(tree.search(70));
        assertFalse(tree.search(100));  // Element not present
    }
	
	@Test
	public void testRemoveLeafNode() {
		tree.add(50);
	    tree.add(30);
	    tree.add(70);
	
	    tree.remove(30);  // Removing leaf node
	    assertFalse(tree.search(30));
	    assertTrue(tree.search(50));
	    assertTrue(tree.search(70));
	}
	
	@Test
	public void testRemoveNodeWithOneChild() {
		tree.add(50);
	    tree.add(30);
	    tree.add(20);  // Left child of 30
	
	    tree.remove(30);  // Node with one child
	    assertFalse(tree.search(30));
	    assertTrue(tree.search(50));
	    assertTrue(tree.search(20));  // 20 should still be present
	}

    @Test
    public void testRemoveNodeWithTwoChildren() {
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(60);
        tree.add(80);

        tree.remove(70);  // Node with two children
        assertFalse(tree.search(70));
        assertTrue(tree.search(50));
        assertTrue(tree.search(60));
        assertTrue(tree.search(80));
    }

    @Test
    public void testGetMinAndGetMax() {
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(80);

        assertEquals(20, tree.getMin());  // Minimum value in the tree
        assertEquals(80, tree.getMax());  // Maximum value in the tree
    }

    @Test
    public void testSize() {
        assertEquals(0, tree.size());  // Empty tree

        tree.add(50);
        tree.add(30);
        tree.add(70);
        assertEquals(3, tree.size());  // Tree has 3 elements

        tree.remove(30);
        assertEquals(2, tree.size());  // Size after removing one element
    }

    @Test
    public void testHeight() {
        assertEquals(0, tree.getHeight());  // Height of empty tree

        tree.add(50);
        assertEquals(1, tree.getHeight());  // Tree with only root

        tree.add(30);
        tree.add(70);
        assertEquals(2, tree.getHeight());  // Height after adding more nodes

        tree.add(80);
        assertEquals(3, tree.getHeight());  // Height after unbalancing the tree
    }

    @Test
    public void testPreOrderTraversal() {
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);

        String expected = "50, 30, 20, 40, 70";  // Pre-order: Root -> Left -> Right
        assertEquals(expected, tree.traverse(TraversalType.PREORDER));
    }

    @Test
    public void testInOrderTraversal() {
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);

        String expected = "20, 30, 40, 50, 70";  // In-order: Left -> Root -> Right
        assertEquals(expected, tree.traverse(TraversalType.INORDER));
    }

    @Test
    public void testPostOrderTraversal() {
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);

        String expected = "20, 40, 30, 70, 50";  // Post-order: Left -> Right -> Root
        assertEquals(expected, tree.traverse(TraversalType.POSTORDER));
    }

    @Test
    public void testKLargest() {
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(60);
        tree.add(80);

        assertEquals(80, tree.kLargest(1));  // Largest element
        assertEquals(70, tree.kLargest(2));  // 2nd largest element
        assertEquals(60, tree.kLargest(3));  // 3rd largest element
    }

    @Test
    public void testRemoveRoot() {
        tree.add(50);
        tree.add(30);
        tree.add(70);

        tree.remove(50);  // Remove root
        assertFalse(tree.search(50));
        assertTrue(tree.search(30));
        assertTrue(tree.search(70));
    }

    @Test
    public void testRemoveFromEmptyTree() {
        tree.remove(50);  // Attempt to remove from an empty tree
        assertEquals(0, tree.size());
    }

    @Test
    public void testRemoveNonExistentElement() {
        tree.add(50);
        tree.add(30);
        tree.add(70);

        tree.remove(100);  // Element not in the tree
        assertEquals(3, tree.size());  // Size should remain the same
    }
}