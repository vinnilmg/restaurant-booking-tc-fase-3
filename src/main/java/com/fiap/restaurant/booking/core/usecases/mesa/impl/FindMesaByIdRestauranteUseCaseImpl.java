package com.fiap.restaurant.booking.core.usecases.mesa.impl;

import com.fiap.restaurant.booking.core.domains.Mesa;
import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.MesaGateway;
import com.fiap.restaurant.booking.core.usecases.mesa.FindMesaByIdRestauranteUseCase;

import static java.util.Objects.isNull;

public class FindMesaByIdRestauranteUseCaseImpl implements FindMesaByIdRestauranteUseCase {
    private final MesaGateway mesaGateway;

    public FindMesaByIdRestauranteUseCaseImpl(MesaGateway mesaGateway) {
        this.mesaGateway = mesaGateway;
    }

//    @Override
//    public Mesa execute(Long restauranteId, Integer numeroDaMesa) {
//        if (isNull(restauranteId) || restauranteId < 0) throw ValidationException.of("Restaurante Id", "cannot be null or negative");
//        return mesaGateway.findById(restauranteId, numeroDaMesa)
//                .orElseThrow(() -> NotFoundException.of("Mesa"));
//    }

    @Override
    public Mesa execute(final Long id) {
        if (isNull(id) || id < 0) throw ValidationException.of("Mesa Id", "cannot be null or negative");
        return mesaGateway.findById(id)
                .orElseThrow(() -> NotFoundException.of("Mesa"));
    }
}
