package com.fiap.restaurant.booking.infrastructure.persistence.mappers;

import com.fiap.restaurant.booking.core.domains.MesaDomain;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.MesaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MesaEntityMapper {

    MesaEntity toEntity(MesaDomain mesa);
    RestauranteEntityMapper restauranteEntityMapper = Mappers.getMapper(RestauranteEntityMapper.class);

    default MesaDomain toDomain(MesaEntity mesaEntity) {
        return new MesaDomain(
                mesaEntity.getId(),
                restauranteEntityMapper.toDomain(mesaEntity.getRestaurante()),
                mesaEntity.getNumeroDaMesa(),
                mesaEntity.getStatus()
        );
    }
}
