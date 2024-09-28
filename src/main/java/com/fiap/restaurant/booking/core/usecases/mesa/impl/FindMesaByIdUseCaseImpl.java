package com.fiap.restaurant.booking.core.usecases.mesa.impl;

import com.fiap.restaurant.booking.core.domains.MesaDomain;
import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.MesaGateway;
import com.fiap.restaurant.booking.core.usecases.mesa.FindMesaByIdUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByIdUseCase;

import java.util.Objects;

public class FindMesaByIdUseCaseImpl implements FindMesaByIdUseCase {

    MesaGateway mesaGateway;
    private final FindRestauranteByIdUseCase findByRestauranteIdUseCase;

    public FindMesaByIdUseCaseImpl(MesaGateway mesaGateway, FindRestauranteByIdUseCase findByRestauranteIdUseCase) {
        this.mesaGateway = mesaGateway;
        this.findByRestauranteIdUseCase = findByRestauranteIdUseCase;
    }

    @Override
    public MesaDomain execute(Long id, Integer numeroMesa) {
        if(Objects.isNull(id)) {
            throw ValidationException.of("Id", "cannot be null");
        }
        if(Objects.isNull(numeroMesa)) {
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
