package com.fiap.restaurant.booking.infrastructure.controllers.request;

public record FeedBackRequest (
        Long restauranteId,
        String nomeCliente,
        Integer avaliacao,
        String comentario){
}
