package com.fiap.restaurant.booking.utils.fixture;

import com.fiap.restaurant.booking.core.domains.MesaDomain;
import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.MesaEntity;

public class MesaDomainFixture {

    public static MesaDomain DISPONIVEL() {
        return new MesaDomain(
                1L,
                RestauranteDomainFixture.FULL_WITH_ID(1L),
                1,
                StatusMesaEnum.DISPONIVEL
        );
    }

    public static MesaDomain FULL_WITH_IDS(final Long id, final Long resturanteId) {
        return new MesaDomain(
                id,
                RestauranteDomainFixture.FULL_WITH_ID(resturanteId),
                1,
                StatusMesaEnum.DISPONIVEL
        );
    }

    public static MesaDomain FULL_WITH_ID_AND_RESERVADA(final Long id, final Long resturanteId) {
        return new MesaDomain(
                id,
                RestauranteDomainFixture.FULL_WITH_ID(resturanteId),
                1,
                StatusMesaEnum.RESERVADA
        );
    }

    public static MesaDomain BY_ENTITY(final MesaEntity entity) {
        return new MesaDomain(
                entity.getId(),
                RestauranteDomainFixture.BY_ENTITY(entity.getRestaurante()),
                entity.getNumeroDaMesa(),
                entity.getStatus()
        );
    }
}
