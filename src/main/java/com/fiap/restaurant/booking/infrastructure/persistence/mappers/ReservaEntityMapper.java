package com.fiap.restaurant.booking.infrastructure.persistence.mappers;

import com.fiap.restaurant.booking.core.domains.Reserva;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.ReservaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservaEntityMapper {

    ReservaEntity toEntity(Reserva reserva);

    Reserva toDomain(ReservaEntity reserva);
}
