package com.fiap.restaurant.booking.infrastructure.gateways;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.infrastructure.persistence.mappers.RestauranteEntityMapper;
import com.fiap.restaurant.booking.infrastructure.persistence.repositories.RestauranteRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class RestauranteRepositoryGateway implements RestauranteGateway {
    private final RestauranteRepository restauranteRepository;
    private final RestauranteEntityMapper restauranteEntityMapper;

    public RestauranteRepositoryGateway(RestauranteRepository restauranteRepository,
                                        RestauranteEntityMapper restauranteEntityMapper) {
        this.restauranteRepository = restauranteRepository;
        this.restauranteEntityMapper = restauranteEntityMapper;
    }

    @Override
    public Restaurante create(final Restaurante restaurante) {
        final var entity = restauranteEntityMapper.toEntity(restaurante);
        final var entitySaved = restauranteRepository.save(entity);
        return restauranteEntityMapper.toDomain(entitySaved);
    }

    @Override
    public List<Restaurante> getAll() {
        final var entities = restauranteRepository.findAll();
        return restauranteEntityMapper.toDomains(entities);
    }

    @Override
    public Optional<Restaurante> findById(final Long id) {
        return restauranteRepository.findById(id)
                .map(restauranteEntityMapper::toDomain);
    }

    @Override
    public List<Restaurante> findByNome(final String nome) {
        final var entities = restauranteRepository.findByNome(nome);
        return restauranteEntityMapper.toDomains(entities);
    }

    @Override
    public List<Restaurante> findByEnderecoRua(String rua) {
        final var entities = restauranteRepository.findByEnderecoRua(rua);
        return restauranteEntityMapper.toDomains(entities);
    }

    @Override
    public List<Restaurante> findByEnderecoBairro(String bairro) {
        final var entities = restauranteRepository.findByEnderecoBairro(bairro);
        return restauranteEntityMapper.toDomains(entities);
    }

    @Override
    public List<Restaurante> findByEnderecoCidade(String cidade) {
        final var entities = restauranteRepository.findByEnderecoCidade(cidade);
        return restauranteEntityMapper.toDomains(entities);
    }

    @Override
    public List<Restaurante> findByTipoCulinaria(final String tipoCulinaria) {
        final var entities = restauranteRepository.findByTipoCulinaria(tipoCulinaria);
        return restauranteEntityMapper.toDomains(entities);
    }

    @Override
    public List<Restaurante> findByMediaFeedback(Double mediaFeedback) {
        final var entities = restauranteRepository.findByMediaFeedback(mediaFeedback);
        return restauranteEntityMapper.toDomains(entities);
    }

    @Override
    public Optional<Restaurante> findByCnpj(final String cnpj) {
        return restauranteRepository.findByCnpj(cnpj)
                .map(restauranteEntityMapper::toDomain);
    }

    @Override
    public void update(Restaurante restaurante) {
        final var entity = restauranteEntityMapper.toEntity(restaurante);
        restauranteRepository.save(entity);
    }
}
