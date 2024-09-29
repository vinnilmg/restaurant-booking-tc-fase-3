package com.fiap.restaurant.booking.utils.fixture;

import com.fiap.restaurant.booking.core.domains.Endereco;
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

    public static EnderecoEntity BY_DOMAIN(final Endereco endereco) {
        final var entity = new EnderecoEntity();
        entity.setId(endereco.getId());
        entity.setRua(endereco.getRua());
        entity.setNumero(endereco.getNumero());
        entity.setComplemento(endereco.getComplemento());
        entity.setBairro(endereco.getBairro());
        entity.setCidade(endereco.getCidade());
        entity.setEstado(endereco.getEstado());
        entity.setCep(endereco.getCep());
        return entity;
    }
}
