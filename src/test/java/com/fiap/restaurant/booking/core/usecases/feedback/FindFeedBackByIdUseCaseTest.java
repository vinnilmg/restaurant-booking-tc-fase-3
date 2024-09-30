package com.fiap.restaurant.booking.core.usecases.feedback;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.impl.FindFeedBackByIdUseCaseImpl;
import com.fiap.restaurant.booking.utils.FeedBackValidationsMessages;
import com.fiap.restaurant.booking.utils.InformationsFeedbackConstants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.fiap.restaurant.booking.utils.InformationsFeedbackConstants.DEFAULT_FEEDBACK_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FindFeedBackByIdUseCaseTest {

    private FindFeedBackByIdUseCase findFeedBackByIdUseCase;

    @Mock
    private FeedBackGateway feedBackGateway;


    private AutoCloseable mock;

    @BeforeEach
    void init() {
        mock = MockitoAnnotations.openMocks(this);
        findFeedBackByIdUseCase = new FindFeedBackByIdUseCaseImpl(feedBackGateway);
    }

    @AfterEach
    void tearsDown() throws Exception {
        mock.close();
    }

    @Test
    void shouldThrowValidationExceptionWhenIdIsNull() {
        assertThatThrownBy(() -> findFeedBackByIdUseCase.execute(null))
                .isInstanceOf(ValidationException.class)
                .hasMessage(FeedBackValidationsMessages.MESSAGE_WHEN_ID_FEEDBACK_IS_NULL);

        verify(feedBackGateway, never()).findById(any());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenFeedBackIsNotFound() {
        when(feedBackGateway.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> findFeedBackByIdUseCase.execute(DEFAULT_FEEDBACK_ID))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(FeedBackValidationsMessages.getMessageIdFeedbackNotFound(DEFAULT_FEEDBACK_ID));

        verify(feedBackGateway, times(1)).findById(any());

    }

    @Test
    void shouldReturnFeedback() {
        Long idRestaurante = 2L;
        Optional<FeedBack> feedbackDomain = Optional.of(InformationsFeedbackConstants
                .buildFeedBackTest(DEFAULT_FEEDBACK_ID,
                        Integer.valueOf(2)));

        when(feedBackGateway.findById(DEFAULT_FEEDBACK_ID)).thenReturn(feedbackDomain);

        final var result = findFeedBackByIdUseCase.execute(DEFAULT_FEEDBACK_ID);

        assertThat(result)
                .isNotNull();

        assertThat(result.getId())
                .isEqualTo(feedbackDomain.get().getId());

        assertThat(result.getRestaurante().getId())
                .isEqualTo(feedbackDomain.get().getRestaurante().getId());

        assertThat(result.getComentario())
                .isEqualTo(feedbackDomain.get().getComentario());

        verify(feedBackGateway, times(1)).findById(DEFAULT_FEEDBACK_ID);
    }

}
