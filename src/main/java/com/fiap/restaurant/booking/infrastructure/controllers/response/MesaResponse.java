package com.fiap.restaurant.booking.infrastructure.controllers.response;

import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;

public record MesaResponse(
        Long id,
        Integer mesa,
        String status,
        Long restauranteId
) {
}
