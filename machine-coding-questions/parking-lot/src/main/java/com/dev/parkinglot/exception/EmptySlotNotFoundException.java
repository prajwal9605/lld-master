package com.dev.parkinglot.exception;

public class EmptySlotNotFoundException extends RuntimeException {
    public EmptySlotNotFoundException(String message) {
        super(message);
    }
}
