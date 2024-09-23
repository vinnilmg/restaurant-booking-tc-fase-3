package com.fiap.restaurant.booking.utils;

public class DefaultParamsConstants {
    private DefaultParamsConstants() {
    }

    public static final String DEFAULT_CPF = "83533067009";
    public static final Long DEFAULT_RESERVA_ID = 1L;
    public static final String DEFAULT_NOME = "Name";
    public static final String DEFAULT_CNPJ = "41.521.747/0001-72";
    public static final String DEFAULT_TIPO_CULINARIA = "BRASILEIRA";

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
