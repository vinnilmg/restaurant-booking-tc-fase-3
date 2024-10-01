package com.fiap.restaurant.booking.core.usecases.mesa.impl;

import com.fiap.restaurant.booking.core.domains.Mesa;
import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.MesaGateway;
import com.fiap.restaurant.booking.core.usecases.mesa.FindIdRestauranteAndNumeroMesa;

import static java.util.Objects.isNull;

public class FindIdRestauranteAndNumeroMesaImpl implements FindIdRestauranteAndNumeroMesa {

    private final MesaGateway mesaGateway;

    public FindIdRestauranteAndNumeroMesaImpl(MesaGateway mesaGateway) {
        this.mesaGateway = mesaGateway;
    }

    @Override
    public Mesa execute(Long idRestaurante, Integer numeroMesa) {
        if (isNull(numeroMesa) || numeroMesa < 0) throw ValidationException.of("Numero da Mesa", "cannot be null or negative");
        return mesaGateway.findByRestauranteIdAndNumeroDaMesa(idRestaurante,numeroMesa)
                .orElseThrow(() -> NotFoundException.of("Mesa"));
    }
}
