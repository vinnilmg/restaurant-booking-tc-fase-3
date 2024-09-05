package com.fiap.restaurant.booking.infrastructure.controllers;

import com.fiap.restaurant.booking.core.usecases.CreateReservaUseCase;
import com.fiap.restaurant.booking.infrastructure.controllers.mappers.ReservaMapper;
import com.fiap.restaurant.booking.infrastructure.controllers.request.ReservaRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.ReservaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/booking")
@RestController
public class ReservaController {
    private final CreateReservaUseCase createReservaUseCase;
    private final ReservaMapper reservaMapper;

    public ReservaController(
            CreateReservaUseCase createReservaUseCase,
            ReservaMapper reservaMapper
    ) {
        this.createReservaUseCase = createReservaUseCase;
        this.reservaMapper = reservaMapper;
    }

    @PostMapping
    public ResponseEntity<ReservaResponse> createBooking(@RequestBody final ReservaRequest request) {
        final var reserva = createReservaUseCase.execute(reservaMapper.toReserva(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservaMapper.toReservaResponse(reserva));
    }
}
