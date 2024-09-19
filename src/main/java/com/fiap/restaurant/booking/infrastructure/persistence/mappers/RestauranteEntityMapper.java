package com.fiap.restaurant.booking.infrastructure.persistence.mappers;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.domains.RestauranteDomain;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.RestauranteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestauranteEntityMapper {

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "cnpj", source = "cnpj")
    @Mapping(target = "tipoCulinaria", source = "tipoCulinaria")
    @Mapping(target = "inicioFuncionamento", source = "inicioFuncionamento")
    @Mapping(target = "fimFuncionamento", source = "fimFuncionamento")
    @Mapping(target = "capacidade", source = "capacidade")
    @Mapping(target = "mediaFeedback", source = "mediaFeedback")
    RestauranteEntity toEntity(Restaurante restaurante);

    default Restaurante toDomain(RestauranteEntity restaurante) {
        return new RestauranteDomain(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getCnpj(),
                restaurante.getTipoCulinaria(),
                restaurante.getInicioFuncionamento(),
                restaurante.getFimFuncionamento(),
                restaurante.getCapacidade(),
                restaurante.getMediaFeedback()
        );
    }

    default List<Restaurante> toDomains(List<RestauranteEntity> restaurantes) {
        return restaurantes.stream()
                .map(this::toDomain)
                .toList();
    }
}
