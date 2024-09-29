package com.fiap.restaurant.booking.utils.fixture;

import com.fiap.restaurant.booking.core.domains.EnderecoDomain;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.EnderecoEntity;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_ENDERECO_ID;

public class EnderecoDomainFixture {

    public static EnderecoDomain NOVO() {
        return new EnderecoDomain(
                DEFAULT_ENDERECO_ID,
                "Rua Teste",
                "120",
                "ap 22",
                "Centro",
                "São Paulo",
                "SP",
                "01512000"
        );
    }

    public static EnderecoDomain BY_ENTITY(final EnderecoEntity entity) {
        return new EnderecoDomain(
                entity.getId(),
                entity.getRua(),
                entity.getNumero(),
                entity.getComplemento(),
                entity.getBairro(),
                entity.getCidade(),
                entity.getEstado(),
                entity.getCep()
        );
    }

    public static EnderecoDomain OTHER() {
        return new EnderecoDomain(
                "Rua",
                "10",
                null,
                "Tijuca",
                "Rio de Janeiro",
                "RJ",
                " 20520090");
    }
}
