package com.fiap.restaurant.booking.infrastructure.controllers;

import com.fiap.restaurant.booking.core.domains.Mesa;
import com.fiap.restaurant.booking.core.usecases.mesa.CreateMesaUseCase;
import com.fiap.restaurant.booking.core.usecases.mesa.impl.CreateMesaUseCaseImpl;
import com.fiap.restaurant.booking.infrastructure.controllers.response.MesaResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesa")
public class MesaController {

    private final CreateMesaUseCase createMesaUseCase;

    public MesaController(CreateMesaUseCase createMesaUseCase) {
        this.createMesaUseCase = createMesaUseCase;
    }

    @PostMapping
    public ResponseEntity<Mesa> createMesa(@RequestBody @Valid Mesa mesa) {
       final var mesa = createMesaUseCase.execute(mesa);
    }

    @GetMapping
    public ResponseEntity<List<MesaResponse>> getAllMesas() {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MesaResponse> deleteMesa(@PathVariable Long id) {
        return null;

    }
}
