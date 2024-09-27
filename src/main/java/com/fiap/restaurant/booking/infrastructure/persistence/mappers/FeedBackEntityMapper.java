package com.fiap.restaurant.booking.infrastructure.persistence.mappers;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.core.domains.FeedBackDomain;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.FeedBackEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedBackEntityMapper {

    RestauranteEntityMapper restauranteEntityMapper = Mappers.getMapper(RestauranteEntityMapper.class);

    FeedBackEntity toEntity(FeedBack feedBack);

    default FeedBack toDomain(FeedBackEntity feedBackEntity) {
        return new FeedBackDomain(feedBackEntity.getId(),
                restauranteEntityMapper.toDomain(feedBackEntity.getRestaurante()),
                feedBackEntity.getNomeCliente(),
                feedBackEntity.getAvaliacao(),
                feedBackEntity.getComentario(),
                feedBackEntity.getDataHoraCriacao()
        );
    }

    default List<FeedBack> toDomains(List<FeedBackEntity> feedBackEntities) {
        return feedBackEntities
                .stream()
                .map(this::toDomain)
                .toList();
    }
}

