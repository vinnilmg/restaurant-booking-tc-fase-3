package com.fiap.restaurant.booking.utils.fixture;

import com.fiap.restaurant.booking.infrastructure.controllers.request.ReservaRequest;

import java.time.LocalDateTime;

import static com.fiap.restaurant.booking.utils.DateTimeUtils.toDefaultFormat;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_CPF;

public class ReservaRequestFixture {

    public static ReservaRequest FULL() {
        return new ReservaRequest(
                1L,
                1L,
                DEFAULT_CPF,
                toDefaultFormat(LocalDateTime.now().plusDays(5))
        );
    }
}
