package com.fiap.restaurant.booking.core.usecases.restaurante;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import java.util.List;

@FunctionalInterface
public interface FindRestauranteByNomeUseCase {
    List<Restaurante> execute(String nome);
}
