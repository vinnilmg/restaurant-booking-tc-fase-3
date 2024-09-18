package com.fiap.restaurant.booking.core.usecases.restaurante;

import com.fiap.restaurant.booking.core.domains.Restaurante;

import java.util.Optional;

@FunctionalInterface
public interface FindRestauranteByCnpjUseCase {
    Optional<Restaurante> execute(String cnpj);
}