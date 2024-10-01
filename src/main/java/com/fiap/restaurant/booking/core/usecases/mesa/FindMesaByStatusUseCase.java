package com.fiap.restaurant.booking.core.usecases.mesa;

import com.fiap.restaurant.booking.core.domains.Mesa;
import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;

import java.util.List;

public interface FindMesaByStatusUseCase {

    List<Mesa> execute(StatusMesaEnum status);
}
