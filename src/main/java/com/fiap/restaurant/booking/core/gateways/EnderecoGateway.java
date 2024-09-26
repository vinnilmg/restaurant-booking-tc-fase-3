package com.fiap.restaurant.booking.core.gateways;

import com.fiap.restaurant.booking.core.domains.Endereco;

import java.util.List;
import java.util.Optional;

public interface EnderecoGateway {
    Endereco create(Endereco endereco);

    List<Endereco> getAll();

    Optional<Endereco> findByid(Long id);

    List<Endereco> findByRua(String rua);

    List<Endereco> findByBairro(String bairro);

    List<Endereco> findByCidade(String cidade);

    List<Endereco> findByCep(String cep);

    void update(Endereco endereco);

    void delete(Long id);
}
