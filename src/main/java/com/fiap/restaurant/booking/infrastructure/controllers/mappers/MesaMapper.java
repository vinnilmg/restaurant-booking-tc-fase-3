package com.fiap.restaurant.booking.infrastructure.controllers.mappers;

import com.fiap.restaurant.booking.core.domains.Mesa;
import com.fiap.restaurant.booking.core.domains.MesaDomain;
import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;
import com.fiap.restaurant.booking.infrastructure.controllers.request.MesaRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.MesaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MesaMapper {

    default Mesa toMesaDomain(final MesaRequest mesaRequest) {
        return MesaDomain.createInstanceRequestValidation(
                mesaRequest.restauranteId(),
                mesaRequest.numeroDaMesa(),
                StatusMesaEnum.DISPONIVEL
        );
    }

    @Mapping(target = "id", source = "id")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "mesa", source = "numeroDaMesa")
//    @Mapping(target = "restauranteId", expression = "java(mesa.getRestaurante().getId())")
    MesaResponse toMesaResponse(Mesa mesaDomain);

    MesaRequest toMesaDomain(Long restauranteId, Integer numeroMesa);

    List<MesaResponse> toMesaResponse(List<MesaDomain> mesasDomain);

}
