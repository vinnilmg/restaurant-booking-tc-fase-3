package com.fiap.restaurant.booking.infrastructure.persistence.mappers;

import com.fiap.restaurant.booking.core.domains.FeedBackDomain;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.FeedBackEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeedBackEntityMapper {

    FeedBackEntity toEntity(FeedBackDomain feedBackDomain);

    FeedBackDomain toDomain(FeedBackEntity feedBackEntity);
}
