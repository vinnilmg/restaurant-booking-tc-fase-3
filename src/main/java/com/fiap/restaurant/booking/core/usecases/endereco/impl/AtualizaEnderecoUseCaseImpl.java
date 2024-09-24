package com.fiap.restaurant.booking.core.usecases.endereco.impl;

import com.fiap.restaurant.booking.core.domains.Endereco;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.usecases.endereco.AtualizaEnderecoUseCase;
import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.gateways.EnderecoGateway;
import com.fiap.restaurant.booking.core.usecases.endereco.FindEnderecoByIdUseCase;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;

public class AtualizaEnderecoUseCaseImpl implements AtualizaEnderecoUseCase {
    private final FindEnderecoByIdUseCase findEnderecoByIdUseCase;
    private final EnderecoGateway enderecoGateway;

    public AtualizaEnderecoUseCaseImpl(FindEnderecoByIdUseCase findEnderecoByIdUseCase, EnderecoGateway enderecoGateway) {
        this.findEnderecoByIdUseCase = findEnderecoByIdUseCase;
        this.enderecoGateway = enderecoGateway;
    }

    @Override
    public void execute(Endereco endereco, Long id) {
        if (isNull(id) || id < 0) throw ValidationException.of("Endereco Id", "cannot be null or negative");
        if (isNull(endereco)) throw ValidationException.of("Endereco", "cannot be null");
        final var enderecoFind = findEnderecoByIdUseCase.execute(id);

        if (isNull(enderecoFind)) {
            throw NotFoundException.of("Endereço");
        }
        enderecoFind.updateEndereco(endereco);
        enderecoGateway.update(enderecoFind);
    }
}
