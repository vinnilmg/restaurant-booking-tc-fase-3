package com.fiap.restaurant.booking.infrastructure.controllers.response;

public record RestauranteResponse(
        Long id,
        String nome,
        String cnpj,
        String tipoCulinaria,
        String inicioFuncionamento,
        String fimFuncionamento,
        Integer capacidade,
        Double mediaFeedback
) {
}
