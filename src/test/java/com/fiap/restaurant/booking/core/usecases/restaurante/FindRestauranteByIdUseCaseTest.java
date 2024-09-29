package com.fiap.restaurant.booking.core.usecases.restaurante;

import com.fiap.restaurant.booking.core.domains.RestauranteDomain;
import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.FindRestauranteByIdUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.EnderecoDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_CNPJ;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_NOME;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_TIME;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_TIPO_CULINARIA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class FindRestauranteByIdUseCaseTest {
    private FindRestauranteByIdUseCaseImpl findRestauranteByIdUseCase;
    private RestauranteGateway restauranteGateway;

    @BeforeEach
    void init() {
        restauranteGateway = mock(RestauranteGateway.class);
        findRestauranteByIdUseCase = new FindRestauranteByIdUseCaseImpl(restauranteGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenIdIsNull() {
        assertThatThrownBy(() -> findRestauranteByIdUseCase.execute(null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Restaurante Id cannot be null or negative");

        verifyNoInteractions(restauranteGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenIdIsNegative() {
        assertThatThrownBy(() -> findRestauranteByIdUseCase.execute(-1L))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Restaurante Id cannot be null or negative");

        verifyNoInteractions(restauranteGateway);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenReservaIsNotFound() {
        final var id = 1L;

        when(restauranteGateway.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> findRestauranteByIdUseCase.execute(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Restaurante not found");

        verify(restauranteGateway).findById(id);
    }

    @Test
    void shouldFindRestauranteById() {
        final var id = 1L;
        final var restaurante = new RestauranteDomain(
                DEFAULT_NOME,
                DEFAULT_CNPJ,
                EnderecoDomainFixture.OTHER(),
                DEFAULT_TIPO_CULINARIA,
                DEFAULT_TIME,
                DEFAULT_TIME,
                50,
                5.0);

        when(restauranteGateway.findById(id)).thenReturn(Optional.of(restaurante));

        final var result = findRestauranteByIdUseCase.execute(id);
        assertThat(result)
                .isNotNull()
                .isEqualTo(restaurante);

        verify(restauranteGateway).findById(id);
    }
}
