package com.fiap.restaurant.booking.utils.fixture;

import com.fiap.restaurant.booking.infrastructure.controllers.request.RestauranteRequest;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.*;
public class RestauranteRequestFixture {

    public static RestauranteRequest FULL() {
        return new RestauranteRequest(
                DEFAULT_NOME,
                DEFAULT_CNPJ,
                DEFAULT_ENDERECO_REQUEST,
                DEFAULT_TIPO_CULINARIA,
                DEFAULT_INICIO_FUNCIONAMENTO,
                DEFAULT_FIM_FUNCIONAMENTO,
                DEFAULT_CAPACIDADE,
                DEFAULT_MEDIA_FEEDBACK
        );
    }
}
