package com.fiap.restaurant.booking.infrastructure.persistence.repositories;

import com.fiap.restaurant.booking.infrastructure.persistence.entities.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
    List<EnderecoEntity> findByRua(String rua);

    List<EnderecoEntity> findByBairro(String bairro);

    List<EnderecoEntity> findByCidade(String cidade);

    List<EnderecoEntity> findByCep(String cep);
}
