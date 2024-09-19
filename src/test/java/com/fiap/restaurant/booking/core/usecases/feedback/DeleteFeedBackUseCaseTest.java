package com.fiap.restaurant.booking.core.usecases.feedback;

import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.impl.DeleteFeedBackUseCaseImpl;
import com.fiap.restaurant.booking.utils.InformationsFeedbackConstants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.fiap.restaurant.booking.utils.InformationsFeedbackConstants.getMessageWhenDeleteAFeedback;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DeleteFeedBackUseCaseTest {

    private DeleteFeedBackUseCase deleteFeedBackUseCase;

    @Mock
    private FindFeedBackByIdUseCase findFeedBackByIdUseCase;

    @Mock
    private FeedBackGateway feedBackGateway;

    private AutoCloseable mock;

    @BeforeEach
    void init() {
        mock = MockitoAnnotations.openMocks(this);
        deleteFeedBackUseCase = new DeleteFeedBackUseCaseImpl(feedBackGateway, findFeedBackByIdUseCase);
    }

    @AfterEach
    void tearsDown() throws Exception {
        mock.close();
    }

    @Test
    void shouldBeDeleteFeedBack() {
        var feedbackDomain = Optional.of(InformationsFeedbackConstants
                .buildFeedBackTest(InformationsFeedbackConstants.DEFAULT_FEEDBACK_ID, 2L, Integer.valueOf(1))
        );

        when(feedBackGateway.findById(any())).thenReturn(feedbackDomain);

        assertThat(deleteFeedBackUseCase.execute(feedbackDomain.get().getId()).getMessage())
                .isEqualTo(getMessageWhenDeleteAFeedback(feedbackDomain.get().getId()));

        verify(feedBackGateway, times(1)).delete(any());
    }


}
