package com.fiap.restaurant.booking.core.usecases.mesa.impl;

import com.fiap.restaurant.booking.core.domains.Mesa;
import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.MesaGateway;
import com.fiap.restaurant.booking.core.usecases.mesa.CreateMesaUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByIdUseCase;

import static java.util.Objects.isNull;

public class CreateMesaUseCaseImpl implements CreateMesaUseCase {
    private final MesaGateway mesaGateway;
    private final FindRestauranteByIdUseCase findByRestauranteIdUseCase;

    public CreateMesaUseCaseImpl(MesaGateway mesaGateway, FindRestauranteByIdUseCase findByRestauranteIdUseCase) {
        this.mesaGateway = mesaGateway;
        this.findByRestauranteIdUseCase = findByRestauranteIdUseCase;
    }

    @Override
    public Mesa execute(final Mesa mesaDomain, final Long restauranteId) {
        Restaurante restaurante = findByRestauranteIdUseCase.execute(restauranteId);
        if (restaurante == null) {
            throw new ValidationException("RestauranteId", "O restaurante com o ID fornecido não existe");
        }
        if (mesaDomain.getStatus() == null) {
            throw new ValidationException("MesaStatus", "O status da mesa não pode ser nulo"); // Retornar um validation exception
        }
        if (isNull(mesaDomain.getId())) {
            mesaDomain.setRestaurante(restaurante);
        }
        return mesaGateway.create(mesaDomain);
    }
}
