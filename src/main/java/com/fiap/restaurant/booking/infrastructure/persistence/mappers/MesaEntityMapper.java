package com.fiap.restaurant.booking.infrastructure.persistence.mappers;

import com.fiap.restaurant.booking.core.domains.Mesa;
import com.fiap.restaurant.booking.core.domains.MesaDomain;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.MesaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MesaEntityMapper {
    RestauranteEntityMapper restauranteEntityMapper = Mappers.getMapper(RestauranteEntityMapper.class);

    MesaEntity toEntity(Mesa mesa);

    default Mesa toDomain(MesaEntity mesaEntity) {
        return new MesaDomain(
                mesaEntity.getId(),
                restauranteEntityMapper.toDomain(mesaEntity.getRestaurante()),
                mesaEntity.getNumeroDaMesa(),
                mesaEntity.getStatus()
        );
    }
}
