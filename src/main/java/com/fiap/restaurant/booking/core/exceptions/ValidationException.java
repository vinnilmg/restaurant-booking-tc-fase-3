package com.fiap.restaurant.booking.core.exceptions;

import static java.lang.String.format;

public class ValidationException extends RuntimeException {
    private final String field;
    private final String message;

    public ValidationException(final String field, final String message) {
        super(message);
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    @Override
    public String getMessage() {
        return format("%s %s", field, message);
    }

    public static ValidationException of(final String field, final String message) {
        return new ValidationException(field, message);
    }
}
