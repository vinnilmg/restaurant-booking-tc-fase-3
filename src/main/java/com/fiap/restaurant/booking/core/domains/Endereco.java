package com.fiap.restaurant.booking.core.domains;

import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;

public interface Endereco {
    Long getId();

    String getRua();

    String getBairro();

    String getNumero();

    String getComplemento();

    String getCidade();

    String getEstado();

    String getCep();

    void updateEndereco(Endereco endereco);
}
