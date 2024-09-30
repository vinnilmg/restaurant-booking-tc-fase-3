package com.fiap.restaurant.booking.core.usecases.restaurante;

import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.FindRestauranteByIdUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.RestauranteDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

public class FindRestauranteByIdUseCaseTest {
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
        final var restaurante = RestauranteDomainFixture.NOVO();

        when(restauranteGateway.findById(id)).thenReturn(Optional.of(restaurante));

        final var result = findRestauranteByIdUseCase.execute(id);
        assertThat(result)
                .isNotNull()
                .isEqualTo(restaurante);

        verify(restauranteGateway).findById(id);
    }
}
