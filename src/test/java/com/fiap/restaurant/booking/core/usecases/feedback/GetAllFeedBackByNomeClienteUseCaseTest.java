package com.fiap.restaurant.booking.core.usecases.feedback;

import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.impl.GetAllFeedBackByNomeClienteUseCaseImpl;
import com.fiap.restaurant.booking.utils.InformationsFeedbackConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static com.fiap.restaurant.booking.utils.InformationsFeedbackConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class GetAllFeedBackByNomeClienteUseCaseTest {

    private GetAllFeedBackByNomeClienteUseCase getAllFeedBackByNomeClienteUseCase;
    private FeedBackGateway feedBackGateway;

    @BeforeEach
    void init() {
        feedBackGateway = mock(FeedBackGateway.class);
        getAllFeedBackByNomeClienteUseCase = new GetAllFeedBackByNomeClienteUseCaseImpl(feedBackGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenNomeClienteIsEmpty() {
        assertThatThrownBy(() -> getAllFeedBackByNomeClienteUseCase.execute(""))
                .isInstanceOf(ValidationException.class)
                .hasMessage(MESSAGE_WHEN_NOME_CLIENTE_IS_EMPTY);

        verify(feedBackGateway,never()).findAllByNomeCliente(anyString());
    }

    @Test
    void shouldThrowNotFoundWhenNomeClienteNotFoundByListFeedback() {
        when(feedBackGateway.findAllByNomeCliente(NOME_CLIENTE_EXAMPLE_TESTE)).thenReturn(Collections.emptyList());

        assertThatThrownBy(() -> getAllFeedBackByNomeClienteUseCase.execute(NOME_CLIENTE_EXAMPLE_TESTE))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(getMessageWhenNomeClienteFeedbackNotFound(NOME_CLIENTE_EXAMPLE_TESTE));

        verify(feedBackGateway).findAllByNomeCliente(NOME_CLIENTE_EXAMPLE_TESTE);
    }

    @Test
    void shouldReturnFeedBackWhenFindByNomeCliente() {
        var feedback = InformationsFeedbackConstants.buildFeedBackTest(
                DEFAULT_FEEDBACK_ID,
                DEFAULT_RESTAURANTE_ID,
                Integer.valueOf(1)
        );
        var listExampleReturn = List.of(feedback);

        when(feedBackGateway.findAllByNomeCliente(NOME_CLIENTE_EXAMPLE_TESTE)).thenReturn(listExampleReturn);

        var result = getAllFeedBackByNomeClienteUseCase.execute(NOME_CLIENTE_EXAMPLE_TESTE);

        assertThat(result)
                .isNotNull()
                .hasSize(1)
                .first()
                .satisfies(feedbackReturned -> {
                    assertThat(feedbackReturned).isEqualTo(feedback);
                });

        verify(feedBackGateway).findAllByNomeCliente(NOME_CLIENTE_EXAMPLE_TESTE);
    }
}
