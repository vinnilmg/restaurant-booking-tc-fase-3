package com.fiap.restaurant.booking.core.usecases.endereco.impl;

import com.fiap.restaurant.booking.core.domains.Endereco;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.EnderecoGateway;
import com.fiap.restaurant.booking.core.usecases.endereco.FindEnderecoByBairroUseCase;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class FindEnderecoByBairroUseCaseImpl implements FindEnderecoByBairroUseCase {
    private final EnderecoGateway enderecoGateway;

    public FindEnderecoByBairroUseCaseImpl(EnderecoGateway enderecoGateway) {
        this.enderecoGateway = enderecoGateway;
    }

    @Override
    public List<Endereco> execute(String bairro) {
        if (isEmpty(bairro)) throw ValidationException.of("Bairro", "cannot be null");
        return enderecoGateway.findByBairro(bairro);
    }
}
