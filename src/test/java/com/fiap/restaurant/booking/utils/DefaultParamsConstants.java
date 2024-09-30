package com.fiap.restaurant.booking.utils;

import com.fiap.restaurant.booking.core.domains.EnderecoDomain;
import com.fiap.restaurant.booking.infrastructure.controllers.request.EnderecoRequest;


public class DefaultParamsConstants {
    private DefaultParamsConstants() {
    }

    public static final String DEFAULT_CPF = "83533067009";
    public static final Long DEFAULT_RESERVA_ID = 1L;
    public static final Long DEFAULT_ENDERECO_ID = 1L;
    public static final String DEFAULT_ENDERECO_RUA = "Rua Teste";
    public static final String DEFAULT_ENDERECO_CIDADE = "São Paulo";
    public static final String DEFAULT_ENDERECO_BAIRRO = "Centro";
    public static final String DEFAULT_ENDERECO_CEP = "01512000";
    public static final String DEFAULT_NOME = "Name";
    public static final String DEFAULT_CNPJ = "41521747000172";
    public static final String DEFAULT_TIPO_CULINARIA = "BRASILEIRA";
    public static final EnderecoDomain DEFAULT_ENDERECO_DOMAIN =
            new EnderecoDomain(
                    "Rua",
                    "10",
                    null,
                    "Tijuca",
                    "Rio de Janeiro",
                    "RJ",
                    " 20520090");

    public static final EnderecoRequest DEFAULT_ENDERECO_REQUEST =
            new EnderecoRequest(
                    "Rua Cristiano Olsen",
                    "22",
                    null,
                    "Jardim Sumaré",
                    "Araçatuba",
                    "SP",
                    "16015244"
            );

    public static final String DEFAULT_INICIO_FUNCIONAMENTO = "11:00";
    public static final String DEFAULT_FIM_FUNCIONAMENTO = "21:00";
    public static final Integer DEFAULT_CAPACIDADE = 100;
    public static final Double DEFAULT_MEDIA_FEEDBACK = 5.0;

    public static final String JSON_PATH_ID = "$.id";
    public static final String JSON_PATH_CPF = "$.cpfCliente";
    public static final String JSON_PATH_STATUS = "$.status";
    public static final String JSON_PATH_DATA_HORA_RESERVA = "$.dataHoraReserva";
    public static final String JSON_PATH_DATA_HORA_CRIACAO = "$.dataHoraCriacao";
    public static final String JSON_PATH_LIST_ID = "$[0].id";
    public static final String JSON_PATH_LIST_CPF = "$[0].cpfCliente";
    public static final String JSON_PATH_LIST_STATUS = "$[0].status";
    public static final String JSON_PATH_LIST_DATA_HORA_RESERVA = "$[0].dataHoraReserva";
    public static final String JSON_PATH_LIST_DATA_HORA_CRIACAO = "$[0].dataHoraCriacao";
}
