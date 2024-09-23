package com.fiap.restaurant.booking.core.builders;

import com.fiap.restaurant.booking.core.domains.FeedBackDomain;
import com.fiap.restaurant.booking.infrastructure.controllers.request.FeedBackRequest;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.FeedBackEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FeedBackBuilder {

    public static FeedBackDomain build(FeedBackRequest request) {
        return FeedBackDomain.createInstanceRequestValidation(request.restauranteId(),
                request.nomeCliente(),
                request.avaliacao(),
                request.comentario());
    }

    public static FeedBackDomain build(FeedBackEntity feedBackEntity) {
        return new FeedBackDomain(feedBackEntity.getId(),
                RestauranteBuilder.build(feedBackEntity.getRestaurante()),
                feedBackEntity.getNomeCliente(),
                feedBackEntity.getAvaliacao(),
                feedBackEntity.getComentario(),
                feedBackEntity.getDataHoraCriacao()

        );
    }
}
