package com.fiap.restaurant.booking.utils.fixture;

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
}
