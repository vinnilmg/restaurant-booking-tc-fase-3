package com.fiap.restaurant.booking.infrastructure.controllers.response;


public record MesaResponse(
        Long id,
        Integer mesa,
        String status,
        Long restauranteId
) {
}
