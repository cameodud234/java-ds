package com.cameodud.camlinkedlist;

public class EmptyCamLinkedListException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyCamLinkedListException() {
        super(""); // Provide a default message
    }

    public EmptyCamLinkedListException(String message) {
        super(message); // Allow custom messages
    }
}
