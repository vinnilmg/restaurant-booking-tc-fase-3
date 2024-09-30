package com.fiap.restaurant.booking.core.usecases.mesa;

import com.fiap.restaurant.booking.infrastructure.controllers.response.MessageResponse;

public interface DeleteMesaUseCase {

    MessageResponse execute(Long restauranteId, Integer numeroMesa);
}
