package com.fiap.restaurant.booking.core.domains;

import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;

import java.util.Objects;

public class MesaDomain implements Mesa {
    private Long id;
    private final Integer numeroDaMesa;
    private Restaurante restaurante;
    private StatusMesaEnum status;

    public MesaDomain(final Long id, final Restaurante idDoRestaurante, final Integer numeroDaMesa, StatusMesaEnum status) {
        validationToCreateInstance(id, numeroDaMesa, status);
        this.id = id;
        this.numeroDaMesa = numeroDaMesa;
        this.restaurante = idDoRestaurante;
        this.status = status;
    }

    public MesaDomain(final Integer numeroDaMesa, final Restaurante idDoRestaurante, StatusMesaEnum status) {
        this.numeroDaMesa = numeroDaMesa;
        this.restaurante = idDoRestaurante;
        this.status = status;
    }

    public MesaDomain(Long id, Integer numeroDaMesa, Restaurante idDoRestaurante, StatusMesaEnum status) {
        this.id = id;
        this.numeroDaMesa = numeroDaMesa;
        this.restaurante = idDoRestaurante;
        this.status = status;
    }

    public MesaDomain(Integer numeroDaMesa, StatusMesaEnum status) {
        this.numeroDaMesa = numeroDaMesa;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int getNumeroDaMesa() {
        return numeroDaMesa;
    }

    @Override
    public StatusMesaEnum getStatus() {
        return status;
    }

    @Override
    public Restaurante getRestaurante() {
        return this.restaurante;
    }

    @Override
    public Restaurante setRestaurante(Restaurante restaurante) {
        return this.restaurante = restaurante;
    }

    @Override
    public void updateStatus(final StatusMesaEnum status) {
        this.status = status;
    }

    public static MesaDomain createInstanceRequestValidation(Long idRestaurante, Integer numeroDaMesa, StatusMesaEnum status) {
        validationFromRequest(idRestaurante, numeroDaMesa, status);
        return new MesaDomain(numeroDaMesa, status);
    }

    private void validationToCreateInstance(Long id, Integer numeroDaMesa, StatusMesaEnum status) {
        if (numeroDaMesa <= 0 || numeroDaMesa > 100)
            throw ValidationException.of("numeroDaMesa", "Numero da mesa deve ser entre 1 e 100");
        if (Objects.isNull(status) || status.equals(""))
            throw ValidationException.of("Status", "Status deve ser valido");
    }

    private static void validationFromRequest(Long idRestaurante, Integer numeroDaMesa, StatusMesaEnum status) {
        if (Objects.isNull(status))
            throw ValidationException.of("Status invalido", "Status não pode ser nulo");
        if (numeroDaMesa <= 0 || numeroDaMesa > 100)
            throw ValidationException.of("Mesa invalida", "Numero da mesa deve ser entre 1 e 100");
        if (Objects.isNull(idRestaurante))
            throw ValidationException.of("Id do restaurante ausente", "Deve-se informar o Id do restaurante");
        if (idRestaurante < 1)
            throw ValidationException.of("Id do restaurante inválido", "id restaurante deve ser maior que zero");
    }
}
