package com.fiap.restaurant.booking.infrastructure.controllers.request;

public record ReservaRequest(
        Integer restauranteId,
        Integer mesaId,
        String cpfCliente,
        String dataHoraReserva
) {
}
