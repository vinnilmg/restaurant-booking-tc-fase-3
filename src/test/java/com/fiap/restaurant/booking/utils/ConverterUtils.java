package com.fiap.restaurant.booking.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.restaurant.booking.core.domains.Reserva;
import lombok.SneakyThrows;

public class ConverterUtils {

    @SneakyThrows
    public static String toJsonString(final Object object) {
        return new ObjectMapper()
                .writeValueAsString(object);
    }

    @SneakyThrows
    public static Reserva toReserva(final String response) {
        return new ObjectMapper()
                .readValue(response, Reserva.class);
    }
}
