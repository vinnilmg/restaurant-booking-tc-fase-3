package com.fiap.restaurant.booking.core.domains;

import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;

public class MesaDomain implements Mesa {
    private Long id;
    private final int numeroDaMesa;
    private StatusMesaEnum status;
    //TODO: Esperar o id do restaurante da classe RestauranteDomain

    public MesaDomain( final Long id, final int numeroDaMesa, StatusMesaEnum status ) {
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

    @Override
    public void updateStatus( final StatusMesaEnum status) {
        this.status = status;
    }


}
