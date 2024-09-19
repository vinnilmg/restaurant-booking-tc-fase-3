package com.fiap.restaurant.booking.core.usecases.restaurante.impl;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByTipoCulinariaUseCase;

import java.util.List;
import static org.apache.commons.lang3.StringUtils.isEmpty;


public class FindRestauranteByTipoCulinariaUseCaseImpl implements FindRestauranteByTipoCulinariaUseCase {
    private final RestauranteGateway restauranteGateway;

    public FindRestauranteByTipoCulinariaUseCaseImpl(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public List<Restaurante> execute(final String tipoCulinaria) {
        if (isEmpty(tipoCulinaria)) throw ValidationException.of("Tipo Culinária", "cannot be null");
        return restauranteGateway.findByTipoCulinaria(tipoCulinaria);
    }
}
