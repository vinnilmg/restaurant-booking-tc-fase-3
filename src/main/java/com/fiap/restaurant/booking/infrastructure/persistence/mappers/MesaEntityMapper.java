package com.fiap.restaurant.booking.infrastructure.persistence.mappers;

import com.fiap.restaurant.booking.core.domains.MesaDomain;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.MesaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MesaEntityMapper {

    MesaEntity toEntity(MesaDomain mesaDomain);
    MesaDomain toDomain(MesaEntity mesaEntity);
}
