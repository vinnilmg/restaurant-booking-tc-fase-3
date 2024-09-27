package com.fiap.restaurant.booking.infrastructure.controllers.mappers;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.core.domains.FeedBackDomain;
import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.infrastructure.controllers.request.FeedBackRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.FeedBackResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface FeedBackMapper {

    default FeedBackDomain toFeedBackDomain(FeedBackRequest request, Restaurante restaurante) {
        return new FeedBackDomain(null, restaurante,
                request.nomeCliente(),
                request.avaliacao(),
                request.comentario()
                , null);
    }

    @Mapping(target = "restauranteId", expression = "java(feedBack.getRestaurante().getId())")
    FeedBackResponse toFeedbackResponse(FeedBack feedBack);

}
