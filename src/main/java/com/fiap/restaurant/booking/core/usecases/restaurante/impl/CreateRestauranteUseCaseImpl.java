package com.fiap.restaurant.booking.core.usecases.restaurante.impl;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.CreateRestauranteUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByCnpjUseCase;

import static java.util.Objects.isNull;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

public class CreateRestauranteUseCaseImpl implements CreateRestauranteUseCase {
    private final RestauranteGateway restauranteGateway;
    private final FindRestauranteByCnpjUseCase findRestauranteByCnpjUseCase;

    public CreateRestauranteUseCaseImpl(RestauranteGateway restauranteGateway,
                                        FindRestauranteByCnpjUseCase findRestauranteByCnpjUseCase) {
        this.restauranteGateway = restauranteGateway;
        this.findRestauranteByCnpjUseCase = findRestauranteByCnpjUseCase;
    }

    @Override
    public Restaurante execute(final Restaurante restaurante) {
        final var cnpj = findRestauranteByCnpjUseCase.execute(restaurante.getCnpj());

        if (cnpj.isPresent()) {
            throw ValidationException.of("CNPJ","already exists");
        }

        return restauranteGateway.create(restaurante);
    }
}
