package com.fiap.restaurant.booking.core.usecases.feedback;

import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.impl.CreateFeedBackUseCaseImpl;
import com.fiap.restaurant.booking.utils.InformationsFeedbackConstants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class CreateFeedBackUseCaseTest {
    private CreateFeedBackUseCase createFeedBackUseCase;

    @Mock
    private FeedBackGateway feedBackGateway;

    private AutoCloseable mock;

    @BeforeEach
    void init() {
        mock = MockitoAnnotations.openMocks(this);
        createFeedBackUseCase = new CreateFeedBackUseCaseImpl(feedBackGateway);
    }

    @AfterEach
    void tearsDown() throws Exception {
        mock.close();
    }

    @Test
    void shouldBeCreateFeedBack() {
        var feedBackToReturned = InformationsFeedbackConstants.buildFeedBackTest(1L, 1L, 1);
        feedBackToReturned.setDataHoraCriacao(null);

        var feedbackToCreate = InformationsFeedbackConstants.buildFeedBackTest(null, 1L, 1);

        when(feedBackGateway.create(any())).thenReturn(feedBackToReturned);

        var feedBackDomainFromDatabase = createFeedBackUseCase.execute(feedbackToCreate);

        assertThat(feedBackDomainFromDatabase.getNomeCliente()).isEqualTo(feedBackToReturned.getNomeCliente());
        assertThat(feedBackDomainFromDatabase.getAvaliacao()).isEqualTo(feedBackToReturned.getAvaliacao());
    }

    @Test
    void shouldBeThrowValidationExceptionWhenCreateFeedBackWithId() {

        var feedbackToCreate = InformationsFeedbackConstants.buildFeedBackTest(1L, 1L, 1);

        assertThatThrownBy(() -> createFeedBackUseCase.execute(feedbackToCreate))
                .isInstanceOf(ValidationException.class)
                .hasMessage(InformationsFeedbackConstants.MESSAGE_ID_FEEDBACK_NOT_NULL_WHEN_CREATE);
    }
}