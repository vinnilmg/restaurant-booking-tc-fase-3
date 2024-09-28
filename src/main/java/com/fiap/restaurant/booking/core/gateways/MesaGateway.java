package com.fiap.restaurant.booking.core.gateways;

import com.fiap.restaurant.booking.core.domains.Mesa;
import com.fiap.restaurant.booking.core.domains.MesaDomain;
import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;

import java.util.List;
import java.util.Optional;

public interface MesaGateway {

    MesaDomain create(MesaDomain mesa);

    Optional<MesaDomain> findById(Long id, Integer numeroMesa);

    List<MesaDomain> findByStatus(StatusMesaEnum status);

    void delete(Long id);
}
