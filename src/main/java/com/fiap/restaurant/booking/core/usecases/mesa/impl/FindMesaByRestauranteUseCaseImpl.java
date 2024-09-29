package com.fiap.restaurant.booking.core.usecases.mesa.impl;

import com.fiap.restaurant.booking.core.domains.Mesa;
import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.MesaGateway;
import com.fiap.restaurant.booking.core.usecases.mesa.FindMesaByRestauranteUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByIdUseCase;

import static java.util.Objects.isNull;

public class FindMesaByRestauranteUseCaseImpl implements FindMesaByRestauranteUseCase {
    private final MesaGateway mesaGateway;
    private final FindRestauranteByIdUseCase findByRestauranteIdUseCase;

    public FindMesaByRestauranteUseCaseImpl(MesaGateway mesaGateway, FindRestauranteByIdUseCase findByRestauranteIdUseCase) {
        this.mesaGateway = mesaGateway;
        this.findByRestauranteIdUseCase = findByRestauranteIdUseCase;
    }

    @Override
    public Mesa execute(Long id, Integer numeroMesa) {
        if (isNull(id)) {
            throw ValidationException.of("Id", "cannot be null");
        }
        if (isNull(numeroMesa)) {
            throw ValidationException.of("NumeroMesa", "cannot be null");
        }
        Restaurante restaurante = findByRestauranteIdUseCase.execute(id);
        if (restaurante == null) {
            throw new NotFoundException(String.format("Restaurante with id %s not found", id));
        }
        return mesaGateway.findById(id, numeroMesa).orElseThrow(() -> new NotFoundException(String.format("Mesa with id %s and number %s not found", id, numeroMesa)));
//        return mesaGateway.findById(id).orElseThrow(() -> new NotFoundException(String.format("Mesa with id %s", id)));
    }
}
