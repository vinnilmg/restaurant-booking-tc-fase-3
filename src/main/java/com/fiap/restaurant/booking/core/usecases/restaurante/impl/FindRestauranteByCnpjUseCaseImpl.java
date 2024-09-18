package com.fiap.restaurant.booking.core.usecases.restaurante.impl;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByCnpjUseCase;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class FindRestauranteByCnpjUseCaseImpl implements FindRestauranteByCnpjUseCase {
    private final RestauranteGateway restauranteGateway;


    public FindRestauranteByCnpjUseCaseImpl(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public Optional<Restaurante> execute(final String cnpj) {
        if (isEmpty(cnpj) || cnpj.length() < 14) throw ValidationException.of("Restaurante CNPJ","invalid");
        return restauranteGateway.findByCnpj(cnpj);

    }
}
