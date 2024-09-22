package com.fiap.restaurant.booking.utils;

import com.fiap.restaurant.booking.core.domains.RestauranteDomain;
import com.fiap.restaurant.booking.core.domains.enums.TipoCulinariaEnum;

import java.time.LocalDateTime;

public class InformationsRestauranteConstants {

    public static RestauranteDomain buildRestauranteTest(Long idRestaurante) {
        return new RestauranteDomain(idRestaurante,
                "testeRestaurante",
                "64589238000187",
                TipoCulinariaEnum.MINEIRA.name(),
                LocalDateTime.now().minusDays(1L),
                LocalDateTime.now(),
                4,
                4.5
        );
    }
}


