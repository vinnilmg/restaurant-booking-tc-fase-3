package com.fiap.restaurant.booking.feedbackTest;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FeedbackDomainClassTest {

    @Test
    void criarFeedbackComAvaliacãoNulo() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {new FeedBack(
                null,
                Long.parseLong("1"),
                "teste",
                null,
                "teste");
        });
    }
    @Test
    void criarFeedbackComRestauranteIdNulo() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {new FeedBack(
                Long.parseLong("1"),
                null,
                "teste",
                1,
                "teste");
        });
    }

    @Test
    void criarFeedbackComNomeClienteNulo() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {new FeedBack(
                Long.parseLong("1"),
                Long.parseLong("1"),
                null,
                1,
                "teste");
        });
    }

    @Test
    void criarFeedbackComAvaliacaoMenorQueUm() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {new FeedBack(
                Long.parseLong("1"),
                Long.parseLong("1"),
                "teste",
                0,
                "teste");
        });
    }

    @Test
    void criarFeedbackComAvaliacaoMaiorQueCinco() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {new FeedBack(
                Long.parseLong("1"),
                Long.parseLong("1"),
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

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new FeedBack(id, restauranteID, nomeCliente, avaliacao, comentario);
        });

    }


    @Test
    void criarInstanciaDeFeedBackComSucesso() {
        Long id = 1L;
        Long restauranteID = 2L;
        String nomeCliente = "João";
        Integer avaliacao = 4;
        String comentario = "Ótimo atendimento!";

        FeedBack feedback = new FeedBack(id, restauranteID, nomeCliente, avaliacao, comentario);

        assertNotNull(feedback);
        assertEquals(id, feedback.getId());
        assertEquals(restauranteID, feedback.getRestauranteId());
        assertEquals(nomeCliente, feedback.getNomeCliente());
        assertEquals(avaliacao, feedback.getAvaliacao());
        assertEquals(comentario, feedback.getComentario());
    }

}
