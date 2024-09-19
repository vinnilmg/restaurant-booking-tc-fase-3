package com.fiap.restaurant.booking.core.usecases.endereco.impl;

import com.fiap.restaurant.booking.core.domains.Endereco;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.EnderecoGateway;
import com.fiap.restaurant.booking.core.usecases.endereco.FindEnderecoByCepUseCase;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class FindEnderecoByCepUseCaseImpl implements FindEnderecoByCepUseCase {
    private final EnderecoGateway enderecoGateway;

    public FindEnderecoByCepUseCaseImpl(EnderecoGateway enderecoGateway) {
        this.enderecoGateway = enderecoGateway;
    }

    @Override
    public List<Endereco> execute(String cep) {
        if (isEmpty(cep)) throw ValidationException.of("Cep", "cannot be null");
        return enderecoGateway.findByCep(cep);
    }
}
