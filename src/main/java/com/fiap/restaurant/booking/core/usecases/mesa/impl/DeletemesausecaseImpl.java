package com.fiap.restaurant.booking.core.usecases.mesa.impl;

import com.fiap.restaurant.booking.core.domains.MesaDomain;
import com.fiap.restaurant.booking.core.gateways.MesaGateway;
import com.fiap.restaurant.booking.core.usecases.mesa.DeleteMesaUseCase;

public class DeletemesausecaseImpl implements DeleteMesaUseCase {

    private final MesaGateway mesaGateway;

    public DeletemesausecaseImpl(MesaGateway mesaGateway) {
        this.mesaGateway = mesaGateway;
    }

    @Override
    public MesaDomain execute(Long id) {

//        mesaGateway.findById(id).ifPresent(mesa -> mesaGateway.delete(id));
        return null;
    }
}
