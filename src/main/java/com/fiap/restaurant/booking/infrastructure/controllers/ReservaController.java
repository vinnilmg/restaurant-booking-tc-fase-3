package com.fiap.restaurant.booking.infrastructure.controllers;

import com.fiap.restaurant.booking.core.usecases.reserva.CancelReservaUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.ConfirmReservaUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.CreateReservaUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.FindCanceledReservasUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.FindConfirmedReservasUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.FindRequestedReservasUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.FindReservaByCpfUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.FindReservaByIdUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.GetAllReservasUseCase;
import com.fiap.restaurant.booking.infrastructure.controllers.mappers.ReservaMapper;
import com.fiap.restaurant.booking.infrastructure.controllers.request.ReservaRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.ReservaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequestMapping("/api/bookings")
@RestController
public class ReservaController {
    private final CreateReservaUseCase createReservaUseCase;
    private final GetAllReservasUseCase getAllReservasUseCase;
    private final FindReservaByCpfUseCase findReservaByCpfUseCase;
    private final FindCanceledReservasUseCase findCanceledReservasUseCase;
    private final FindRequestedReservasUseCase findRequestedReservasUseCase;
    private final FindConfirmedReservasUseCase findConfirmedReservasUseCase;
    private final FindReservaByIdUseCase findReservaByIdUseCase;
    private final CancelReservaUseCase cancelReservaUseCase;
    private final ConfirmReservaUseCase confirmReservaUseCase;
    private final ReservaMapper reservaMapper;

    public ReservaController(
            CreateReservaUseCase createReservaUseCase,
            GetAllReservasUseCase getAllReservasUseCase,
            FindReservaByCpfUseCase findReservaByCpfUseCase,
            FindCanceledReservasUseCase findCanceledReservasUseCase,
            FindRequestedReservasUseCase findRequestedReservasUseCase,
            FindConfirmedReservasUseCase findConfirmedReservasUseCase,
            FindReservaByIdUseCase findReservaByIdUseCase,
            CancelReservaUseCase cancelReservaUseCase,
            ConfirmReservaUseCase confirmReservaUseCase,
            ReservaMapper reservaMapper
    ) {
        this.createReservaUseCase = createReservaUseCase;
        this.getAllReservasUseCase = getAllReservasUseCase;
        this.findReservaByCpfUseCase = findReservaByCpfUseCase;
        this.findCanceledReservasUseCase = findCanceledReservasUseCase;
        this.findRequestedReservasUseCase = findRequestedReservasUseCase;
        this.findConfirmedReservasUseCase = findConfirmedReservasUseCase;
        this.findReservaByIdUseCase = findReservaByIdUseCase;
        this.cancelReservaUseCase = cancelReservaUseCase;
        this.confirmReservaUseCase = confirmReservaUseCase;
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
    public ResponseEntity<List<ReservaResponse>> getBookingsByCpf(@PathVariable final String cpf) {
        final var reservas = findReservaByCpfUseCase.execute(cpf);
        return ResponseEntity.status(OK)
                .body(reservaMapper.toReservasResponse(reservas));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponse> getById(@PathVariable final Long id) {
        final var reserva = findReservaByIdUseCase.execute(id);
        return ResponseEntity.status(OK)
                .body(reservaMapper.toReservaResponse(reserva));
    }
    @GetMapping("/canceled")
    public ResponseEntity<List<ReservaResponse>> getCanceledBookings() {
        final var reservas = findCanceledReservasUseCase.execute();
        return ResponseEntity.status(OK)
                .body(reservaMapper.toReservasResponse(reservas));
    }

    @GetMapping("/requested")
    public ResponseEntity<List<ReservaResponse>> getRequestedBookings() {
        final var reservas = findRequestedReservasUseCase.execute();
        return ResponseEntity.status(OK)
                .body(reservaMapper.toReservasResponse(reservas));
    }

    @GetMapping("/confirmed")
    public ResponseEntity<List<ReservaResponse>> getConfirmedBookings() {
        final var reservas = findConfirmedReservasUseCase.execute();
        return ResponseEntity.status(OK)
                .body(reservaMapper.toReservasResponse(reservas));
    }

    @PutMapping("/cancel/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void cancelBooking(@PathVariable(name = "id") final Long idReserva) {
        cancelReservaUseCase.execute(idReserva);
    }

    @PutMapping("/confirm/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void confirmBooking(@PathVariable(name = "id") final Long idReserva) {
        confirmReservaUseCase.execute(idReserva);
    }
}
