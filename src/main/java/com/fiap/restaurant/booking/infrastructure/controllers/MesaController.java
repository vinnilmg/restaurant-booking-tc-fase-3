package com.fiap.restaurant.booking.infrastructure.controllers;

import com.fiap.restaurant.booking.core.usecases.mesa.CreateMesaUseCase;
import com.fiap.restaurant.booking.core.usecases.mesa.FindMesaByRestauranteUseCase;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/tables")
public class MesaController {

    private final CreateMesaUseCase createMesaUseCase;
    private final MesaMapper mesaMapper;
    private final FindMesaByRestauranteUseCase findMesaByIdUseCase;

    public MesaController(CreateMesaUseCase createMesaUseCase, MesaMapper mesaMapper, FindMesaByRestauranteUseCase findMesaByIdUseCase) {
        this.createMesaUseCase = createMesaUseCase;
        this.mesaMapper = mesaMapper;
        this.findMesaByIdUseCase = findMesaByIdUseCase;
    }

    @PostMapping
    public ResponseEntity<MesaResponse> createMesa(@RequestBody final MesaRequest mesaRequest) {
        final var mesaDomain = mesaMapper.toMesaDomain(mesaRequest);
        final var mesa = createMesaUseCase.execute(mesaDomain, mesaRequest.restauranteId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mesaMapper.toMesaResponse(mesa));
    }

    @GetMapping
    public ResponseEntity<List<MesaResponse>> getAllMesas() {

        return null;
    }

    @GetMapping("/disponibilidade")
    public ResponseEntity<MesaResponse> getMesaByIdRestaurante(@RequestParam("restauranteId") Long restauranteId, @RequestParam("numeroMesa") Integer numeroMesa) {
//        var mesaDomain = mesaMapper.toMesaDomain(restauranteId, numeroMesa);
        final var response = mesaMapper.toMesaResponse(findMesaByIdUseCase.execute(restauranteId, numeroMesa));
        return ResponseEntity.status(201).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MesaResponse> deleteMesa(@PathVariable Long id) {
        return null;
    }
}
