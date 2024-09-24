package com.fiap.restaurant.booking.infrastructure.controllers.mappers;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.domains.RestauranteDomain;
import com.fiap.restaurant.booking.infrastructure.controllers.request.RestauranteRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.RestauranteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestauranteMapper {

    default RestauranteDomain toRestaurante(RestauranteRequest request) {
        return new RestauranteDomain(
                request.nome(),
                request.cnpj(),
                request.endereco(),
                request.tipoCulinaria(),
                request.inicioFuncionamento(),
                request.fimFuncionamento(),
                request.capacidade(),
                request.mediaFeedback()
        );
    }

    List<RestauranteResponse> toRestauranteResponse(List<Restaurante> restaurantes);

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "cnpj", source = "cnpj")
    @Mapping(target = "endereco", source = "endereco")
    @Mapping(target = "tipoCulinaria", source = "tipoCulinaria")
    @Mapping(target = "inicioFuncionamento", source = "inicioFuncionamento")
    @Mapping(target = "fimFuncionamento", source = "fimFuncionamento")
    @Mapping(target = "capacidade", source = "capacidade")
    @Mapping(target = "mediaFeedback", source = "mediaFeedback")
    RestauranteResponse toRestauranteResponse(Restaurante restaurante);
}
