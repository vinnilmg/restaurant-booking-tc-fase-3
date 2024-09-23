package com.fiap.restaurant.booking.core.usecases.feedback;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.impl.GetAllFeedBackUseCaseImpl;
import com.fiap.restaurant.booking.utils.InformationsFeedbackConstants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetAllFeedBackUseCaseTest {

    private GetAllFeedBackUseCaseImpl getAllFeedBackUseCase;

    @Mock
    private FeedBackGateway feedBackGateway;

    private AutoCloseable mock;

    @BeforeEach
    void init() {
        mock = MockitoAnnotations.openMocks(this);
        getAllFeedBackUseCase = new GetAllFeedBackUseCaseImpl(feedBackGateway);
    }

    @AfterEach
    void tearsDown() throws Exception {
        mock.close();
    }

    @Test
    void shouldGetAllFeedbacks() {
        final List<FeedBack> feedBackDomainsList = List.of(
                InformationsFeedbackConstants.buildFeedBackTest(1L, 2L, 1),
                InformationsFeedbackConstants.buildFeedBackTest(2L, 2L, 1)
        );

        when(feedBackGateway.findAll()).thenReturn(feedBackDomainsList);

        final var result = getAllFeedBackUseCase.execute();

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(feedBackDomainsList.size());

        verify(feedBackGateway, times(1)).findAll();
    }
}
