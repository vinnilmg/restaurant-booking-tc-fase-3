package com.fiap.restaurant.booking.infrastructure.gateways;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.infrastructure.persistence.mappers.FeedBackEntityMapper;
import com.fiap.restaurant.booking.infrastructure.persistence.repositories.FeedBackRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FeedBackRepositoryGateway implements FeedBackGateway {

    private final FeedBackRepository feedBackRepository;

    private final FeedBackEntityMapper feedBackEntityMapper;

    public FeedBackRepositoryGateway(FeedBackRepository feedBackRepository, FeedBackEntityMapper feedBackEntityMapper) {
        this.feedBackRepository = feedBackRepository;
        this.feedBackEntityMapper = feedBackEntityMapper;
    }


    @Override
    public FeedBack create(FeedBack feedBack) {
        return feedBackEntityMapper.toDomain(
                feedBackRepository.save(
                        feedBackEntityMapper.toEntity(feedBack)
                )
        );
    }

    @Override
    public List<FeedBack> findAll() {
        return feedBackEntityMapper.toDomains(feedBackRepository.findAll());

    }

    @Override
    public List<FeedBack> findAllByNomeCliente(String nomeCliente) {
        return feedBackEntityMapper.toDomains(feedBackRepository.findAllByNomeCliente(nomeCliente));

    }

    @Override
    public Optional<FeedBack> findById(Long id) {
        return feedBackRepository.findById(id)
                .map(feedBackEntityMapper::toDomain);
    }

    @Override
    public Optional<FeedBack> findByIdRestaurante(Long id) {
        return feedBackRepository.findByRestauranteId(id)
                .map(feedBackEntityMapper::toDomain);
    }


    @Override
    public void delete(Long id) {
        feedBackRepository.deleteById(id);
    }
}
