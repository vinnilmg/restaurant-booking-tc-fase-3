package com.fiap.restaurant.booking.utils;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.core.domains.FeedBackDomain;
import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.domains.enums.TipoCulinariaEnum;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.FeedBackEntity;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.RestauranteEntity;

import java.time.LocalDateTime;
import java.time.LocalTime;

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
                InformationsRestauranteConstants.buildRestauranteTest(),
                "Cliente Teste",
                avaliacao,
                "Comentário aleatório",
                LocalDateTime.now()
        );
    }


    public static FeedBackEntity FEEDBACK_BY_DOMAIN_WITH_ID(final FeedBack feedBack) {
        final var result = new FeedBackEntity();
        result.setId(1L);
        result.setRestaurante(RESTAURANTE_BY_DOMAIN_WITH_ID(feedBack.getRestaurante()));
        result.setNomeCliente(feedBack.getNomeCliente());
        result.setAvaliacao(feedBack.getAvaliacao());
        result.setComentario(feedBack.getComentario());
        result.setDataHoraCriacao(feedBack.getDataHoraCriacao());
        return result;
    }

    public static RestauranteEntity RESTAURANTE_BY_DOMAIN_WITH_ID(final Restaurante restaurante) {
        final var result = new RestauranteEntity();
        result.setId(1L);
        result.setNome(restaurante.getNome());
        result.setCnpj(restaurante.getCnpj());
        result.setTipoCulinaria(restaurante.getTipoCulinaria());
        result.setInicioFuncionamento(restaurante.getInicioFuncionamento());
        result.setFimFuncionamento(restaurante.getFimFuncionamento());
        result.setCapacidade(restaurante.getCapacidade());
        result.setMediaFeedback(restaurante.getMediaFeedback());
        return result;
    }


    public static RestauranteEntity RESTAURANTE_FULL() {
        final var result = new RestauranteEntity();
        result.setId(1L);
        result.setNome("Restaurante Fictício");
        result.setCnpj("12345678901234");
        result.setTipoCulinaria(TipoCulinariaEnum.VEGETARIANA.name());
        result.setInicioFuncionamento(LocalTime.of(11, 0));
        result.setFimFuncionamento(LocalTime.of(23, 0));
        result.setCapacidade(100);
        result.setMediaFeedback(4.5);
        return result;
    }

    public static FeedBackEntity FEEDBACK_FULL() {
        final var result = new FeedBackEntity();
        result.setId(1L);
        result.setRestaurante(RESTAURANTE_FULL());
        result.setNomeCliente("John Doe");
        result.setAvaliacao(5);
        result.setComentario("Excelente serviço!");
        result.setDataHoraCriacao(LocalDateTime.now());
        return result;
    }

}


