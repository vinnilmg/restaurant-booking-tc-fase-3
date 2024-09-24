package com.fiap.restaurant.booking.core.usecases.feedback;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.impl.FindFeedBackByIdRestauranteUseCaseImpl;
import com.fiap.restaurant.booking.utils.InformationsFeedbackConstants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class FindFeedBackByIdRestauranteUseCaseTest {

    private FindFeedBackByIdRestauranteUseCase findFeedBackByIdRestauranteUseCase;

    @Mock
    private FeedBackGateway feedBackGateway;

    private AutoCloseable mock;

    @BeforeEach
    void init() {
        mock = MockitoAnnotations.openMocks(this);
        findFeedBackByIdRestauranteUseCase = new FindFeedBackByIdRestauranteUseCaseImpl(feedBackGateway);
    }

    @AfterEach
    void tearsDown() throws Exception {
        mock.close();
    }

    @Test
    void shouldThrowNotFoundExceptionWhenFeedBackIsNotFound() {
        assertThatThrownBy(() ->
                findFeedBackByIdRestauranteUseCase.execute(InformationsFeedbackConstants.DEFAULT_FEEDBACK_ID))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(InformationsFeedbackConstants.getMessageIdRestauranteNotFound(InformationsFeedbackConstants.DEFAULT_FEEDBACK_ID)
                        , InformationsFeedbackConstants.DEFAULT_FEEDBACK_ID);

        verify(feedBackGateway, times(1)).findByIdRestaurante(any());
    }

    @Test
    void shouldThrowValidationExceptionWhenIdIsNull() {
        assertThatThrownBy(() -> findFeedBackByIdRestauranteUseCase.execute(null))
                .isInstanceOf(ValidationException.class)
                .hasMessage(InformationsFeedbackConstants.MESSAGE_WHEN_ID_RESTAURANTE_IS_NULL);

        verify(feedBackGateway, never()).findByIdRestaurante(any());
    }

    @Test
    void shouldReturnFeedbackByIdRestaurante() {
        Long idRestaurante = 2L;

        Optional<FeedBack> feedbackDomain = Optional.of(InformationsFeedbackConstants
                .buildFeedBackTest(InformationsFeedbackConstants.DEFAULT_FEEDBACK_ID, idRestaurante, Integer.valueOf(1))
        );

        when(feedBackGateway.findByIdRestaurante(idRestaurante)).thenReturn(feedbackDomain);

        final var result = findFeedBackByIdRestauranteUseCase.execute(idRestaurante);

        assertThat(result)
                .isNotNull()
                .isEqualTo(feedbackDomain.get());

        verify(feedBackGateway, times(1)).findByIdRestaurante(any());
    }

}
