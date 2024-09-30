package com.fiap.restaurant.booking.core.usecases.mesa.impl;

import com.fiap.restaurant.booking.core.domains.Mesa;
import com.fiap.restaurant.booking.core.gateways.MesaGateway;
import com.fiap.restaurant.booking.core.usecases.mesa.FindMesasByIdRestauranteUseCase;

import java.util.List;

public class FindMesaByIdRestauranteUseCaseImpl implements FindMesasByIdRestauranteUseCase {

    private final MesaGateway mesaGateway;

    public FindMesaByIdRestauranteUseCaseImpl(MesaGateway mesaGateway) {
        this.mesaGateway = mesaGateway;
    }

    @Override
    public List<Mesa> execute(Long restauranteId) {
        return mesaGateway.findByRestauranteId(restauranteId);
    }
}
