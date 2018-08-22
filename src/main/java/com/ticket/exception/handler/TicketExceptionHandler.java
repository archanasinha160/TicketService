package com.ticket.exception.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ticket.common.entity.ClientError;
import com.ticket.exception.CustomValidationException;
import com.ticket.exception.SeatHoldNotFoundException;
import com.ticket.exception.SeatReservationNotValidException;
import com.ticket.exception.TicketException;

import java.util.List;

/**
 * This particular implementation is a convenient to handle
 * exceptions specific to this application.
 */
@ControllerAdvice
public class TicketExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(TicketExceptionHandler.class);

    @ExceptionHandler({CustomValidationException.class})
    public ResponseEntity<List<ClientError>> handleNotValidException(CustomValidationException e){
        HttpStatus responseStatus = HttpStatus.BAD_REQUEST;
        List<ClientError> clientErrors = e.getClientErrors();
        LOGGER.error("Validation errors! Sending HTTP status \'{}\' along {}", responseStatus, clientErrors);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(clientErrors, headers, responseStatus);
    }

    @ExceptionHandler({SeatReservationNotValidException.class})
    public ResponseEntity<ClientError> handleSeatReservationNotValidException(SeatReservationNotValidException exception) {
        return new ResponseEntity<>(new ClientError("Seat hold", exception.getMessage(), "seatHold"), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Seat hold not found")
    @ExceptionHandler({SeatHoldNotFoundException.class})
    public void handleSeatHoldNotFoundException(SeatHoldNotFoundException exception) {
        LOGGER.debug("Seat hold not found");
    }

    @ExceptionHandler({TicketException.class})
    public ResponseEntity<String> handleTicketException(
            TicketException exception) {
        HttpStatus responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        LOGGER.error(
                "TicketException raised! Sending HTTP status \'{}\', caused by {}",
                responseStatus, exception.getCause());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>("Failure! Please try again later", headers, responseStatus);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleNotValidException(
            RuntimeException exception) {
        HttpStatus responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        LOGGER.error(
                "RuntimeException raised! Sending HTTP status \'{}\', caused by {}",
                responseStatus, exception);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>("Failure! Please try again later", headers, responseStatus);
    }
}
