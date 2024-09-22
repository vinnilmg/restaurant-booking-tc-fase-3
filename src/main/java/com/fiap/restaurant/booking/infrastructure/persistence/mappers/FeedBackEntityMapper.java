package com.fiap.restaurant.booking.infrastructure.persistence.mappers;

import com.fiap.restaurant.booking.core.builders.RestauranteBuilder;
import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.core.domains.FeedBackDomain;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.FeedBackEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeedBackEntityMapper {

    FeedBackEntity toEntity(FeedBack feedBack);

    default FeedBack toDomain(FeedBackEntity feedBackEntity) {
      return  FeedBackDomain.builder()
                .id(feedBackEntity.getId())
                .nomeCliente(feedBackEntity.getNomeCliente())
                .dataHoraCriacao(feedBackEntity.getDataHoraCriacao())
                .avaliacao(feedBackEntity.getAvaliacao())
                .comentario(feedBackEntity.getComentario())
                .restauranteId(RestauranteBuilder.build(feedBackEntity.getRestaurante()))
                .build();
    }
}

