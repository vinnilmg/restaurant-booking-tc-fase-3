package com.fiap.restaurant.booking.core.domains.enums;

import java.util.Optional;
import java.util.stream.Stream;

public enum StatusReservaEnum {
    CANCELADA,
    SOLICITADA,
    CONFIRMADA;

    public static Optional<StatusReservaEnum> toEnum(final String value) {
        return Stream.of(values())
                .filter(status -> status.name().equalsIgnoreCase(value))
                .findFirst();
    }
}
