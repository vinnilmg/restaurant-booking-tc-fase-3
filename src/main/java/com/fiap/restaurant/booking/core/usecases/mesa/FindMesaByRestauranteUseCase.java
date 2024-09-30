package com.fiap.restaurant.booking.core.usecases.mesa;

import com.fiap.restaurant.booking.core.domains.Mesa;

public interface FindMesaByRestauranteUseCase {
    Mesa execute(Long idRestaurante,  Integer numeroMesa);
}