package com.fiap.restaurant.booking.core.exception;

public class ConvertModelToEntityException extends RuntimeException {

    public ConvertModelToEntityException(Class<?> tClass) {
        super(String.format("Failed to convert %s model to entity",tClass.getSimpleName()));
    }
}

