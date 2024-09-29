package com.fiap.restaurant.booking.utils.fixture;

import com.fiap.restaurant.booking.core.domains.RestauranteDomain;
import com.fiap.restaurant.booking.core.domains.enums.TipoCulinariaEnum;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.RestauranteEntity;

import java.time.LocalTime;

public class RestauranteDomainFixture {

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
                EnderecoDomainFixture.OTHER(),
                TipoCulinariaEnum.MINEIRA.name(),
                LocalTime.now().minusHours(4),
                LocalTime.now(),
                4,
                4.5
        );
    }

    public static RestauranteDomain buildRestauranteTest() {
        return new RestauranteDomain(
                1L,
                "testeRestaurante",
                "64589238000187",
                EnderecoDomainFixture.OTHER(),
                TipoCulinariaEnum.MINEIRA.name(),
                LocalTime.now().minusHours(4),
                LocalTime.now(),
                4,
                4.5
        );
    }
}
