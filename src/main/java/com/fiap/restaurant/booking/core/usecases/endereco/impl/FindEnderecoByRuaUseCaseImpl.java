package com.fiap.restaurant.booking.core.usecases.endereco.impl;

import com.fiap.restaurant.booking.core.domains.Endereco;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.EnderecoGateway;
import com.fiap.restaurant.booking.core.usecases.endereco.FindEnderecoByRuaUseCase;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class FindEnderecoByRuaUseCaseImpl implements FindEnderecoByRuaUseCase {
    private final EnderecoGateway enderecoGateway;

    public FindEnderecoByRuaUseCaseImpl(EnderecoGateway enderecoGateway) {
        this.enderecoGateway = enderecoGateway;
    }

    @Override
    public List<Endereco> execute(String rua) {
        if (isEmpty(rua)) throw ValidationException.of("Rua", "cannot be null");
        return enderecoGateway.findByRua(rua);
    }
}
