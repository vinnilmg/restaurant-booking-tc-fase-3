package com.fiap.restaurant.booking.core.usecases.feedback;

import com.fiap.restaurant.booking.core.domains.FeedBackDomain;
import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.impl.FindFeedBackByIdUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindFeedBackByIdUseCaseTest {
    private FindFeedBackByIdUseCase findFeedBackByIdUseCase;
    private FeedBackGateway feedBackGateway;

    @BeforeEach
    void init() {
        feedBackGateway = mock(FeedBackGateway.class);
        findFeedBackByIdUseCase = new FindFeedBackByIdUseCaseImpl(feedBackGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenIdIsNull() {
        assertThatThrownBy(() -> findFeedBackByIdUseCase.execute(null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("id cannot be null");
    }

    @Test
    void shouldThrowNotFoundExceptionWhenFeedBackIsNotFound() {
        when(feedBackGateway.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> findFeedBackByIdUseCase.execute(1L))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Feedback with id 1 not found");

    }

    @Test
    void shouldReturnFeedback() {
        var feedbackDomain = Optional.of(FeedBackDomain
                .builder()
                .id(2L)
                .avaliacao(Integer.valueOf(1))
                .comentario("Boulos Comunista")
                .dataHoraCriacao(LocalDateTime.now())
                .restauranteId(2L)
                .nomeCliente("Lula 13")
                .build());

        when(feedBackGateway.findById(1L)).thenReturn(feedbackDomain);

        final var result = findFeedBackByIdUseCase.execute(1L);

        assertThat(result)
                .isNotNull()
                .isEqualTo(feedbackDomain.get());
    }


}
