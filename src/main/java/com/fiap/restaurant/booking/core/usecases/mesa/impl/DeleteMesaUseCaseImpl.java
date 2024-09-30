package com.fiap.restaurant.booking.core.usecases.mesa.impl;

import com.fiap.restaurant.booking.core.gateways.MesaGateway;
import com.fiap.restaurant.booking.core.usecases.mesa.DeleteMesaUseCase;
import com.fiap.restaurant.booking.core.usecases.mesa.FindMesaByIdRestauranteUseCase;
import com.fiap.restaurant.booking.infrastructure.controllers.response.MessageResponse;

public class DeleteMesaUseCaseImpl implements DeleteMesaUseCase {

    private final MesaGateway mesaGateway;
    private final FindMesaByIdRestauranteUseCase findMesaByIdRestauranteUseCase;

    public DeleteMesaUseCaseImpl(MesaGateway mesaGateway, FindMesaByIdRestauranteUseCase findMesaByIdRestauranteUseCase) {
        this.mesaGateway = mesaGateway;
        this.findMesaByIdRestauranteUseCase = findMesaByIdRestauranteUseCase;
    }

    @Override
    public MessageResponse execute(Long id, Integer numeroMesa) {

        findMesaByIdRestauranteUseCase.execute(id);
        mesaGateway.delete(id, numeroMesa);
        return MessageResponse.builder()
                .message(String.format("Table %d of restaurant %d was delected with succes.", id, numeroMesa))
                .build();
    }
}
