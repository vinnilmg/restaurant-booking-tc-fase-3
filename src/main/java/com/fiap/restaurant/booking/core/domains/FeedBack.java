package com.fiap.restaurant.booking.core.domains;

import java.time.LocalDateTime;


public interface FeedBack {

    Long getId();

    Restaurante getRestaurante();

    String getNomeCliente();

    Integer getAvaliacao();

    String getComentario();

    LocalDateTime getDataHoraCriacao();

}
