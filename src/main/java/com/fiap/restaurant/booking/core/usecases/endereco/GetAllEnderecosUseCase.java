package com.fiap.restaurant.booking.core.usecases.endereco;

import com.fiap.restaurant.booking.core.domains.Endereco;

import java.util.List;

public interface GetAllEnderecosUseCase {
    List<Endereco> execute();
}
