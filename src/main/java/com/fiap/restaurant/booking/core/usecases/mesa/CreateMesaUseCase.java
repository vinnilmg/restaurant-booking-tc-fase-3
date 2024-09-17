package com.fiap.restaurant.booking.core.usecases.mesa;

import com.fiap.restaurant.booking.core.domains.MesaDomain;

@FunctionalInterface
public interface CreateMesaUseCase {

    MesaDomain execute(MesaDomain mesaDomain);
}
