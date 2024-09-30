package com.fiap.restaurant.booking.utils.fixture;

import com.fiap.restaurant.booking.core.domains.RestauranteDomain;
import com.fiap.restaurant.booking.core.domains.enums.TipoCulinariaEnum;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.RestauranteEntity;

import java.time.LocalTime;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_CAPACIDADE;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_CNPJ;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_ENDERECO_DOMAIN;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_FIM_FUNCIONAMENTO;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_INICIO_FUNCIONAMENTO;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_MEDIA_FEEDBACK;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_NOME;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_TIPO_CULINARIA;

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
        return new RestauranteDomain(
                entity.getId(),
                entity.getNome(),
                entity.getCnpj(),
                EnderecoDomainFixture.BY_ENTITY(entity.getEndereco()),
                entity.getTipoCulinaria(),
                entity.getInicioFuncionamento(),
                entity.getFimFuncionamento(),
                entity.getCapacidade(),
                entity.getMediaFeedback()
        );
    }

    public static RestauranteDomain FULL_WITH_ID(final Long id) {
        return new RestauranteDomain(
                id,
                "testeRestaurante",
                "64589238000187",
                EnderecoDomainFixture.NOVO(),
                TipoCulinariaEnum.MINEIRA.name(),
                LocalTime.now().minusHours(4),
                LocalTime.now(),
                4,
                4.5
        );
    }
}
