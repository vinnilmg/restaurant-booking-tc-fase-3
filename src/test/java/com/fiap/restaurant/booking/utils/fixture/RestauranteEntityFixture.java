package com.fiap.restaurant.booking.utils.fixture;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.RestauranteEntity;

import java.time.LocalTime;

public class RestauranteEntityFixture {

    public static RestauranteEntity FULL() {
        final var entity = new RestauranteEntity();
        entity.setId(1L);
        entity.setNome("Padaria do seu Zé");
        entity.setCnpj("20094036000199");
        entity.setEndereco(EnderecoEntityFixture.FULL());
        entity.setTipoCulinaria("BRASILEIRA");
        entity.setCapacidade(40);
        entity.setMediaFeedback(5.0);
        entity.setInicioFuncionamento(LocalTime.of(8, 0));
        entity.setFimFuncionamento(LocalTime.of(17, 0));
        return entity;
    }

    public static RestauranteEntity BY_DOMAIN(final Restaurante restaurante) {
        final var entity = new RestauranteEntity();
        entity.setId(restaurante.getId());
        entity.setNome(restaurante.getNome());
        entity.setCnpj(restaurante.getCnpj());
        entity.setEndereco(EnderecoEntityFixture.BY_DOMAIN(restaurante.getEndereco()));
        entity.setTipoCulinaria(restaurante.getTipoCulinaria());
        entity.setCapacidade(restaurante.getCapacidade());
        entity.setMediaFeedback(restaurante.getMediaFeedback());
        entity.setInicioFuncionamento(restaurante.getInicioFuncionamento());
        entity.setFimFuncionamento(restaurante.getFimFuncionamento());
        return entity;
    }
}
