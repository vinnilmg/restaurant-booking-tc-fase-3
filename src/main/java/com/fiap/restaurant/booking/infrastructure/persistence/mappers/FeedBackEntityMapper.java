package com.fiap.restaurant.booking.infrastructure.persistence.mappers;

import com.fiap.restaurant.booking.core.builders.FeedBackBuilder;
import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.FeedBackEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeedBackEntityMapper {

    FeedBackEntity toEntity(FeedBack feedBack);

    default FeedBack toDomain(FeedBackEntity feedBackEntity) {
        return FeedBackBuilder.build(feedBackEntity);

    }
}

