package com.fiap.restaurant.booking.core.usecases.feedback;

import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.impl.FindFeedBackByIdUseCaseImpl;
import com.fiap.restaurant.booking.utils.InformationsFeedbackConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
                .hasMessage(InformationsFeedbackConstants.MESSAGE_WHEN_ID_FEEDBACK_IS_NULL);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenFeedBackIsNotFound() {
        when(feedBackGateway.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> findFeedBackByIdUseCase.execute(InformationsFeedbackConstants.DEFAULT_FEEDBACK_ID))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(InformationsFeedbackConstants.getMessageIdFeedbackNotFound(InformationsFeedbackConstants.DEFAULT_FEEDBACK_ID));

    }

    @Test
    void shouldReturnFeedback() {
        Long idRestaurante = 2L;
        var feedbackDomain = Optional.of(InformationsFeedbackConstants
                .buildFeedBackTest(InformationsFeedbackConstants.DEFAULT_FEEDBACK_ID,
                        idRestaurante,
                        Integer.valueOf(2)));

        when(feedBackGateway.findById(InformationsFeedbackConstants.DEFAULT_FEEDBACK_ID)).thenReturn(feedbackDomain);

        final var result = findFeedBackByIdUseCase.execute(InformationsFeedbackConstants.DEFAULT_FEEDBACK_ID);

        assertThat(result)
                .isNotNull()
                .isEqualTo(feedbackDomain.get());
    }


}
