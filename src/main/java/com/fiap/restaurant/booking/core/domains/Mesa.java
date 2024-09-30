package com.fiap.restaurant.booking.core.domains;

import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;

public interface Mesa {

    Long getId();

    Restaurante getRestaurante();

    Restaurante setRestaurante(Restaurante restaurante);

    int getNumeroDaMesa();

    StatusMesaEnum getStatus();

    void updateStatus(StatusMesaEnum status);

}
