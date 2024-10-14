package com.cameodud.cambst;

import java.util.ArrayList;
import java.util.List;

public class CamBST {
	
	public Node root;
	
	public CamBST() {
		root = null;
	}
	
	public CamBST(int val) {
		root = new Node(val);
	}
	
	// Add elements to tree
	public void add(int val) {
		if(root == null) {
			root = new Node(val);
			return;
		}
		addHelper(root, val);
	}
	
	private void addHelper(Node current, int val) {
		if(val > current.val) {
			if(current.right == null) {
				current.right = new Node(val);
				return;
			}
			addHelper(current.right, val);
		}
		else if(val < current.val) {
			if(current.left == null) {
				current.left = new Node(val);
				return;
			}
			addHelper(current.left, val);
		}
	}
	
	// Searches for element in tree.
	public boolean search(int val) {
		if(searchHelper(root, val) != null) {
			return true;
		}
		return false;
	}
	
	private Node searchHelper(Node current, int val) {
		if(current == null) {
			return null;
		}
		if(val < current.val) {
			return searchHelper(current.left, val);
		}
		else if(val > current.val) {
			return searchHelper(current.right, val);
		}
		
		return current;
	}
	
	// Removes element from tree
	public void remove(int val) {
		removeHelper(root, val);
	}
	
	private Node removeHelper(Node current, int val) {
		if(current == null) {
			return null;
		}
		
		if(val < current.val) {
			if(current.left != null) {
				current.left = removeHelper(current.left, val);
			}
		}
		else if(val > current.val) {
			if(current.right != null) {
				current.right = removeHelper(current.right, val);
			}
		}
		else {
			
			if(current.left == null && current.right == null) {
				current = null;
			}
			else if(current.left == null) {
				current = current.right;
			}
			else if(current.right == null) {
				current = current.left;
			}
			else {
				Node tmp = findMin(current.right);
				current.val = tmp.val;
				current.right = removeHelper(current.right, tmp.val);
			}
			
		}
		
		return current;
	}

	// Gets elements in BST
	public String traverse(TraversalType type) {
		switch (type) {
			case INORDER:
				return inOrderTraversal();
				
			case PREORDER:
				return preOrderTraversal();
				
			case POSTORDER:
				return postOrderTraversal();
	
			default:
				throw new IllegalArgumentException("Argument must be of type TraversalType");
		}
	}
	

	private String postOrderTraversal() {
		List<String> elements = new ArrayList<>();
		postOrderTraversalHelper(root, elements);
		return String.join(", ", elements);
	}

	private void postOrderTraversalHelper(Node current, List<String> elements) {
		if(current == null) return;
		postOrderTraversalHelper(current.left, elements);
		postOrderTraversalHelper(current.right, elements);
		elements.add(Integer.toString(current.val));
	}

	private String preOrderTraversal() {
		List<String> elements = new ArrayList<>();
		preOrderTraversalHelper(root, elements);
		return String.join(", ", elements);
	}

	private void preOrderTraversalHelper(Node current, List<String> elements) {
		if(current == null) return;
		elements.add(Integer.toString(current.val));
		preOrderTraversalHelper(current.left, elements);
		preOrderTraversalHelper(current.right, elements);
	}

	private String inOrderTraversal() {
		List<String> elements = new ArrayList<>();
		inOrderTraversalHelper(root, elements);
		return String.join(", ", elements);
	}

	private void inOrderTraversalHelper(Node current, List<String> elements) {
		if(current == null) return;
		inOrderTraversalHelper(current.left, elements);
		elements.add(Integer.toString(current.val));
		inOrderTraversalHelper(current.right, elements);
	}

	// Finds smallest element
	public int getMin() {
		assert(root != null);
		Node current = root;
		while(current.left != null) {
			current = current.left;
		}
		return current.val;
	}
	
	// Finds smallest element node
	private Node findMin(Node node) {
		assert(node != null);
		Node current = node;
		while(current.left != null) {
			current = current.left;
		}
		return current;
	}
	
	// Finds largest element
	public int getMax() {
		assert(root != null);
		Node current = root;
		while(current.right != null) {
			current = current.right;
		}
		return current.val;
	}
	
	// Finds largest element node
	private Node findMax(Node node) {
		assert(root != null);
		Node current = root;
		while(current.right != null) {
			current = current.right;
		}
		return current;
	}
	
	// Finds height of bst.
	public int getHeight() {
		return getHeightHelper(root);
	}
	
	private int getHeightHelper(Node current) {
		if(current == null) {
			return 0;
		}
		
		int leftHeight = getHeightHelper(current.left);
		int rightHeight = getHeightHelper(current.right);
		
		return Math.max(leftHeight, rightHeight) + 1;
		
	}
	
	// Counts total node in tree
	public int size() {
		return sizeHelper(root);
	}
	
	private int sizeHelper(Node current) {
		if(current == null) {
			return 0;
		}
		int leftCount = sizeHelper(current.left);
		int rightCount = sizeHelper(current.right);
		
		return leftCount + rightCount + 1;
		
	}

	// Find Kth largest element
	public int kLargest(int k) {
		if(root == null) {
			throw new IllegalStateException("Tree must have nodes provided.");
		}
		return kLargestHelper(root, new int[] {0}, k);
	}
	
	private int kLargestHelper(Node current, int[] counter, int k) {
		
		if(current == null) {
			return Integer.MIN_VALUE;
		}
		
		int right = kLargestHelper(current.right, counter, k);
		
		if(right != Integer.MIN_VALUE) {
			return right;
		}
		
		counter[0]++;
		
		if(counter[0] == k) {
			return current.val;
		}
		
		
		return kLargestHelper(current.left, counter, k);
		
	}

	private class Node {
	    public Node left;
	    public Node right;
	    public int val;
	    
	    public Node() {
	        left = null;
	        right = null;
	        val = Integer.MIN_VALUE;
	    }
	    
	    public Node(int val) {
	        left = null;
	        right = null;
	        this.val = val;
	    }
	    
	    public Node(int val, Node left, Node right) {
	        this.left = left;
	        this.right = right;
	        this.val = val;
	    }
	    
	}

}
