package com.fiap.restaurant.booking.infrastructure.controllers.response;

import java.time.LocalDateTime;

public record FeedBackResponse(
        Long id,
        Long restauranteId,
        String nomeCliente,
        Integer avaliacao,
        String comentario,
        LocalDateTime dataHoraCriacao
) {
}
