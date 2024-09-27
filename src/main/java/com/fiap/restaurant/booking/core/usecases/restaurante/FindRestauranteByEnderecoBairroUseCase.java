package com.fiap.restaurant.booking.core.usecases.restaurante;

import com.fiap.restaurant.booking.core.domains.Restaurante;

import java.util.List;

@FunctionalInterface
public interface FindRestauranteByEnderecoBairroUseCase {
    List<Restaurante> execute(String bairro);
}
