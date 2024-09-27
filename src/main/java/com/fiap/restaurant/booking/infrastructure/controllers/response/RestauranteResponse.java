package com.fiap.restaurant.booking.infrastructure.controllers.response;

import com.fiap.restaurant.booking.core.domains.Endereco;

public record RestauranteResponse(
        Long id,
        String nome,
        String cnpj,
        Endereco endereco,
        String tipoCulinaria,
        String inicioFuncionamento,
        String fimFuncionamento,
        Integer capacidade,
        Double mediaFeedback
) {
}
