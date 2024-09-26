package com.fiap.restaurant.booking.infrastructure.gateways;

import com.fiap.restaurant.booking.core.domains.Endereco;
import com.fiap.restaurant.booking.core.gateways.EnderecoGateway;
import com.fiap.restaurant.booking.infrastructure.persistence.mappers.EnderecoEntityMapper;
import com.fiap.restaurant.booking.infrastructure.persistence.repositories.EnderecoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EnderecoRepositoryGateway implements EnderecoGateway {
    private final EnderecoRepository enderecoRepository;
    private final EnderecoEntityMapper enderecoEntityMapper;

    public EnderecoRepositoryGateway(
            EnderecoRepository enderecoRepository,
            EnderecoEntityMapper enderecoEntityMapper
    ) {
        this.enderecoRepository = enderecoRepository;
        this.enderecoEntityMapper = enderecoEntityMapper;
    }

    @Override
    public Endereco create(final Endereco endereco) {
        final var entity = enderecoEntityMapper.toEntity(endereco);
        final var entitySaved = enderecoRepository.save(entity);
        return enderecoEntityMapper.toDomain(entitySaved);
    }

    @Override
    public List<Endereco> getAll() {
        final var entities = enderecoRepository.findAll();
        return enderecoEntityMapper.toDomains(entities);
    }

    @Override
    public Optional<Endereco> findByid(final Long id) {
        return enderecoRepository.findById(id)
                .map(enderecoEntityMapper::toDomain);
    }

    @Override
    public List<Endereco> findByRua(final String rua) {
        final var entities = enderecoRepository.findByRua(rua);
        return enderecoEntityMapper.toDomains(entities);
    }

    @Override
    public List<Endereco> findByBairro(final String bairro) {
        final var entities = enderecoRepository.findByBairro(bairro);
        return enderecoEntityMapper.toDomains(entities);
    }

    @Override
    public List<Endereco> findByCidade(final String cidade) {
        final var entities = enderecoRepository.findByCidade(cidade);
        return enderecoEntityMapper.toDomains(entities);
    }

    @Override
    public List<Endereco> findByCep(final String cep) {
        final var entities = enderecoRepository.findByCep(cep);
        return enderecoEntityMapper.toDomains(entities);
    }

    @Override
    public void update(final Endereco endereco) {
        final var entity = enderecoEntityMapper.toEntity(endereco);
        enderecoRepository.save(entity);
    }

    @Override
    public void delete(final Long id) {
        enderecoRepository.deleteById(id);
    }
}
