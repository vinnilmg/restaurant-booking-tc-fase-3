package com.fiap.restaurant.booking.core.usecases.restaurante.impl;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.GetAllRestaurantesUseCase;

import java.util.List;

public class GetAllRestaurantesUseCaseImpl implements GetAllRestaurantesUseCase {
    private final RestauranteGateway restauranteGateway;

    public GetAllRestaurantesUseCaseImpl(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public List<Restaurante> execute() {
        return restauranteGateway.getAll();
    }
}
