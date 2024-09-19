package com.fiap.restaurant.booking.core.usecases.restaurante.impl;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByNomeUseCase;

import java.util.List;
import static org.apache.commons.lang3.StringUtils.isEmpty;


public class FindRestauranteByNomeUseCaseImpl implements FindRestauranteByNomeUseCase {
    private final RestauranteGateway restauranteGateway;

    public FindRestauranteByNomeUseCaseImpl(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public List<Restaurante> execute(final String nome) {
        if (isEmpty(nome)) throw ValidationException.of("Nome", "cannot be null");
        return restauranteGateway.findByNome(nome);
    }
}
