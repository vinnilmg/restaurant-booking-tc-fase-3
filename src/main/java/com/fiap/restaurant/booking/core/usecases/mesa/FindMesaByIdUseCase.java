package com.fiap.restaurant.booking.core.usecases.mesa;

import com.fiap.restaurant.booking.core.domains.Mesa;

@FunctionalInterface
public interface FindMesaByIdUseCase {
    Mesa execute(Long id);
}
