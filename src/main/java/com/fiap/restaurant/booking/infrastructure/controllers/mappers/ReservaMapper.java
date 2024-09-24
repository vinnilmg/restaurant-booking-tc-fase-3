package com.fiap.restaurant.booking.infrastructure.controllers.mappers;

import com.fiap.restaurant.booking.core.domains.Reserva;
import com.fiap.restaurant.booking.core.domains.ReservaDomain;
import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;
import com.fiap.restaurant.booking.infrastructure.controllers.request.ReservaRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.ReservaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ReservaMapper {

    default ReservaDomain toReserva(ReservaRequest request) {
        return new ReservaDomain(
                request.cpfCliente(),
                StatusReservaEnum.SOLICITADA.name(),
                request.dataHoraReserva()
        );
    }

    List<ReservaResponse> toReservasResponse(List<Reserva> reservas);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "cpfCliente", source = "cpf")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "dataHoraReserva", source = "dataHoraReservaFormatted")
    @Mapping(target = "dataHoraCriacao", source = "dataHoraCriacaoFormatted")
    ReservaResponse toReservaResponse(Reserva reserva);
}
