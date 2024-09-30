package com.fiap.restaurant.booking.core.usecases.mesa.impl;

import com.fiap.restaurant.booking.core.gateways.MesaGateway;
import com.fiap.restaurant.booking.core.usecases.mesa.DeleteMesaUseCase;
import com.fiap.restaurant.booking.core.usecases.mesa.FindIdRestauranteAndNumeroMesa;
import com.fiap.restaurant.booking.core.usecases.mesa.FindMesaByIdRestauranteUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByIdUseCase;
import com.fiap.restaurant.booking.infrastructure.controllers.response.MessageResponse;

public class DeleteMesaUseCaseImpl implements DeleteMesaUseCase {

    private final MesaGateway mesaGateway;
    private final FindIdRestauranteAndNumeroMesa findMesaByIdRestauranteUseCase;
    private final FindRestauranteByIdUseCase findRestauranteByIdUseCase;

    public DeleteMesaUseCaseImpl(MesaGateway mesaGateway, FindIdRestauranteAndNumeroMesa findMesaByIdRestauranteUseCase, FindRestauranteByIdUseCase findRestauranteByIdUseCase) {
        this.mesaGateway = mesaGateway;
        this.findMesaByIdRestauranteUseCase = findMesaByIdRestauranteUseCase;
        this.findRestauranteByIdUseCase = findRestauranteByIdUseCase;
    }

    @Override
    public MessageResponse execute(Long restauranteId, Integer numeroMesa) {
        findRestauranteByIdUseCase.execute(restauranteId);
        findMesaByIdRestauranteUseCase.execute(restauranteId, numeroMesa);
        mesaGateway.deleteByRestaurantIdAndNumeroMesa(restauranteId, numeroMesa);
        return MessageResponse.builder()
                .message(String.format("Table %d of restaurant %d was delected with succes.", restauranteId, numeroMesa))
                .build();
    }
}
