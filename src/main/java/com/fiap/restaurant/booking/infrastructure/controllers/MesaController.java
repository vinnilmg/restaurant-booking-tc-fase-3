package com.fiap.restaurant.booking.infrastructure.controllers;

import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;
import com.fiap.restaurant.booking.core.usecases.mesa.CreateMesaUseCase;
import com.fiap.restaurant.booking.core.usecases.mesa.DeleteMesaUseCase;
import com.fiap.restaurant.booking.core.usecases.mesa.FindMesaByIdRestauranteUseCase;
import com.fiap.restaurant.booking.core.usecases.mesa.FindMesaByStatusUseCase;
import com.fiap.restaurant.booking.infrastructure.controllers.mappers.MesaMapper;
import com.fiap.restaurant.booking.infrastructure.controllers.request.MesaRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.MesaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/tables")
public class MesaController {

    private final CreateMesaUseCase createMesaUseCase;
    private final MesaMapper mesaMapper;
    private final FindMesaByIdRestauranteUseCase findMesaByIdRestauranteUseCase;
    private final FindMesaByStatusUseCase findMesaByStatusUseCase;
    private final DeleteMesaUseCase deleteMesaUseCase;

    public MesaController(CreateMesaUseCase createMesaUseCase, MesaMapper mesaMapper, FindMesaByIdRestauranteUseCase findMesaByIdRestauranteUseCase, FindMesaByStatusUseCase findMesaByStatusUseCase, DeleteMesaUseCase deleteMesaUseCase) {
        this.createMesaUseCase = createMesaUseCase;
        this.mesaMapper = mesaMapper;
        this.findMesaByIdRestauranteUseCase = findMesaByIdRestauranteUseCase;
        this.findMesaByStatusUseCase = findMesaByStatusUseCase;
        this.deleteMesaUseCase = deleteMesaUseCase;
    }

    @PostMapping
    public ResponseEntity<MesaResponse> createMesa(@RequestBody final MesaRequest mesaRequest) {
        final var mesaDomain = mesaMapper.toMesaDomain(mesaRequest);
        final var mesa = createMesaUseCase.execute(mesaDomain, mesaRequest.restauranteId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mesaMapper.toMesaResponse(mesa));
    }

    @GetMapping
    public ResponseEntity<List<MesaResponse>> getAllMesas() {return null;}

    @GetMapping("/status/{status}")
    public ResponseEntity<List<MesaResponse>> getAllMesasFromStatus(@PathVariable String status) {
        StatusMesaEnum statusMesaEnum = StatusMesaEnum.valueOf(status.toUpperCase());
        var mesasComStatus = findMesaByStatusUseCase.execute(statusMesaEnum);

        var response = mesasComStatus.stream()
                .map(mesaMapper::toMesaResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/disponibilidade")
//    public ResponseEntity<MesaResponse> getMesaByIdRestaurante(@RequestParam("restauranteId") Long restauranteId, @RequestParam("numeroMesa") Long numeroMesa)
//    {
//        var mesaDomain = mesaMapper.toMesaDomain(restauranteId, numeroMesa);
//        final var response = mesaMapper.toMesaResponse(findMesaByIdRestauranteUseCase.execute(restauranteId, numeroMesa));
//        return ResponseEntity.status(201).body(response);
//    }

    @DeleteMapping
    public ResponseEntity<MesaResponse> deleteMesa(@RequestParam Long restauranteId, @RequestParam Integer numeroMesa) {
        final var mesa = deleteMesaUseCase.execute(restauranteId, numeroMesa);
        return ResponseEntity.noContent().build();
    }
}
