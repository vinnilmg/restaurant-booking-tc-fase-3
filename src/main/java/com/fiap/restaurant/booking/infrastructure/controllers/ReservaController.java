package com.fiap.restaurant.booking.infrastructure.controllers;

import com.fiap.restaurant.booking.core.usecases.CreateReservaUseCase;
import com.fiap.restaurant.booking.core.usecases.FindReservaByCpfUseCase;
import com.fiap.restaurant.booking.core.usecases.GetAllReservasUseCase;
import com.fiap.restaurant.booking.infrastructure.controllers.mappers.ReservaMapper;
import com.fiap.restaurant.booking.infrastructure.controllers.request.ReservaRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.ReservaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequestMapping("/api/bookings")
@RestController
public class ReservaController {
    private final CreateReservaUseCase createReservaUseCase;
    private final GetAllReservasUseCase getAllReservasUseCase;
    private final FindReservaByCpfUseCase findReservaByCpfUseCase;
    private final ReservaMapper reservaMapper;

    public ReservaController(
            CreateReservaUseCase createReservaUseCase,
            GetAllReservasUseCase getAllReservasUseCase,
            FindReservaByCpfUseCase findReservaByCpfUseCase,
            ReservaMapper reservaMapper
    ) {
        this.createReservaUseCase = createReservaUseCase;
        this.getAllReservasUseCase = getAllReservasUseCase;
        this.findReservaByCpfUseCase = findReservaByCpfUseCase;
        this.reservaMapper = reservaMapper;
    }

    @PostMapping
    public ResponseEntity<ReservaResponse> createBooking(@RequestBody final ReservaRequest request) {
        final var reserva = createReservaUseCase.execute(reservaMapper.toReserva(request));
        return ResponseEntity.status(CREATED)
                .body(reservaMapper.toReservaResponse(reserva));
    }

    @GetMapping
    public ResponseEntity<List<ReservaResponse>> getAllBookings() {
        final var reservas = getAllReservasUseCase.execute();
        return ResponseEntity.status(OK)
                .body(reservaMapper.toReservasResponse(reservas));
    }

    @GetMapping("/customers/{cpf}")
    public ResponseEntity<ReservaResponse> getBookingByCpf(@PathVariable final String cpf) {
        final var reserva = findReservaByCpfUseCase.execute(cpf);
        return ResponseEntity.status(OK)
                .body(reservaMapper.toReservaResponse(reserva));
    }
}
