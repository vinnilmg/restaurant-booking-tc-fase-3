package com.fiap.restaurant.booking.core.usecases.endereco;

import com.fiap.restaurant.booking.core.domains.Endereco;

@FunctionalInterface
public interface AtualizaEnderecoUseCase {
    void execute(Endereco endereco, Long id);
}