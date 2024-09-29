package com.fiap.restaurant.booking.utils.fixture;

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
}
