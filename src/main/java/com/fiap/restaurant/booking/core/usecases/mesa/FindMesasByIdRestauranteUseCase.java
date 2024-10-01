package com.fiap.restaurant.booking.core.usecases.mesa;

import com.fiap.restaurant.booking.core.domains.Mesa;

import java.util.List;

public interface FindMesasByIdRestauranteUseCase {
    List<Mesa> execute(Long restauranteId);
}
