package com.fiap.restaurant.booking.core.usecases.mesa;

import com.fiap.restaurant.booking.core.domains.Mesa;

public interface FindIdRestauranteAndNumeroMesa {
    Mesa execute(Long restauranteId, Integer numeroMesa);
}
