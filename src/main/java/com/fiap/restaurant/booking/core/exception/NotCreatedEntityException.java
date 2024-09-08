package com.fiap.restaurant.booking.core.exception;

public class NotCreatedEntityException  extends RuntimeException {

    public NotCreatedEntityException(Class<?> tClass) {
        super(String.format("%s entity not created", tClass.getName()));
    }
}
