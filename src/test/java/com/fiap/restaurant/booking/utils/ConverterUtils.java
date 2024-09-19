package com.fiap.restaurant.booking.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class ConverterUtils {

    @SneakyThrows
    public static String toJsonString(final Object object) {
        return new ObjectMapper()
                .writeValueAsString(object);
    }
}
