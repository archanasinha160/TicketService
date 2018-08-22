package com.ticket.exception;

/**
 * Exception that is raised when input details for seat reservation are not valid
 */
public class SeatReservationNotValidException extends RuntimeException{

    public SeatReservationNotValidException(String message) {
        super(message);
    }
}
