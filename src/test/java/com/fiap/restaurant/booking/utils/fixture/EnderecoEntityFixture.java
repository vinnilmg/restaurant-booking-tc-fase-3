package com.fiap.restaurant.booking.utils.fixture;

import com.fiap.restaurant.booking.infrastructure.persistence.entities.EnderecoEntity;

public class EnderecoEntityFixture {

    public static EnderecoEntity FULL() {
        final var entity = new EnderecoEntity();
        entity.setId(1L);
        entity.setRua("Rua numero 1");
        entity.setNumero("52");
        entity.setComplemento("ap 05");
        entity.setBairro("Vila Bela");
        entity.setCidade("Brusque");
        entity.setEstado("SP");
        entity.setCep("02998050");
        return entity;
    }
}
