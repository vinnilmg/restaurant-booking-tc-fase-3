package com.fiap.restaurant.booking.core.usecases.endereco.impl;

import com.fiap.restaurant.booking.core.domains.Endereco;
import com.fiap.restaurant.booking.core.gateways.EnderecoGateway;
import com.fiap.restaurant.booking.core.usecases.endereco.GetAllEnderecosUseCase;

import java.util.List;

public class GetAllEnderecosUseCaseImpl implements GetAllEnderecosUseCase {
    private final EnderecoGateway enderecoGateway;

    public GetAllEnderecosUseCaseImpl(EnderecoGateway enderecoGateway) {
        this.enderecoGateway = enderecoGateway;
    }

    @Override
    public List<Endereco> execute() {
        return enderecoGateway.getAll();
    }
}
