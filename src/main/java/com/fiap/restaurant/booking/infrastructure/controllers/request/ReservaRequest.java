package com.fiap.restaurant.booking.infrastructure.controllers.request;

public record ReservaRequest(
        Long restauranteId,
        Long mesaId,
        String cpfCliente,
        String dataHoraReserva
) {
}
