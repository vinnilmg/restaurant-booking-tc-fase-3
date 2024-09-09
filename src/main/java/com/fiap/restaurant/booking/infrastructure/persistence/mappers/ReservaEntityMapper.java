package com.fiap.restaurant.booking.infrastructure.persistence.mappers;

import com.fiap.restaurant.booking.core.domains.Reserva;
import com.fiap.restaurant.booking.core.domains.ReservaDomain;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.ReservaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ReservaEntityMapper {

    @Mapping(target = "cpfCliente", source = "cpf")
    @Mapping(target = "dataHoraReserva", source = "dataHoraReserva")
    @Mapping(target = "status", source = "status")
    ReservaEntity toEntity(Reserva reserva);

    default Reserva toDomain(ReservaEntity reserva) {
        return new ReservaDomain(
                reserva.getId(),
                reserva.getCpfCliente(),
                reserva.getStatus(),
                reserva.getDataHoraReserva(),
                reserva.getDataHoraCriacao()
        );
    }

    default List<Reserva> toDomains(List<ReservaEntity> reservas) {
        return reservas
                .stream()
                .map(this::toDomain)
                .toList();
    }
}
