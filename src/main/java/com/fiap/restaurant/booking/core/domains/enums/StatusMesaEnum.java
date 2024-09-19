package com.fiap.restaurant.booking.core.domains.enums;

import java.util.Optional;
import java.util.stream.Stream;

public enum StatusMesaEnum {
    DISPONIVEL,
    RESERVADA;

    public static Optional<StatusMesaEnum> toEnum(final String value) {
        return Stream.of(values())
                .filter(status -> status.name().equalsIgnoreCase(value))
                .findFirst();
    }
}
