package com.fiap.restaurant.booking.core.usecases.feedback;

import com.fiap.restaurant.booking.core.domains.FeedBackDomain;
import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.impl.FindFeedBackByIdRestauranteUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindFeedBackByIdRestauranteUseCaseTest {
    private FindFeedBackByIdRestauranteUseCase findFeedBackByIdRestauranteUseCase;
    private FeedBackGateway feedBackGateway;

    @BeforeEach
    void init() {
        feedBackGateway = mock(FeedBackGateway.class);
        findFeedBackByIdRestauranteUseCase = new FindFeedBackByIdRestauranteUseCaseImpl(feedBackGateway);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenFeedBackIsNotFound() {
        assertThatThrownBy(() -> findFeedBackByIdRestauranteUseCase.execute(1L))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Feedback with id restaurante %s not found", 1L);
    }
    @Test
    void shouldThrowValidationExceptionWhenIdIsNull() {
       assertThatThrownBy(() -> findFeedBackByIdRestauranteUseCase.execute(null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("id restaurante cannot be null");

    }
    @Test
    void shouldReturnFeedbackByIdRestaurante() {
        var feedbackDomain = Optional.of(FeedBackDomain
                .builder()
                .id(1L)
                .avaliacao(Integer.valueOf(1))
                .comentario("Boulos Comunista")
                .dataHoraCriacao(LocalDateTime.now())
                .restauranteId(2L)
                .nomeCliente("Lula 13")
                .build());

        when(feedBackGateway.findByIdRestaurante(2L)).thenReturn(feedbackDomain);

        final var result = findFeedBackByIdRestauranteUseCase.execute(2L);

        assertThat(result)
                .isNotNull()
                .isEqualTo(feedbackDomain.get());
    }

}
