package com.fiap.restaurant.booking.core.usecases.restaurante.impl;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByEnderecoBairroUseCase;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class FindRestauranteByEnderecoBairroUseCaseImpl implements FindRestauranteByEnderecoBairroUseCase {
    private final RestauranteGateway restauranteGateway;


    public FindRestauranteByEnderecoBairroUseCaseImpl(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public List<Restaurante> execute(String bairro) {
        if (isEmpty(bairro)) throw ValidationException.of("Bairro", "cannot be null");
        return restauranteGateway.findByEnderecoBairro(bairro);
    }
}
