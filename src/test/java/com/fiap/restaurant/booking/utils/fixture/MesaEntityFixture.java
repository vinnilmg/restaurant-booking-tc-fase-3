package com.fiap.restaurant.booking.utils.fixture;

import com.fiap.restaurant.booking.core.domains.Mesa;
import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.MesaEntity;

public class MesaEntityFixture {

    public static MesaEntity FULL() {
        final var entity = new MesaEntity();
        entity.setId(1L);
        entity.setNumeroDaMesa(1);
        entity.setStatus(StatusMesaEnum.DISPONIVEL);
        entity.setRestaurante(RestauranteEntityFixture.FULL());
        return entity;
    }

    public static MesaEntity BY_DOMAIN(final Mesa mesa) {
        final var entity = new MesaEntity();
        entity.setId(mesa.getId());
        entity.setNumeroDaMesa(mesa.getNumeroDaMesa());
        entity.setStatus(mesa.getStatus());
        entity.setRestaurante(RestauranteEntityFixture.BY_DOMAIN(mesa.getRestaurante()));
        return entity;
    }
}
