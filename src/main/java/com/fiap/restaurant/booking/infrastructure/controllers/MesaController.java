package com.fiap.restaurant.booking.infrastructure.controllers;

import com.fiap.restaurant.booking.core.usecases.mesa.CreateMesaUseCase;
import com.fiap.restaurant.booking.infrastructure.controllers.mappers.MesaMapper;
import com.fiap.restaurant.booking.infrastructure.controllers.request.MesaRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.MesaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<MesaResponse> createMesa(@RequestBody final MesaRequest request) {
        log.info("Request received: {}", request);
       final var response = mesaMapper.toMesaResponse(createMesaUseCase.execute(mesaMapper.toMesaDomain(request)));
        log.info("MesaDomain created: {}", mesaMapper.toMesaDomain(request));
//feedBackMapper.toFeedbackResponse(
//                createFeedBackUseCase.execute(feedBackMapper.toFeedBackDomain(feedBackRequest)));
       return ResponseEntity.status(201).body(response);
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
