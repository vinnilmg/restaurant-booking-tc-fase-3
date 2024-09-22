package com.fiap.restaurant.booking.core.usecases.feedback;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.core.domains.FeedBackDomain;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.impl.GetAllFeedBackUseCaseImpl;
import com.fiap.restaurant.booking.utils.InformationsRestauranteConstants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
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
               FeedBackDomain
                       .builder()
                       .id(1L)
                       .avaliacao(Integer.valueOf(1))
                       .comentario("cadeirada do datena")
                       .dataHoraCriacao(LocalDateTime.now())
                       .restauranteId(InformationsRestauranteConstants.buildRestauranteTest(1L))
                        .nomeCliente("Pablo Marçal")
                       .build(),
                FeedBackDomain
                        .builder()
                        .id(2L)
                        .avaliacao(Integer.valueOf(1))
                        .comentario("Boulos Comunista")
                        .dataHoraCriacao(LocalDateTime.now())
                        .restauranteId(InformationsRestauranteConstants.buildRestauranteTest(2L))
                        .nomeCliente("Lula 13")
                        .build()
        );

        when(feedBackGateway.findAll()).thenReturn(feedBackDomainsList);

        final var result = getAllFeedBackUseCase.execute();

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(feedBackDomainsList.size());

        verify(feedBackGateway,times(1)).findAll();
    }
}
