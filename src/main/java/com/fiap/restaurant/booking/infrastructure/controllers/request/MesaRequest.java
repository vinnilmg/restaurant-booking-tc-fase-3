package com.fiap.restaurant.booking.infrastructure.controllers.request;

import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;

public record MesaRequest(int numeroDaMesa,
                          StatusMesaEnum status
                          ) {
}
//TODO: Implementar Long idDoRestaurante