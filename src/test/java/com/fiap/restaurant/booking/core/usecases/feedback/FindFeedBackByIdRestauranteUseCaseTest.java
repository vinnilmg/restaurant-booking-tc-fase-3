package com.fiap.restaurant.booking.core.usecases.feedback;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.impl.FindFeedBackByIdRestauranteUseCaseImpl;
import com.fiap.restaurant.booking.utils.FeedBackValidationsMessages;
import com.fiap.restaurant.booking.utils.InformationsFeedbackConstants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.fiap.restaurant.booking.utils.InformationsFeedbackConstants.DEFAULT_FEEDBACK_ID;
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
                .hasMessage(FeedBackValidationsMessages.getMessageIdRestauranteNotFound(InformationsFeedbackConstants.DEFAULT_FEEDBACK_ID)
                        , InformationsFeedbackConstants.DEFAULT_FEEDBACK_ID);

        verify(feedBackGateway, times(1)).findByIdRestaurante(any());
    }

    @Test
    void shouldThrowValidationExceptionWhenIdIsNull() {
        assertThatThrownBy(() -> findFeedBackByIdRestauranteUseCase.execute(null))
                .isInstanceOf(ValidationException.class)
                .hasMessage(FeedBackValidationsMessages.MESSAGE_WHEN_ID_RESTAURANTE_IS_NULL);

        verify(feedBackGateway, never()).findByIdRestaurante(any());
    }

    @Test
    void shouldReturnFeedbackByIdRestaurante() {

        List<FeedBack> feedbackList = List.of(InformationsFeedbackConstants.buildFeedBackTest(
                DEFAULT_FEEDBACK_ID,
                Integer.valueOf(1)
        ));

        Long idRestaurante = feedbackList.get(0).getId();

        when(feedBackGateway.findByIdRestaurante(idRestaurante)).thenReturn(feedbackList);

        final var result = findFeedBackByIdRestauranteUseCase.execute(idRestaurante);

        assertThat(result)
                .isNotNull()
                .isEqualTo(feedbackList);

        verify(feedBackGateway, times(1)).findByIdRestaurante(any());
    }

}
