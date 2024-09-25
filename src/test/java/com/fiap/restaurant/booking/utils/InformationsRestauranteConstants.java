package com.fiap.restaurant.booking.utils;

import com.fiap.restaurant.booking.core.domains.RestauranteDomain;
import com.fiap.restaurant.booking.core.domains.enums.TipoCulinariaEnum;

import java.time.LocalTime;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_ENDERECO_DOMAIN;

public class InformationsRestauranteConstants {

    public static RestauranteDomain buildRestauranteTest(Long idRestaurante) {
        return new RestauranteDomain(
                1L,
                "testeRestaurante",
                "64589238000187",
                DEFAULT_ENDERECO_DOMAIN,
                TipoCulinariaEnum.MINEIRA.name(),
                LocalTime.now().minusHours(4),
                LocalTime.now(),
                4,
                4.5
        );
    }
}


