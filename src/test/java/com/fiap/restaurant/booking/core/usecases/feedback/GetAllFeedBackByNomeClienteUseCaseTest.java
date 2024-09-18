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

import static com.fiap.restaurant.booking.utils.InformationsFeedbackConstants.NOME_CLIENTE_EXAMPLE_TESTE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetAllFeedBackByNomeClienteUseCaseTest {

    private GetAllFeedBackByNomeClienteUseCase getAllFeedBackByNomeClienteUseCase;
    private FeedBackGateway feedBackGateway;

    @BeforeEach
    void init() {
        feedBackGateway = mock(FeedBackGateway.class);
        getAllFeedBackByNomeClienteUseCase = new GetAllFeedBackByNomeClienteUseCaseImpl(feedBackGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenNomeClienteIsNull() {
        assertThatThrownBy(() -> getAllFeedBackByNomeClienteUseCase.execute(""))
                .isInstanceOf(ValidationException.class)
                .hasMessage(InformationsFeedbackConstants.MESSAGE_WHEN_NOME_CLIENTE_IS_EMPTY);

    }

    @Test
    void shouldThrowNotFoundWhenNomeClienteNotFoundByListFeedback() {
        String nomeTeste = "teste";
        when(feedBackGateway.findAllByNomeCliente(nomeTeste)).thenReturn(Collections.emptyList());
        assertThatThrownBy(() -> getAllFeedBackByNomeClienteUseCase.execute(nomeTeste))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(InformationsFeedbackConstants.getMessageWhenNomeClienteFeedbackNotFound(nomeTeste));

    }
    @Test
    void shouldReturnFeedBackWhenFindByNomeCliente() {
        var listExampleReturn = List.of(InformationsFeedbackConstants.buildFeedBackTest(1L,1L,Integer.valueOf(1)));

        when(feedBackGateway.findAllByNomeCliente(NOME_CLIENTE_EXAMPLE_TESTE)).thenReturn(listExampleReturn);

        var result = getAllFeedBackByNomeClienteUseCase.execute(NOME_CLIENTE_EXAMPLE_TESTE);

        assertThat(result)
                .isNotNull()
                .isEqualTo(listExampleReturn);


    }

}
