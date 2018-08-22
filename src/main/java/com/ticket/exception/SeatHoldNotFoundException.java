package com.ticket.exception;

/**
 * {@link RuntimeException} that is thrown when a seat hold is not found
 */
public class SeatHoldNotFoundException extends RuntimeException{

    public SeatHoldNotFoundException(String message) {
        super(message);
    }
}
