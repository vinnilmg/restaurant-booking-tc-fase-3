package com.fiap.restaurant.booking.core.domains;

import com.fiap.restaurant.booking.core.domains.enums.TipoCulinariaEnum;

import java.time.LocalDateTime;

public interface Restaurante {
    Long getId();

    String getNome();

    String getCnpj();

    String getTipoCulinaria();

    LocalDateTime getInicioFuncionamento();

    LocalDateTime getFimFuncionamento();

    Integer getCapacidade();

    Double getMediaFeedback();

    void updateMedia(Double mediaFeedback);
}
