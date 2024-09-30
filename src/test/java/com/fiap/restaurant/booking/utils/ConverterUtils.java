package com.fiap.restaurant.booking.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fiap.restaurant.booking.core.domains.Reserva;
import lombok.SneakyThrows;

public class ConverterUtils {

    @SneakyThrows
    public static String toJsonString(final Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(object);
    }

    @SneakyThrows
    public static Reserva toReserva(final String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper
                .readValue(response, Reserva.class);
    }
}
