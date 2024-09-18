package com.fiap.restaurant.booking.core.domains.enums;

import java.util.Optional;
import java.util.stream.Stream;

public enum TipoCulinariaEnum {
    BRASILEIRA,
    ITALIANA,
    JAPONESA,
    MEXICANA,
    CHINESA,
    INDIANA,
    ARABE,
    VEGETARIANA,
    VEGANA,
    DOCERIA,
    MINEIRA,
    PAULISTA,
    NORDESTINA,
    FAST_FOOD,
    ALEMA;

    public static Optional<TipoCulinariaEnum> toEnum(final String value) {
        return Stream.of(values())
                .filter(culinaria -> culinaria.name().equalsIgnoreCase(value))
                .findFirst();
    }
}
