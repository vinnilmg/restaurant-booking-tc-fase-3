package com.fiap.restaurant.booking.core.usecases.endereco;

import com.fiap.restaurant.booking.core.domains.Endereco;

@FunctionalInterface
public interface CreateEnderecoUseCase {
    Endereco execute(Endereco endereco);
}