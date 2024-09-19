package com.fiap.restaurant.booking.core.usecases.endereco;

import com.fiap.restaurant.booking.core.domains.Endereco;

import java.util.List;

public interface FindEnderecoByBairroUseCase {
    List<Endereco> execute(String bairro);
}
