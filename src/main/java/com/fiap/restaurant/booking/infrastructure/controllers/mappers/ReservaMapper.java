package com.fiap.restaurant.booking.infrastructure.controllers.mappers;

import com.fiap.restaurant.booking.core.domains.Reserva;
import com.fiap.restaurant.booking.infrastructure.controllers.request.ReservaRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.ReservaResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    Reserva toReserva(ReservaRequest request);

    ReservaResponse toReservaResponse(Reserva reserva);
}
