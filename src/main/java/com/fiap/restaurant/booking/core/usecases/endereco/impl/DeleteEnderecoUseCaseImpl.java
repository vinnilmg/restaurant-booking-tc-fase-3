package com.fiap.restaurant.booking.core.usecases.endereco.impl;

import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.EnderecoGateway;
import com.fiap.restaurant.booking.core.usecases.endereco.DeleteEnderecoUseCase;
import com.fiap.restaurant.booking.core.usecases.endereco.FindEnderecoByIdUseCase;

import static java.util.Objects.isNull;

public class DeleteEnderecoUseCaseImpl implements DeleteEnderecoUseCase {
    private final FindEnderecoByIdUseCase findEnderecoByIdUseCase;
    private final EnderecoGateway enderecoGateway;

    public DeleteEnderecoUseCaseImpl(FindEnderecoByIdUseCase findEnderecoByIdUseCase, EnderecoGateway enderecoGateway) {
        this.findEnderecoByIdUseCase = findEnderecoByIdUseCase;
        this.enderecoGateway = enderecoGateway;
    }

    @Override
    public void execute(final Long id) {
        if (isNull(id) || id < 0) throw ValidationException.of("Endereco Id", "cannot be null or negative");
        final var endereco = findEnderecoByIdUseCase.execute(id);

        if (isNull(endereco)) {
            throw NotFoundException.of("Endereço");
        }
        enderecoGateway.delete(id);
    }
}
