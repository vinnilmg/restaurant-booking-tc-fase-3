package com.fiap.restaurant.booking.core.domains;

import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;
import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;

public interface Mesa {

    Long getId();
    int getNumeroDaMesa();
    StatusMesaEnum getStatus();

    void updateStatus(StatusMesaEnum status);

}
