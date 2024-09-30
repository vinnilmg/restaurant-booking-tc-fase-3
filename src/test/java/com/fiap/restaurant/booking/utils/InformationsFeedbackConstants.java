package com.fiap.restaurant.booking.utils;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.core.domains.FeedBackDomain;
import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.domains.enums.TipoCulinariaEnum;
import com.fiap.restaurant.booking.infrastructure.controllers.request.FeedBackRequest;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.FeedBackEntity;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.RestauranteEntity;
import com.fiap.restaurant.booking.utils.fixture.RestauranteDomainFixture;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class InformationsFeedbackConstants {
    public static final Long DEFAULT_FEEDBACK_ID = 1L;

    public static FeedBackDomain buildFeedBackTest(Long idFeedback, Integer avaliacao) {
        return new FeedBackDomain(idFeedback,
                RestauranteDomainFixture.NOVO(),
                "Cliente Teste",
                avaliacao,
                "Comentário aleatório",
                LocalDateTime.now()
        );
    }


    public static FeedBackEntity buildFeedBackTestEntity() {
        final var result = new FeedBackEntity();
        result.setId(null);
        result.setRestaurante(buildRestauranteEntityFromRestauranteDomain(RestauranteDomainFixture.NOVO()));
        result.setNomeCliente("teste");
        result.setAvaliacao(1);
        result.setComentario("teste");
        return result;
    }

    public static FeedBackEntity FEEDBACK_BY_DOMAIN_WITH_ID(final FeedBack feedBack) {
        final var result = new FeedBackEntity();
        result.setId(1L);
        result.setRestaurante(buildRestauranteEntityFromRestauranteDomain(feedBack.getRestaurante()));
        result.setNomeCliente(feedBack.getNomeCliente());
        result.setAvaliacao(feedBack.getAvaliacao());
        result.setComentario(feedBack.getComentario());
        result.setDataHoraCriacao(feedBack.getDataHoraCriacao());
        return result;
    }


    public static FeedBackRequest buildFeedbackRequest() {
        return new FeedBackRequest(1L, "Pablo Marçal", 1, "teste");
    }

    public static FeedBackRequest buildFeedbackRequestFromEntity(FeedBackEntity feedBackEntity) {
        return new FeedBackRequest(feedBackEntity.getRestaurante().getId(), feedBackEntity.getNomeCliente(), feedBackEntity.getAvaliacao(), feedBackEntity.getComentario());
    }


    public static RestauranteEntity buildRestauranteEntityFromRestauranteDomain(final Restaurante restaurante) {
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


    public static RestauranteEntity buildRestauranteEntityFull() {
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

    public static FeedBackEntity buildFeedBackEntityFull() {
        final var result = new FeedBackEntity();
        result.setId(1L);
        result.setRestaurante(buildRestauranteEntityFull());
        result.setNomeCliente("John Doe");
        result.setAvaliacao(5);
        result.setComentario("Excelente serviço!");
        result.setDataHoraCriacao(LocalDateTime.now());
        return result;
    }

}


