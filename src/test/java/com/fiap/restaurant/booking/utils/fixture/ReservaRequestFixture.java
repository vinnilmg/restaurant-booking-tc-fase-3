package com.fiap.restaurant.booking.utils.fixture;

import com.fiap.restaurant.booking.infrastructure.controllers.request.ReservaRequest;

import java.time.LocalDateTime;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_CPF;
import static com.fiap.restaurant.booking.utils.FormatUtils.formatLocalDateTimeToDefault;

public class ReservaRequestFixture {

    public static ReservaRequest FULL() {
        return new ReservaRequest(
                1,
                1,
                DEFAULT_CPF,
                formatLocalDateTimeToDefault(LocalDateTime.now().plusDays(5))
        );
    }
}
