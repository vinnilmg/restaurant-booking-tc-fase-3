package com.fiap.restaurant.booking.core.usecases.restaurante.impl;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByEnderecoCidadeUseCase;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class FindRestauranteByEnderecoCidadeUseCaseImpl implements FindRestauranteByEnderecoCidadeUseCase {
    private final RestauranteGateway restauranteGateway;

    public FindRestauranteByEnderecoCidadeUseCaseImpl(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;

    }

    @Override
    public List<Restaurante> execute(String cidade) {
        if (isEmpty(cidade)) throw ValidationException.of("Cidade", "cannot be null");
        return restauranteGateway.findByEnderecoCidade(cidade);
    }
}
