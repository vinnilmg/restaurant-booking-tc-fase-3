package com.fiap.restaurant.booking.infrastructure.controllers.response;

public record EnderecoResponse(
        Long id,
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep
) {
}
