package com.fiap.restaurant.booking.core.usecases.mesa.impl;

import com.fiap.restaurant.booking.core.domains.MesaDomain;
import com.fiap.restaurant.booking.core.gateways.MesaGateway;
import com.fiap.restaurant.booking.core.usecases.mesa.CreateMesaUseCase;

public class CreateMesaUseCaseImpl implements CreateMesaUseCase {

    private final MesaGateway mesaGateway;

    public CreateMesaUseCaseImpl(MesaGateway mesaGateway) {
        this.mesaGateway = mesaGateway;
    }

    @Override
    public MesaDomain execute(final MesaDomain mesaDomain) {
        if (mesaDomain.getStatus() == null) {
            throw new IllegalArgumentException("O status da mesa não pode ser nulo"); // Retornar um validation exception
        }
        return mesaGateway.create(mesaDomain);
    }
}
