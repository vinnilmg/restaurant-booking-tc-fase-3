package com.fiap.restaurant.booking.infrastructure.controllers.mappers;

import com.fiap.restaurant.booking.core.domains.MesaDomain;
import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;
import com.fiap.restaurant.booking.infrastructure.controllers.request.MesaRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.MesaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MesaMapper {

    default MesaDomain toMesaDomain(final MesaRequest mesaRequest) {
        return new MesaDomain(
                mesaRequest.numeroDaMesa(),
                mesaRequest.idDoRestaurante(),
                StatusMesaEnum.DISPONIVEL
        );
    }

    @Mapping(target = "id", source = "id")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "mesa", source = "numeroDaMesa")
    MesaResponse toMesaResponse(MesaDomain mesaDomain);
}
