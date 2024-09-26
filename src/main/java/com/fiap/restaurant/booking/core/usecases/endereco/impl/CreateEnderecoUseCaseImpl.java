package com.fiap.restaurant.booking.core.usecases.endereco.impl;

import com.fiap.restaurant.booking.core.domains.Endereco;
import com.fiap.restaurant.booking.core.gateways.EnderecoGateway;
import com.fiap.restaurant.booking.core.usecases.endereco.CreateEnderecoUseCase;

public class CreateEnderecoUseCaseImpl implements CreateEnderecoUseCase {
    private final EnderecoGateway enderecoGateway;

    public CreateEnderecoUseCaseImpl(EnderecoGateway enderecoGateway) {
        this.enderecoGateway = enderecoGateway;
    }

    @Override
    public Endereco execute(final Endereco endereco) {
        return enderecoGateway.create(endereco);
    }
}