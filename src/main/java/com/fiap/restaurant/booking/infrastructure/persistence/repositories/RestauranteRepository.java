package com.fiap.restaurant.booking.infrastructure.persistence.repositories;

import com.fiap.restaurant.booking.infrastructure.persistence.entities.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RestauranteRepository extends JpaRepository<RestauranteRepository, Long> {
    List<RestauranteEntity> findByNome(String nome);

    List<RestauranteEntity> findByTipoCulinaria(String tipoCulinaria);

    List<RestauranteEntity> findByMediaFeedback(Double mediaFeedback);

}
