package com.ticket.exception;

import java.util.List;

import com.ticket.common.entity.ClientError;

/**
  * {@link RuntimeException} used for validation clientErrors
 */
public class CustomValidationException extends RuntimeException {

    private final List<ClientError> clientErrors;

    public CustomValidationException(final List<ClientError> clientErrors) {
        this.clientErrors = clientErrors;
    }

    public List<ClientError> getClientErrors() {
        return clientErrors;
    }
}
