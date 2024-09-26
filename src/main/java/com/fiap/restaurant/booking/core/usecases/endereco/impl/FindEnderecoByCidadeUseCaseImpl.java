package com.fiap.restaurant.booking.core.usecases.endereco.impl;

import com.fiap.restaurant.booking.core.domains.Endereco;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.EnderecoGateway;
import com.fiap.restaurant.booking.core.usecases.endereco.FindEnderecoByCidadeUseCase;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class FindEnderecoByCidadeUseCaseImpl implements FindEnderecoByCidadeUseCase {
    private final EnderecoGateway enderecoGateway;

    public FindEnderecoByCidadeUseCaseImpl(EnderecoGateway enderecoGateway) {
        this.enderecoGateway = enderecoGateway;
    }

    @Override
    public List<Endereco> execute(String cidade) {
        if (isEmpty(cidade)) throw ValidationException.of("Cidade", "cannot be null");
        return enderecoGateway.findByCidade(cidade);
    }
}
