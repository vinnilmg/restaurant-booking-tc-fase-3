package com.fiap.restaurant.booking.core.usecases.feedback;

import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.impl.FindFeedBackByIdRestauranteUseCaseImpl;
import com.fiap.restaurant.booking.utils.InformationsFeedbackConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertThatThrownBy(() ->
                findFeedBackByIdRestauranteUseCase.execute(InformationsFeedbackConstants.DEFAULT_FEEDBACK_ID))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(InformationsFeedbackConstants.getMessageIdRestauranteNotFound(InformationsFeedbackConstants.DEFAULT_FEEDBACK_ID)
                        , InformationsFeedbackConstants.DEFAULT_FEEDBACK_ID);
    }

    @Test
    void shouldThrowValidationExceptionWhenIdIsNull() {
        assertThatThrownBy(() -> findFeedBackByIdRestauranteUseCase.execute(null))
                .isInstanceOf(ValidationException.class)
                .hasMessage(InformationsFeedbackConstants.MESSAGE_WHEN_ID_RESTAURANTE_IS_NULL);

    }

    @Test
    void shouldReturnFeedbackByIdRestaurante() {
        Long idRestaurante = 2L;

        var feedbackDomain = Optional.of(InformationsFeedbackConstants
                .buildFeedBackTest(InformationsFeedbackConstants.DEFAULT_FEEDBACK_ID, idRestaurante, Integer.valueOf(1))
        );

        when(feedBackGateway.findByIdRestaurante(idRestaurante)).thenReturn(feedbackDomain);

        final var result = findFeedBackByIdRestauranteUseCase.execute(idRestaurante);

        assertThat(result)
                .isNotNull()
                .isEqualTo(feedbackDomain.get());
    }

}
