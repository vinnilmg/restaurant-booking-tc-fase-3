package com.fiap.restaurant.booking.core.usecases.restaurante;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.FindRestauranteByMediaFeedbackUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.RestauranteDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class FindRestauranteByMediaFeedbackUseCaseTest {
    private FindRestauranteByMediaFeedbackUseCaseImpl findRestauranteByMediaFeedbackUseCase;
    private RestauranteGateway restauranteGateway;

    @BeforeEach
    void init() {
        restauranteGateway = mock(RestauranteGateway.class);
        findRestauranteByMediaFeedbackUseCase = new FindRestauranteByMediaFeedbackUseCaseImpl(restauranteGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenMediaFeedbackIsNull() {
        assertThatThrownBy(() -> findRestauranteByMediaFeedbackUseCase.execute(null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Media Feedback cannot be null or negative");

        verifyNoInteractions(restauranteGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenMediaFeedbackIsNegative() {
        assertThatThrownBy(() -> findRestauranteByMediaFeedbackUseCase.execute(-1d))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Media Feedback cannot be null or negative");

        verifyNoInteractions(restauranteGateway);
    }

    @Test
    void shouldFindRestauranteByMediaFeedback() {
        final var mediaFeedback = 5.0;
        final var restaurante = RestauranteDomainFixture.NOVO();

        final List<Restaurante> expected = List.of(restaurante);

        when(restauranteGateway.findByMediaFeedback(mediaFeedback)).thenReturn(expected);

        final var result = findRestauranteByMediaFeedbackUseCase.execute(mediaFeedback);

        assertThat(result)
                .isNotNull()
                .hasSize(expected.size())
                .isEqualTo(expected);

        verify(restauranteGateway).findByMediaFeedback(mediaFeedback);
    }
}
