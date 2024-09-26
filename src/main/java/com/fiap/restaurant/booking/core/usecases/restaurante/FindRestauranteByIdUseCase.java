package com.fiap.restaurant.booking.core.usecases.restaurante;

import com.fiap.restaurant.booking.core.domains.Restaurante;

@FunctionalInterface
public interface FindRestauranteByIdUseCase {
    Restaurante execute(Long id);
}
