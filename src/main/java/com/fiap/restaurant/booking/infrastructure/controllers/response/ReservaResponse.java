package com.fiap.restaurant.booking.infrastructure.controllers.response;

public record ReservaResponse(
        Long id,
        String cpfCliente,
        String status,
        String dataHoraReserva,
        String dataHoraCriacao
) {
}
