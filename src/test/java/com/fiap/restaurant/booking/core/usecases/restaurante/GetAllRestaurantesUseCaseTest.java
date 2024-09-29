package com.fiap.restaurant.booking.core.usecases.restaurante;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.GetAllRestaurantesUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.RestauranteDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class GetAllRestaurantesUseCaseTest {
    private GetAllRestaurantesUseCaseImpl getAllRestaurantesUseCase;
    private RestauranteGateway restauranteGateway;

    @BeforeEach
    void init() {
        restauranteGateway = mock(RestauranteGateway.class);
        getAllRestaurantesUseCase = new GetAllRestaurantesUseCaseImpl(restauranteGateway);
    }

    @Test
    void shouldGetAllRestaurantes() {
        final var restaurante = RestauranteDomainFixture.NOVO();

        final List<Restaurante> restaurantes = List.of(restaurante);

        when(restauranteGateway.getAll()).thenReturn(restaurantes);

        final var result = getAllRestaurantesUseCase.execute();

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(restaurantes.size());

        verify(restauranteGateway).getAll();
    }
}
