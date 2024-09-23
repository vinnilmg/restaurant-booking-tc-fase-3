package com.fiap.restaurant.booking.utils;

import com.fiap.restaurant.booking.core.domains.FeedBackDomain;

import java.time.LocalDateTime;

public class InformationsFeedbackConstants {

    private static final String MESSAGE_ID_RESTAURANTE_NOT_FOUND = "Feedback with id restaurante %s not found";

    private static final String MESSAGE_ID_FEEDBACK_NOT_FOUND = "Feedback with id %s not found";

    public static final String MESSAGE_ID_FEEDBACK_NOT_NULL_WHEN_CREATE = "id has to be null to create a new feedback";

    public static final String MESSAGE_WHEN_ID_RESTAURANTE_IS_NULL = "id restaurante cannot be null";

    public static final String MESSAGE_WHEN_ID_FEEDBACK_IS_NULL = "id cannot be null";

    public static final String MESSAGE_WHEN_NOME_CLIENTE_IS_EMPTY = "nomeCliente cannot be empty";

    public static final String MESSAGE_WHEN_NOME_CLIENTE_FEEDBACK_NOT_FOUND = "feedbacks by nome cliente %s not found";

    public static final String MESSAGE_WHEN_DELETE_A_FEEDBACK = "Feedback by id %s was deleted by success.";

    public static final String NOME_CLIENTE_EXAMPLE_TESTE = "teste";

    public static final Long DEFAULT_FEEDBACK_ID = 1L;

    public static final String ROUTE_CONTROLLER_DEFAULT = "/api/feedbacks";

    public static final String ROUTE_CONTROLLER_FIND_BY_ID = ROUTE_CONTROLLER_DEFAULT.concat("/{id}");

    public static final String ROUTE_CONTROLLER_DELETE_BY_ID = ROUTE_CONTROLLER_DEFAULT.concat("/{id}");

    public static final String ROUTE_CONTROLLER_FIND_BY_RESTAURANTE_ID = ROUTE_CONTROLLER_DEFAULT.concat("/restaurante/{idRestaurante}");

    public static final String ROUTE_CONTROLLER_FIND_BY_NOME_CLIENTE = ROUTE_CONTROLLER_DEFAULT.concat("/nome-cliente/{nomeCliente}");


    public static final Long DEFAULT_RESTAURANTE_ID = 1L;

    public static String getMessageIdRestauranteNotFound(Long idRestaurante) {
        return String.format(MESSAGE_ID_RESTAURANTE_NOT_FOUND, idRestaurante);
    }

    public static String getMessageIdFeedbackNotFound(Long idFeedback) {
        return String.format(MESSAGE_ID_FEEDBACK_NOT_FOUND, idFeedback);
    }

    public static String getMessageWhenDeleteAFeedback(Long idFeedback) {
        return String.format(MESSAGE_WHEN_DELETE_A_FEEDBACK, idFeedback);
    }

    public static String getMessageWhenNomeClienteFeedbackNotFound(String nomeCliente) {
        return String.format(MESSAGE_WHEN_NOME_CLIENTE_FEEDBACK_NOT_FOUND, nomeCliente);
    }

    public static FeedBackDomain buildFeedBackTest(Long idFeedback, Long idRestaurante, Integer avaliacao) {
        return new FeedBackDomain(idFeedback,
                InformationsRestauranteConstants.buildRestauranteTest(idRestaurante),
                "Cliente Teste",
                avaliacao,
                "Comentário aleatório",
                LocalDateTime.now()
        );
    }
}


