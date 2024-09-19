package com.fiap.restaurant.booking.utils;

public class DefaultParamsConstants {
    private DefaultParamsConstants() {
    }

    public static final String DEFAULT_CPF = "83533067009";
    public static final Long DEFAULT_RESERVA_ID = 1L;

    public static final String JSON_PATH_ID = "$.id";
    public static final String JSON_PATH_CPF = "$.cpfCliente";
    public static final String JSON_PATH_STATUS = "$.status";
    public static final String JSON_PATH_DATA_HORA_RESERVA = "$.dataHoraReserva";
    public static final String JSON_PATH_DATA_HORA_CRIACAO = "$.dataHoraCriacao";
}
