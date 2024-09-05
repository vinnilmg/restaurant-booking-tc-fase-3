package com.fiap.restaurant.booking.core.domains;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

public class ReservaImpl implements Reserva {
    private Long id;
    private String cpf;
    private String status;
    private LocalDateTime dataHoraCriacao;

    public ReservaImpl(Long id, String cpf, String status, LocalDateTime dataHoraCriacao) {
        requireNonNull(id, "Reserva Id cannot be null");
        if (id < 0) throw new IllegalArgumentException("Reserva Id cannot be negative");

        requireNonNull(cpf, "Customer Cpf cannot be null");
        if (cpf.length() < 11) throw new IllegalArgumentException("Customer Cpf must be 11 positions");

        this.id = id;
        this.cpf = cpf;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String cpf() {
        return cpf;
    }

    @Override
    public String status() {
        return status;
    }

    @Override
    public LocalDateTime dataHoraReserva() {
        return null;
    }
}
