package com.fiap.restaurant.booking.infrastructure.controllers.request;

public record EnderecoRequest(
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep
) {
}
