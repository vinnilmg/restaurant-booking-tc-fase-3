package com.fiap.restaurant.booking.core.domains;

import java.time.LocalTime;

public interface Restaurante {
    Long getId();

    String getNome();

    String getCnpj();

    Endereco getEndereco();

    String getTipoCulinaria();

    LocalTime getInicioFuncionamento();

    LocalTime getFimFuncionamento();

    Integer getCapacidade();

    Double getMediaFeedback();

}
