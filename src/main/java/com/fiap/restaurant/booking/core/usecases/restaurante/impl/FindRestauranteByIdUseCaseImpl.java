package com.fiap.restaurant.booking.core.usecases.restaurante.impl;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByIdUseCase;

import static java.util.Objects.isNull;

public class FindRestauranteByIdUseCaseImpl implements FindRestauranteByIdUseCase {
    private final RestauranteGateway restauranteGateway;


    public FindRestauranteByIdUseCaseImpl(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public Restaurante execute(final Long id) {
        if (isNull(id) || id < 0) throw ValidationException.of("Restaurante Id", "cannot be null or negative");
        return restauranteGateway.findById(id)
                .orElseThrow(() -> NotFoundException.of("Restaurante"));
    }
}
