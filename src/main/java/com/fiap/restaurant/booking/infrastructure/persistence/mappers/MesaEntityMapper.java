package com.fiap.restaurant.booking.infrastructure.persistence.mappers;

import com.fiap.restaurant.booking.core.domains.Mesa;
import com.fiap.restaurant.booking.core.domains.MesaDomain;
import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.FeedBackEntity;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.MesaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MesaEntityMapper {
    RestauranteEntityMapper restauranteEntityMapper = Mappers.getMapper(RestauranteEntityMapper.class);

    MesaEntity toEntity(Mesa mesa);

    default Mesa toDomain(MesaEntity mesaEntity) {
        return new MesaDomain(
                mesaEntity.getId(),
                restauranteEntityMapper.toDomain(mesaEntity.getRestaurante()),
                mesaEntity.getNumeroDaMesa(),
                StatusMesaEnum.valueOf(mesaEntity.getStatus())
        );
    }

    default List<MesaDomain> toDomains(List<MesaEntity> mesaEntities) {
        return mesaEntities
                .stream()
                .map(this::toDomain)
                .toList();
    }
}
