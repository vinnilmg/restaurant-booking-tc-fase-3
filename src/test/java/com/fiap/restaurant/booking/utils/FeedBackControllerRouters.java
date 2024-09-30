package com.fiap.restaurant.booking.utils;

public class FeedBackControllerRouters {
    public static final String ROUTE_CONTROLLER_DEFAULT = "/api/feedbacks";

    public static final String ROUTE_CONTROLLER_FIND_BY_ID = ROUTE_CONTROLLER_DEFAULT.concat("/{id}");

    public static final String ROUTE_CONTROLLER_DELETE_BY_ID = ROUTE_CONTROLLER_DEFAULT.concat("/{id}");

    public static final String ROUTE_CONTROLLER_FIND_BY_RESTAURANTE_ID = ROUTE_CONTROLLER_DEFAULT.concat("/restaurante/{idRestaurante}");

    public static final String ROUTE_CONTROLLER_FIND_BY_NOME_CLIENTE = ROUTE_CONTROLLER_DEFAULT.concat("/nome-cliente/{nomeCliente}");
}