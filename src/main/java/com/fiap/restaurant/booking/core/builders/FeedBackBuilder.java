package com.fiap.restaurant.booking.core.builders;

import com.fiap.restaurant.booking.core.domains.FeedBackDomain;
import com.fiap.restaurant.booking.infrastructure.controllers.request.FeedBackRequest;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.FeedBackEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FeedBackBuilder {

    public static FeedBackDomain build(FeedBackRequest request) {
        return FeedBackDomain.builder()
                .nomeCliente(request.nomeCliente())
                .comentario(request.comentario())
                .avaliacao(request.avaliacao())
                .build();

    }

    public static FeedBackDomain build(FeedBackEntity feedBackEntity) {
        return FeedBackDomain.builder()
                .nomeCliente(feedBackEntity.getNomeCliente())
                .comentario(feedBackEntity.getComentario())
                .avaliacao(feedBackEntity.getAvaliacao())
                .id(feedBackEntity.getId())
                .restauranteId(RestauranteBuilder.build(feedBackEntity.getRestaurante()))
                .dataHoraCriacao(feedBackEntity.getDataHoraCriacao())
                .build();

    }

}
