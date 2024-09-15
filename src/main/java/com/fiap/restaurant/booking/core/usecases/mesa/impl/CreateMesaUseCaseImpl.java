package com.fiap.restaurant.booking.core.usecases.mesa.impl;

import com.fiap.restaurant.booking.core.domains.Mesa;
import com.fiap.restaurant.booking.core.gateways.MesaGateway;
import com.fiap.restaurant.booking.core.usecases.mesa.CreateMesaUseCase;

public class CreateMesaUseCaseImpl implements CreateMesaUseCase {

    private final MesaGateway mesaGateway;

    public CreateMesaUseCaseImpl(MesaGateway mesaGateway) {
        this.mesaGateway = mesaGateway;
    }

    @Override
    public Mesa execute(Mesa mesa) {
        return mesaGateway.create(mesa);
    }
}
