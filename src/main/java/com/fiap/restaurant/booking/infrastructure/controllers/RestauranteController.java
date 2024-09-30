package com.fiap.restaurant.booking.infrastructure.controllers;

import com.fiap.restaurant.booking.core.usecases.endereco.CreateEnderecoUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.CreateRestauranteUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByEnderecoBairroUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByEnderecoCidadeUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByEnderecoRuaUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByIdUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByMediaFeedbackUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByNomeUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByTipoCulinariaUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.GetAllRestaurantesUseCase;
import com.fiap.restaurant.booking.infrastructure.controllers.mappers.EnderecoMapper;
import com.fiap.restaurant.booking.infrastructure.controllers.mappers.RestauranteMapper;
import com.fiap.restaurant.booking.infrastructure.controllers.request.RestauranteRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.RestauranteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private final FindRestauranteByEnderecoRuaUseCase findRestauranteByEnderecoRuaUseCase;
    private final FindRestauranteByEnderecoBairroUseCase findRestauranteByEnderecoBairroUseCase;
    private final FindRestauranteByEnderecoCidadeUseCase findRestauranteByEnderecoCidadeUseCase;
    private final FindRestauranteByTipoCulinariaUseCase findRestauranteByTipoCulinariaUseCase;
    private final FindRestauranteByMediaFeedbackUseCase findRestauranteByMediaFeedbackUseCase;
    private final CreateEnderecoUseCase createEnderecoUseCase;
    private final RestauranteMapper restauranteMapper;
    private final EnderecoMapper enderecoMapper;

    public RestauranteController(CreateRestauranteUseCase createRestauranteUseCase,
                                 GetAllRestaurantesUseCase getAllRestaurantesUseCase,
                                 FindRestauranteByIdUseCase findRestauranteByIdUseCase,
                                 FindRestauranteByNomeUseCase findRestauranteByNomeUseCase,
                                 FindRestauranteByEnderecoRuaUseCase findRestauranteByEnderecoRuaUseCase,
                                 FindRestauranteByEnderecoBairroUseCase findRestauranteByEnderecoBairroUseCase,
                                 FindRestauranteByEnderecoCidadeUseCase findRestauranteByEnderecoCidadeUseCase,
                                 FindRestauranteByTipoCulinariaUseCase findRestauranteByTipoCulinariaUseCase,
                                 FindRestauranteByMediaFeedbackUseCase findRestauranteByMediaFeedbackUseCase,
                                 CreateEnderecoUseCase createEnderecoUseCase,
                                 RestauranteMapper restauranteMapper, EnderecoMapper enderecoMapper) {
        this.createRestauranteUseCase = createRestauranteUseCase;
        this.getAllRestaurantesUseCase = getAllRestaurantesUseCase;
        this.findRestauranteByIdUseCase = findRestauranteByIdUseCase;
        this.findRestauranteByNomeUseCase = findRestauranteByNomeUseCase;
        this.findRestauranteByEnderecoRuaUseCase = findRestauranteByEnderecoRuaUseCase;
        this.findRestauranteByEnderecoBairroUseCase = findRestauranteByEnderecoBairroUseCase;
        this.findRestauranteByEnderecoCidadeUseCase = findRestauranteByEnderecoCidadeUseCase;
        this.findRestauranteByTipoCulinariaUseCase = findRestauranteByTipoCulinariaUseCase;
        this.findRestauranteByMediaFeedbackUseCase = findRestauranteByMediaFeedbackUseCase;
        this.createEnderecoUseCase = createEnderecoUseCase;
        this.restauranteMapper = restauranteMapper;
        this.enderecoMapper = enderecoMapper;
    }

    @PostMapping
    public ResponseEntity<RestauranteResponse> createRestaurant(@RequestBody final RestauranteRequest request) {

        final var endereco = createEnderecoUseCase.execute(enderecoMapper.toEndereco(request.endereco()));
        final var restaurant = createRestauranteUseCase.execute(restauranteMapper.toRestaurante(request, endereco));
        return ResponseEntity
                .status(CREATED)
                .body(restauranteMapper.toRestauranteResponse(restaurant));
    }

    @GetMapping
    public ResponseEntity<List<RestauranteResponse>> getAllRestaurants() {
        final var restaurants = getAllRestaurantesUseCase.execute();
        return ResponseEntity.status(OK)
                .body(restauranteMapper.toRestauranteResponse(restaurants));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteResponse> getById(@PathVariable final Long id) {
        final var restaurant = findRestauranteByIdUseCase.execute(id);
        return ResponseEntity.status(OK)
                .body(restauranteMapper.toRestauranteResponse(restaurant));
    }

    @GetMapping("name/{name}")
    public ResponseEntity<List<RestauranteResponse>> getRestaurantsByName(@PathVariable final String name) {
        final var restaurants = findRestauranteByNomeUseCase.execute(name);
        return ResponseEntity.status(OK)
                .body(restauranteMapper.toRestauranteResponse(restaurants));
    }

    @GetMapping("street/{street}")
    public ResponseEntity<List<RestauranteResponse>> getRestauranteByAddressStreet(@PathVariable final String street) {
        final var restaurants = findRestauranteByEnderecoRuaUseCase.execute(street);
        return ResponseEntity.status(OK)
                .body(restauranteMapper.toRestauranteResponse(restaurants));
    }

    @GetMapping("district/{district}")
    public ResponseEntity<List<RestauranteResponse>> getRestauranteByAddressDistrict(@PathVariable final String district) {
        final var restaurants = findRestauranteByEnderecoBairroUseCase.execute(district);
        return ResponseEntity.status(OK)
                .body(restauranteMapper.toRestauranteResponse(restaurants));
    }

    @GetMapping("city/{city}")
    public ResponseEntity<List<RestauranteResponse>> getRestauranteByAddressCity(@PathVariable final String city) {
        final var restaurants = findRestauranteByEnderecoCidadeUseCase.execute(city);
        return ResponseEntity.status(OK)
                .body(restauranteMapper.toRestauranteResponse(restaurants));
    }

    @GetMapping("cuisine/{cuisine}")
    public ResponseEntity<List<RestauranteResponse>> getRestaurantsByCuisine(@PathVariable final String cuisine) {
        final var restaurantes = findRestauranteByTipoCulinariaUseCase.execute(cuisine);
        return ResponseEntity.status(OK)
                .body(restauranteMapper.toRestauranteResponse(restaurantes));
    }

    @GetMapping("feedback/{feedback}")
    public ResponseEntity<List<RestauranteResponse>> getRestaurantsByFeedback(@PathVariable final Double feedback) {
        final var restaurantes = findRestauranteByMediaFeedbackUseCase.execute(feedback);
        return ResponseEntity.status(OK)
                .body(restauranteMapper.toRestauranteResponse(restaurantes));
    }
}
