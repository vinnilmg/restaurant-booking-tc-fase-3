package com.fiap.restaurant.booking.core.usecases.mesa.impl;

import com.fiap.restaurant.booking.core.domains.Mesa;
import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.MesaGateway;
import com.fiap.restaurant.booking.core.usecases.mesa.FindMesaByIdUseCase;

import java.util.Objects;

public class FindMesaByIdUseCaseImpl implements FindMesaByIdUseCase {

    MesaGateway mesaGateway;

    public FindMesaByIdUseCaseImpl(MesaGateway mesaGateway) {
        this.mesaGateway = mesaGateway;
    }

    @Override
    public Mesa execute(Long id) {
        if(Objects.isNull(id)) {
            throw ValidationException.of("Id", "cannot be null");
        }
        return mesaGateway.findById(id).orElseThrow(() -> new NotFoundException(String.format("Mesa with id %s", id)));
    }
}
