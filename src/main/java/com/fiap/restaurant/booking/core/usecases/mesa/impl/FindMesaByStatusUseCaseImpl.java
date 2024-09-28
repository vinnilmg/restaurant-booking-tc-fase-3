package com.fiap.restaurant.booking.core.usecases.mesa.impl;

import com.fiap.restaurant.booking.core.domains.Mesa;
import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.MesaGateway;
import com.fiap.restaurant.booking.core.usecases.mesa.FindMesaByStatusUseCase;

public class FindMesaByStatusUseCaseImpl implements FindMesaByStatusUseCase {

    MesaGateway mesaGateway;

    public FindMesaByStatusUseCaseImpl(MesaGateway mesaGateway) {
        this.mesaGateway = mesaGateway;
    }

    @Override
    public Mesa execute(StatusMesaEnum status) {
        if (status == null || !status.toString().equals("DISPONIVEL") && !status.toString().equals("RESERVADA")) {
            throw ValidationException.of("Status", "Status must be DISPONIVEL or RESERVADA");
        }
        return (Mesa) mesaGateway.findByStatus(status);
    }
}
