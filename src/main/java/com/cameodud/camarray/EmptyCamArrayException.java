package com.cameodud.camarray;

public class EmptyCamArrayException extends Exception {

    private static final long serialVersionUID = 1L;

    public EmptyCamArrayException() {
        super(""); // Provide a default message
    }

    public EmptyCamArrayException(String message) {
        super(message); // Allow custom messages
    }
}