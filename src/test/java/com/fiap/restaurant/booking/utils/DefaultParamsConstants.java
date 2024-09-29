package com.fiap.restaurant.booking.utils;

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
    public static final String DEFAULT_TIME = "08:00";
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
