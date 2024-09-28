package com.fiap.restaurant.booking.infrastructure.controllers;

import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;
import com.fiap.restaurant.booking.core.usecases.mesa.CreateMesaUseCase;
import com.fiap.restaurant.booking.core.usecases.mesa.FindMesaByIdUseCase;
import com.fiap.restaurant.booking.core.usecases.mesa.FindMesaByStatusUseCase;
import com.fiap.restaurant.booking.core.usecases.mesa.impl.FindMesaByIdUseCaseImpl;
import com.fiap.restaurant.booking.infrastructure.controllers.mappers.MesaMapper;
import com.fiap.restaurant.booking.infrastructure.controllers.request.MesaRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.MesaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/mesa")
public class MesaController {

    private final CreateMesaUseCase createMesaUseCase;
    private final MesaMapper mesaMapper;
    private final FindMesaByIdUseCase findMesaByIdUseCase;
    private final FindMesaByStatusUseCase findMesaByStatusUseCase;

    public MesaController(CreateMesaUseCase createMesaUseCase, MesaMapper mesaMapper, FindMesaByIdUseCase findMesaByIdUseCase, FindMesaByStatusUseCase findMesaByStatusUseCase) {
        this.createMesaUseCase = createMesaUseCase;
        this.mesaMapper = mesaMapper;
        this.findMesaByIdUseCase = findMesaByIdUseCase;
        this.findMesaByStatusUseCase = findMesaByStatusUseCase;
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

    @GetMapping("/status/{status}")
    public ResponseEntity<List<MesaResponse>> getAllMesasFromStatus( @PathVariable StatusMesaEnum status) {
//        var statusEnum = mesaMapper.toMesaDomain(status);
//        final var response = mesaMapper.toMesaResponse(findMesaByStatusUseCase.execute(status));
//        return ResponseEntity.ok(response);
        var mesas = findMesaByStatusUseCase.execute(status);
        // Mapeia o resultado para a resposta
        var response = mesaMapper.toMesaResponse(mesas);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/disponibilidade")
    public ResponseEntity<MesaResponse> getMesaByIdRestaurante(@RequestParam("restauranteId") Long restauranteId, @RequestParam("numeroMesa") Integer numeroMesa)
    {
//        var mesaDomain = mesaMapper.toMesaDomain(restauranteId, numeroMesa);
        final var response = mesaMapper.toMesaResponse(findMesaByIdUseCase.execute(restauranteId, numeroMesa));
        return ResponseEntity.status(201).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MesaResponse> deleteMesa(@PathVariable Long id) {
        return null;
    }
}
