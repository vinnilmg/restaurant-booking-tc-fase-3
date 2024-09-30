package com.fiap.restaurant.booking.infrastructure.controllers.request;

import com.fiap.restaurant.booking.core.domains.Endereco;

public record RestauranteRequest(
        String nome,
        String cnpj,
        EnderecoRequest endereco,
        String tipoCulinaria,
        String inicioFuncionamento,
        String fimFuncionamento,
        Integer capacidade,
        Double mediaFeedback
) {
}
