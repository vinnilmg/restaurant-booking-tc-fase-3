package com.fiap.restaurant.booking.core.usecases.mesa;

import com.fiap.restaurant.booking.core.domains.MesaDomain;
import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;

public interface FindMesaByStatusUseCase {

    MesaDomain execute(StatusMesaEnum status);
}
