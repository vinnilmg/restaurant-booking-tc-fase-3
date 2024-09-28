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
}
