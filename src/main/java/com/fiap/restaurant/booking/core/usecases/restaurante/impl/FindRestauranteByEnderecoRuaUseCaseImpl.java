package com.fiap.restaurant.booking.core.usecases.restaurante.impl;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByEnderecoRuaUseCase;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class FindRestauranteByEnderecoRuaUseCaseImpl implements FindRestauranteByEnderecoRuaUseCase {
    private final RestauranteGateway restauranteGateway;


    public FindRestauranteByEnderecoRuaUseCaseImpl(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public List<Restaurante> execute(String rua) {
        if (isEmpty(rua)) throw ValidationException.of("Rua", "cannot be null");
        return restauranteGateway.findByEnderecoRua(rua);
    }
}
