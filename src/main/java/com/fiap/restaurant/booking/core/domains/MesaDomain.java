package com.fiap.restaurant.booking.core.domains;

import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;

import java.util.Objects;

public class MesaDomain {
    private Long id;
    private int numeroDaMesa;
    private StatusMesaEnum status;
    //TOD: Esperar o id do restaurante da classe RestauranteDomain

    public MesaDomain(){}

    public MesaDomain( final Long id, final int numeroDaMesa, StatusMesaEnum status ) {
        validationToCreateInstance(id, numeroDaMesa, status);
        this.id = id;
        this.numeroDaMesa = numeroDaMesa;
        this.status = status;
    }
    public MesaDomain(final int numeroDaMesa, StatusMesaEnum status){
        this.numeroDaMesa = numeroDaMesa;
        this.status = status;
    }
    public Long getId() {
        return id;
    }
    public int getNumeroDaMesa() {
        return numeroDaMesa;
    }
    public StatusMesaEnum getStatus() {
        return status;
    }
    public void updateStatus( final StatusMesaEnum status) {
        this.status = status;
    }
    private void validationToCreateInstance(Long id, int numeroDaMesa, StatusMesaEnum status) {
        if (numeroDaMesa <= 0 || numeroDaMesa > 100)
            throw ValidationException.of("numeroDaMesa", "Numero da mesa deve ser entre 1 e 100");
        if (Objects.isNull(status) || status.equals(""))
            throw ValidationException.of("Status", "Status deve ser valido");
    }
}
