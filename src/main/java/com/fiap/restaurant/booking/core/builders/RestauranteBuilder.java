package com.fiap.restaurant.booking.core.builders;

import com.fiap.restaurant.booking.core.domains.RestauranteDomain;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.RestauranteEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RestauranteBuilder {


    public static RestauranteDomain build(RestauranteEntity entity) {
        return new RestauranteDomain(entity.getId(),
                entity.getNome(),
                entity.getCnpj(),
                entity.getTipoCulinaria(),
                entity.getInicioFuncionamento(),
                entity.getFimFuncionamento(),
                entity.getCapacidade(),
                entity.getMediaFeedback()
                );
    }
}
