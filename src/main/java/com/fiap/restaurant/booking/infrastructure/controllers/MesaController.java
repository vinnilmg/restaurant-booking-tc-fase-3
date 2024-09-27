package com.fiap.restaurant.booking.infrastructure.controllers;

import com.fiap.restaurant.booking.core.usecases.mesa.CreateMesaUseCase;
import com.fiap.restaurant.booking.infrastructure.controllers.mappers.MesaMapper;
import com.fiap.restaurant.booking.infrastructure.controllers.request.MesaRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.MesaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/mesa")
public class MesaController {

    private final CreateMesaUseCase createMesaUseCase;
    private final MesaMapper mesaMapper;

    public MesaController(CreateMesaUseCase createMesaUseCase, MesaMapper mesaMapper) {
        this.createMesaUseCase = createMesaUseCase;
        this.mesaMapper = mesaMapper;
    }

    @PostMapping
    public ResponseEntity<MesaResponse> createMesa(@RequestBody final MesaRequest mesaRequest) {
        var mesaDomain = mesaMapper.toMesaDomain(mesaRequest);

        final var response =  mesaMapper
                .toMesaResponse(createMesaUseCase
                        .execute(mesaDomain,
                                mesaRequest.restauranteId()));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
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
