package com.fiap.restaurant.booking.core.usecases.mesa;

import com.fiap.restaurant.booking.core.domains.Mesa;
import com.fiap.restaurant.booking.infrastructure.controllers.response.MessageResponse;

public interface DeleteMesaUseCase {

    MessageResponse execute(Long id, Integer numeroMesa);
}
