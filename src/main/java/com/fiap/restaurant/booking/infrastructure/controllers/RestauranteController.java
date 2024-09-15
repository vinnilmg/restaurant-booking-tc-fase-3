package com.fiap.restaurant.booking.infrastructure.controllers;

import com.fiap.restaurant.booking.core.usecases.restaurante.*;
import com.fiap.restaurant.booking.infrastructure.controllers.mappers.RestauranteMapper;
import com.fiap.restaurant.booking.infrastructure.controllers.request.RestauranteRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.RestauranteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequestMapping("/api/restaurants")
@RestController
public class RestauranteController {
    private final CreateRestauranteUseCase createRestauranteUseCase;
    private final GetAllRestaurantesUseCase getAllRestaurantesUseCase;
    private final FindRestauranteByIdUseCase findRestauranteByIdUseCase;
    private final FindRestauranteByNomeUseCase findRestauranteByNomeUseCase;
    private final FindRestauranteByTipoCulinariaUseCase findRestauranteByTipoCulinariaUseCase;
    private final FindRestauranteByMediaFeedbackUseCase findRestauranteByMediaFeedbackUseCase;
    private final RestauranteMapper restauranteMapper;

    public RestauranteController(CreateRestauranteUseCase createRestauranteUseCase,
                                 GetAllRestaurantesUseCase getAllRestaurantesUseCase,
                                 FindRestauranteByIdUseCase findRestauranteByIdUseCase,
                                 FindRestauranteByNomeUseCase findRestauranteByNomeUseCase,
                                 FindRestauranteByTipoCulinariaUseCase findRestauranteByTipoCulinariaUseCase,
                                 FindRestauranteByMediaFeedbackUseCase findRestauranteByMediaFeedbackUseCase,
                                 RestauranteMapper restauranteMapper) {
        this.createRestauranteUseCase = createRestauranteUseCase;
        this.getAllRestaurantesUseCase = getAllRestaurantesUseCase;
        this.findRestauranteByIdUseCase = findRestauranteByIdUseCase;
        this.findRestauranteByNomeUseCase = findRestauranteByNomeUseCase;
        this.findRestauranteByTipoCulinariaUseCase = findRestauranteByTipoCulinariaUseCase;
        this.findRestauranteByMediaFeedbackUseCase = findRestauranteByMediaFeedbackUseCase;
        this.restauranteMapper = restauranteMapper;
    }

    @PostMapping
    public ResponseEntity<RestauranteResponse> createRestaurant(@RequestBody final RestauranteRequest request) {
        final var restaurante = createRestauranteUseCase.execute(restauranteMapper.toRestaurante(request));
        return ResponseEntity.status(CREATED)
                .body(restauranteMapper.toRestauranteResponse(restaurante));
    }

    @GetMapping
    public ResponseEntity<List<RestauranteResponse>> getAllRestaurants() {
        final var restaurantes = getAllRestaurantesUseCase.execute();
        return ResponseEntity.status(OK)
                .body(restauranteMapper.toRestauranteResponse(restaurantes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteResponse> getById(@PathVariable final Long id) {
        final var restaurante = findRestauranteByIdUseCase.execute(id);
        return ResponseEntity.status(OK)
                .body(restauranteMapper.toRestauranteResponse(restaurante));
    }

    @GetMapping("name/{name}")
    public ResponseEntity<List<RestauranteResponse>> getRestaurantsByName(@PathVariable final String name) {
        final var restaurantes = findRestauranteByNomeUseCase.execute(name);
        return ResponseEntity.status(OK)
                .body(restauranteMapper.toRestauranteResponse(restaurantes));
    }

    @GetMapping("cuisine/{cuisine}")
    public ResponseEntity<List<RestauranteResponse>> getRestaurantsByCuisine(@PathVariable final String cuisine) {
        final var restaurantes = findRestauranteByTipoCulinariaUseCase.execute(cuisine);
        return ResponseEntity.status(OK)
                .body(restauranteMapper.toRestauranteResponse(restaurantes));
    }

    @GetMapping("feedback/{feedback}")
    public ResponseEntity<List<RestauranteResponse>> getRestaurantsByCuisine(@PathVariable final Double feedback) {
        final var restaurantes = findRestauranteByMediaFeedbackUseCase.execute(feedback);
        return ResponseEntity.status(OK)
                .body(restauranteMapper.toRestauranteResponse(restaurantes));
    }




}
