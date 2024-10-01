package com.fiap.restaurant.booking.core.domains;

import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;
import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;

import static io.micrometer.common.util.StringUtils.isEmpty;
import static java.util.Objects.isNull;

public class MesaDomain implements Mesa {
    private Long id;
    private final Integer numeroDaMesa;
    private Restaurante restaurante;
    private StatusMesaEnum status;

    public MesaDomain(final Long id, final Restaurante restaurante, final Integer numeroDaMesa, StatusMesaEnum status) {
        validationToCreateInstance(id, numeroDaMesa, status);
        this.id = id;
        this.numeroDaMesa = numeroDaMesa;
        this.restaurante = restaurante;
        this.status = statusValidation(String.valueOf(status));
    }

    public MesaDomain(Integer numeroDaMesa, StatusMesaEnum status) {
        this.numeroDaMesa = numeroDaMesa;
        this.status = statusValidation(String.valueOf(status));
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
        if (isNull(status) || status.equals(""))
            throw ValidationException.of("Status", "Status deve ser valido");
    }

    private static void validationFromRequest(Long idRestaurante, Integer numeroDaMesa, StatusMesaEnum status) {
        if (isNull(status))
            throw ValidationException.of("Status invalido", "Status não pode ser nulo");
        if (numeroDaMesa <= 0 || numeroDaMesa > 100)
            throw ValidationException.of("Mesa invalida", "Numero da mesa deve ser entre 1 e 100");
        if (isNull(idRestaurante))
            throw ValidationException.of("Id do restaurante ausente", "Deve-se informar o Id do restaurante");
        if (idRestaurante < 1)
            throw ValidationException.of("Id do restaurante inválido", "id restaurante deve ser maior que zero");
    }

    private static StatusMesaEnum statusValidation(final String status) {
        if (isEmpty(status)) throw ValidationException.of("Status", "cannot be null or empty");
        final var statusEnum = StatusMesaEnum.toEnum(status);
        if (statusEnum.isEmpty()) throw ValidationException.of("Status", "is invalid");
        return statusEnum.get();
    }
}
