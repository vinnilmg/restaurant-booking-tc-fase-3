package com.fiap.restaurant.booking.core.usecases.endereco.impl;

import com.fiap.restaurant.booking.core.domains.Endereco;
import com.fiap.restaurant.booking.core.usecases.endereco.AtualizaEnderecoUseCase;
import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.gateways.EnderecoGateway;
import com.fiap.restaurant.booking.core.usecases.endereco.FindEnderecoByIdUseCase;

import static java.util.Objects.isNull;

public class AtualizaEnderecoUseCaseImpl implements AtualizaEnderecoUseCase {
    private final FindEnderecoByIdUseCase findEnderecoByIdUseCase;
    private final EnderecoGateway enderecoGateway;

    public AtualizaEnderecoUseCaseImpl(FindEnderecoByIdUseCase findEnderecoByIdUseCase, EnderecoGateway enderecoGateway) {
        this.findEnderecoByIdUseCase = findEnderecoByIdUseCase;
        this.enderecoGateway = enderecoGateway;
    }

    @Override
    public void execute(Endereco endereco, Long id) {
        final var enderecoFind = findEnderecoByIdUseCase.execute(id);

        if (isNull(enderecoFind)) {
            throw NotFoundException.of("Endereço");
        }
        enderecoFind.updateEndereco(endereco);
        enderecoGateway.update(enderecoFind);
    }
}
