package com.fiap.restaurant.booking.core.usecases.restaurante;

import com.fiap.restaurant.booking.core.domains.RestauranteDomain;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.CreateRestauranteUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class CreateRestauranteUseCaseTest {
    private CreateRestauranteUseCaseImpl createRestauranteUseCase;
    private RestauranteGateway restauranteGateway;
    private FindRestauranteByCnpjUseCase findRestauranteByCnpjUseCase;

    @BeforeEach
    void init() {
        restauranteGateway = mock(RestauranteGateway.class);
        findRestauranteByCnpjUseCase = mock(FindRestauranteByCnpjUseCase.class);
        createRestauranteUseCase = new CreateRestauranteUseCaseImpl(
                restauranteGateway,
                findRestauranteByCnpjUseCase
        );
    }

    @Test
    void shouldCreateRestaurant() {
        final var restaurant = new RestauranteDomain(
                DEFAULT_NOME,
                DEFAULT_CNPJ,
                DEFAULT_ENDERECO_DOMAIN,
                DEFAULT_TIPO_CULINARIA,
                DEFAULT_TIME,
                DEFAULT_TIME,
                50,
                5.0);

        when(findRestauranteByCnpjUseCase.execute(restaurant.getCnpj()))
                .thenReturn(Optional.empty());

        when(restauranteGateway.create(restaurant))
                .thenAnswer(i -> i.getArguments()[0]);

        final var result = createRestauranteUseCase.execute(restaurant);

        assertThat(result)
                .isNotNull()
                .isEqualTo(restaurant);

        verify(findRestauranteByCnpjUseCase).execute(restaurant.getCnpj());
        verify(restauranteGateway).create(restaurant);
    }

    @Test
    void shouldThrowValidationExceptionWhenCreateRestaurantaWithCnpjAlreadyExistent() {
        final var restaurant = new RestauranteDomain(
                DEFAULT_NOME,
                DEFAULT_CNPJ,
                DEFAULT_ENDERECO_DOMAIN,
                DEFAULT_TIPO_CULINARIA,
                DEFAULT_TIME,
                DEFAULT_TIME,
                50,
                5.0);

        when(findRestauranteByCnpjUseCase.execute(restaurant.getCnpj()))
                .thenReturn(Optional.of(restaurant));

        assertThatThrownBy(() -> createRestauranteUseCase.execute(restaurant))
                .isInstanceOf(ValidationException.class)
                .hasMessage("CNPJ already exists");

        verify(findRestauranteByCnpjUseCase).execute(restaurant.getCnpj());
        verifyNoInteractions(restauranteGateway);
    }

}
