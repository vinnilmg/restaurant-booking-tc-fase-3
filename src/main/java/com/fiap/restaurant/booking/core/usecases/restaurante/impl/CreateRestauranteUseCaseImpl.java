package com.fiap.restaurant.booking.core.usecases.restaurante.impl;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.CreateRestauranteUseCase;

public class CreateRestauranteUseCaseImpl implements CreateRestauranteUseCase {
    private final RestauranteGateway restauranteGateway;

    public CreateRestauranteUseCaseImpl(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public Restaurante execute(final Restaurante restaurante) {
        //TODO validacoes
        return restauranteGateway.create(restaurante);
    }
}
