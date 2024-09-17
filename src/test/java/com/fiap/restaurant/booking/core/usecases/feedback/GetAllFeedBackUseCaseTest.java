package com.fiap.restaurant.booking.core.usecases.feedback;

import com.fiap.restaurant.booking.core.domains.FeedBackDomain;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.impl.GetAllFeedBackUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetAllFeedBackUseCaseTest {

    private GetAllFeedBackUseCaseImpl getAllFeedBackUseCase;
    private FeedBackGateway feedBackGateway;

    @BeforeEach
    void init() {
        feedBackGateway = mock(FeedBackGateway.class);

        getAllFeedBackUseCase = new GetAllFeedBackUseCaseImpl(feedBackGateway);
    }


    @Test
    void shouldGetAllFeedbacks() {
        final List<FeedBackDomain> feedBackDomainsList = List.of(
               FeedBackDomain
                       .builder()
                       .id(1L)
                       .avaliacao(Integer.valueOf(1))
                       .comentario("cadeirada do datena")
                       .dataHoraCriacao(LocalDateTime.now())
                       .restauranteId(2L)
                        .nomeCliente("Pablo Marçal")
                       .build(),
                FeedBackDomain
                        .builder()
                        .id(2L)
                        .avaliacao(Integer.valueOf(1))
                        .comentario("Boulos Comunista")
                        .dataHoraCriacao(LocalDateTime.now())
                        .restauranteId(2L)
                        .nomeCliente("Lula 13")
                        .build()
        );

        when(feedBackGateway.findAll()).thenReturn(feedBackDomainsList);

        final var result = getAllFeedBackUseCase.execute();

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(feedBackDomainsList.size());

        verify(feedBackGateway).findAll();
    }
}
