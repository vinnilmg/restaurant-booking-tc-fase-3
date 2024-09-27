package com.fiap.restaurant.booking.infrastructure.controllers.request;


public record MesaRequest(
        Integer numeroDaMesa,
        Long restauranteId
) {
}