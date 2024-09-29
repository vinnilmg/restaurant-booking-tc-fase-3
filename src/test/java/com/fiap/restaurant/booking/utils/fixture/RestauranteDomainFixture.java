package com.fiap.restaurant.booking.utils.fixture;

import com.fiap.restaurant.booking.core.domains.RestauranteDomain;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.RestauranteEntity;
import com.fiap.restaurant.booking.infrastructure.persistence.mappers.EnderecoEntityMapper;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.*;
import static org.mapstruct.factory.Mappers.getMapper;

public class RestauranteDomainFixture {
    public static RestauranteDomain NOVO() {
        return new RestauranteDomain(
                DEFAULT_NOME,
                DEFAULT_CNPJ,
                DEFAULT_ENDERECO_DOMAIN,
                DEFAULT_TIPO_CULINARIA,
                DEFAULT_INICIO_FUNCIONAMENTO,
                DEFAULT_FIM_FUNCIONAMENTO,
                DEFAULT_CAPACIDADE,
                DEFAULT_MEDIA_FEEDBACK
        );
    }

    public static RestauranteDomain BY_ENTITY(final RestauranteEntity entity) {
        final var enderecoDomain = getMapper(EnderecoEntityMapper.class)
                .toDomain(entity.getEndereco());

        return new RestauranteDomain(
                entity.getId(),
                entity.getNome(),
                entity.getCnpj(),
                enderecoDomain,
                entity.getTipoCulinaria(),
                entity.getInicioFuncionamento(),
                entity.getFimFuncionamento(),
                entity.getCapacidade(),
                entity.getMediaFeedback()
        );
    }
}
