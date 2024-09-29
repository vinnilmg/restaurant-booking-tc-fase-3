package com.fiap.restaurant.booking.utils;

public class FeedBackValidationsMessages {
    public static final String MESSAGE_ID_RESTAURANTE_NOT_FOUND = "Feedback with id restaurante %s not found";
    public static final String MESSAGE_ID_FEEDBACK_NOT_FOUND = "Feedback with id %s not found";
    public static final String MESSAGE_WHEN_ID_RESTAURANTE_IS_NULL = "id restaurante cannot be null";
    public static final String MESSAGE_WHEN_ID_FEEDBACK_IS_NULL = "id cannot be null";
    public static final String MESSAGE_WHEN_NOME_CLIENTE_IS_EMPTY = "nomeCliente cannot be empty";
    public static final String MESSAGE_WHEN_NOME_CLIENTE_FEEDBACK_NOT_FOUND = "feedbacks by nome cliente %s not found";
    public static final String MESSAGE_WHEN_DELETE_A_FEEDBACK = "Feedback by id %s was deleted by success.";
    public static final String NOME_CLIENTE_EXAMPLE_TESTE = "teste";

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
}