package com.fiap.restaurant.booking.core.usecases.mesa.impl;

import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;
import com.fiap.restaurant.booking.core.gateways.MesaGateway;
import com.fiap.restaurant.booking.core.usecases.mesa.BookMesaUseCase;
import com.fiap.restaurant.booking.core.usecases.mesa.FindMesaByIdUseCase;

public class BookMesaUseCaseImpl implements BookMesaUseCase {
    private final MesaGateway mesaGateway;
    private final FindMesaByIdUseCase findMesaByIdUseCase;

    public BookMesaUseCaseImpl(MesaGateway mesaGateway, FindMesaByIdUseCase findMesaByIdUseCase) {
        this.mesaGateway = mesaGateway;
        this.findMesaByIdUseCase = findMesaByIdUseCase;
    }

    @Override
    public void execute(final Long id) {
        final var mesa = findMesaByIdUseCase.execute(id);
        mesa.updateStatus(StatusMesaEnum.RESERVADA);
        mesaGateway.update(mesa);
    }
}
