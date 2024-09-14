package com.cameodud.camList;

public class EmptyCamListException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7008076742637321314L;

	public EmptyCamListException() {
        super(""); // Provide a default message
    }

    public EmptyCamListException(String message) {
        super(message); // Allow custom messages
    }
    
}