package com.fiap.restaurant.booking.core.usecases.restaurante;

import com.fiap.restaurant.booking.core.domains.RestauranteDomain;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.CreateRestauranteUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.EnderecoDomainFixture;
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
    void shouldCreateRestaurante() {
        final var restaurante = new RestauranteDomain(
                DEFAULT_NOME,
                DEFAULT_CNPJ,
                EnderecoDomainFixture.OTHER(),
                DEFAULT_TIPO_CULINARIA,
                DEFAULT_TIME,
                DEFAULT_TIME,
                50,
                5.0);

        when(findRestauranteByCnpjUseCase.execute(restaurante.getCnpj()))
                .thenReturn(Optional.empty());

        when(restauranteGateway.create(restaurante))
                .thenAnswer(i -> i.getArguments()[0]);

        final var result = createRestauranteUseCase.execute(restaurante);

        assertThat(result)
                .isNotNull()
                .isEqualTo(restaurante);

        verify(findRestauranteByCnpjUseCase).execute(restaurante.getCnpj());
        verify(restauranteGateway).create(restaurante);
    }

    @Test
    void shouldThrowValidationExceptionWhenCreateRestauranteWithCnpjAlreadyExistent() {
        final var restaurante = new RestauranteDomain(
                DEFAULT_NOME,
                DEFAULT_CNPJ,
                EnderecoDomainFixture.OTHER(),
                DEFAULT_TIPO_CULINARIA,
                DEFAULT_TIME,
                DEFAULT_TIME,
                50,
                5.0);

        when(findRestauranteByCnpjUseCase.execute(restaurante.getCnpj()))
                .thenReturn(Optional.of(restaurante));

        assertThatThrownBy(() -> createRestauranteUseCase.execute(restaurante))
                .isInstanceOf(ValidationException.class)
                .hasMessage("CNPJ already exists");

        verify(findRestauranteByCnpjUseCase).execute(restaurante.getCnpj());
        verifyNoInteractions(restauranteGateway);
    }

}
