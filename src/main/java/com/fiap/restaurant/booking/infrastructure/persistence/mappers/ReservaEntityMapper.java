package com.fiap.restaurant.booking.infrastructure.persistence.mappers;

import com.fiap.restaurant.booking.core.domains.Mesa;
import com.fiap.restaurant.booking.core.domains.Reserva;
import com.fiap.restaurant.booking.core.domains.ReservaDomain;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.MesaEntity;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.ReservaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ReservaEntityMapper {

    @Mapping(target = "cpfCliente", source = "cpf")
    @Mapping(target = "dataHoraReserva", source = "dataHoraReserva")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "mesa", source = "mesa", qualifiedByName = "toMesaEntity")
    ReservaEntity toEntity(Reserva reserva);

    default Reserva toDomain(final ReservaEntity reserva) {
        return new ReservaDomain(
                reserva.getId(),
                reserva.getCpfCliente(),
                reserva.getStatus(),
                reserva.getDataHoraReserva(),
                reserva.getDataHoraCriacao(),
                getMapper(MesaEntityMapper.class).toDomain(reserva.getMesa())
        );
    }

    default List<Reserva> toDomains(List<ReservaEntity> reservas) {
        return reservas
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Named("toMesaEntity")
    default MesaEntity toMesaEntity(final Mesa mesa) {
        return getMapper(MesaEntityMapper.class)
                .toEntity(mesa);
    }
}
