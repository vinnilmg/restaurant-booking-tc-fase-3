package com.fiap.restaurant.booking.core.domains;

import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.utils.InformationsRestauranteConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FeedbackDomainClassTest {

    @Test
    void criarFeedbackComAvaliacãoNulo() {
        Assertions.assertThrows(ValidationException.class, () -> {new FeedBackDomain(
                null,
                InformationsRestauranteConstants.buildRestauranteTest(2L),
                "teste",
                null,
                "teste");
        });
    }
    @Test
    void criarFeedbackComRestauranteIdNulo() {

        Assertions.assertThrows(ValidationException.class, () -> {new FeedBackDomain(
                Long.parseLong("1"),
                InformationsRestauranteConstants.buildRestauranteTest(null),
                "teste",
                1,
                "teste");
        });
    }

    @Test
    void criarFeedbackComNomeClienteNulo() {

        Assertions.assertThrows(ValidationException.class, () -> {new FeedBackDomain(
                Long.parseLong("1"),
                InformationsRestauranteConstants.buildRestauranteTest(2L),
                null,
                1,
                "teste");
        });
    }

    @Test
    void criarFeedbackComAvaliacaoMenorQueUm() {

        Assertions.assertThrows(ValidationException.class, () -> {new FeedBackDomain(
                Long.parseLong("1"),
                InformationsRestauranteConstants.buildRestauranteTest(2L),
                "teste",
                0,
                "teste");
        });
    }

    @Test
    void criarFeedbackComAvaliacaoMaiorQueCinco() {

        Assertions.assertThrows(ValidationException.class, () -> {new FeedBackDomain(
                Long.parseLong("1"),
                InformationsRestauranteConstants.buildRestauranteTest(2L),
                "teste",
                6,
                "teste");
        });
    }
    @Test
    void criarInstanciaDeFeedBackComNomeClienteVazio() {
        Long id = 1L;
        Long restauranteID = 2L;
        String nomeCliente = "";
        Integer avaliacao = 4;
        String comentario = "Ótimo atendimento!";

        Assertions.assertThrows(ValidationException.class, () -> {
            new FeedBackDomain(id, InformationsRestauranteConstants.buildRestauranteTest(2L), nomeCliente, avaliacao, comentario);
        });

    }


    @Test
    void criarInstanciaDeFeedBackComSucesso() {
        Long id = 1L;
        Long restauranteID = 2L;
        String nomeCliente = "João";
        Integer avaliacao = 4;
        String comentario = "Ótimo atendimento!";

        FeedBackDomain feedback = new FeedBackDomain(id, InformationsRestauranteConstants.buildRestauranteTest(2L), nomeCliente, avaliacao, comentario);

        assertNotNull(feedback);
        assertEquals(id, feedback.getId());
        assertEquals(restauranteID, feedback.getRestaurante().getId());
        assertEquals(nomeCliente, feedback.getNomeCliente());
        assertEquals(avaliacao, feedback.getAvaliacao());
        assertEquals(comentario, feedback.getComentario());
    }

}
