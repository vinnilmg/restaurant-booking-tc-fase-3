package com.fiap.restaurant.booking.core.usecases.endereco.impl;

import com.fiap.restaurant.booking.core.domains.Endereco;
import com.fiap.restaurant.booking.core.usecases.endereco.FindEnderecoByIdUseCase;
import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.EnderecoGateway;

import static java.util.Objects.isNull;

public class FindEnderecoByIdUseCaseImpl implements FindEnderecoByIdUseCase {
    private final EnderecoGateway enderecoGateway;

    public FindEnderecoByIdUseCaseImpl(EnderecoGateway enderecoGateway) {
        this.enderecoGateway = enderecoGateway;
    }

    @Override
    public Endereco execute(Long id) {
        if (isNull(id) || id < 0) throw ValidationException.of("Endereço ID", "cannot be null or negative");
        return enderecoGateway.findByid(id)
                .orElseThrow(() -> NotFoundException.of("Endereço"));
    }
}
