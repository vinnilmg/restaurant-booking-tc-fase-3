package com.fiap.restaurant.booking.infrastructure.gateways;

import com.fiap.restaurant.booking.core.domains.FeedBackDomain;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.infrastructure.controllers.response.MessageResponse;
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
    public FeedBackDomain create(FeedBackDomain feedBack) {
        return feedBackEntityMapper.toDomain(
                feedBackRepository.save(
                        feedBackEntityMapper.toEntity(
                                feedBack)
                )
        );
    }

    @Override
    public List<FeedBackDomain> findAll() {
        return feedBackRepository.findAll()
                .stream()
                .map(feedBackEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<FeedBackDomain> findAllByNomeCliente(String nomeCliente) {
        return feedBackRepository.findAllByNomeCliente(nomeCliente)
                .stream()
                .map(feedBackEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<FeedBackDomain> findById(Long id) {
        return feedBackRepository.findById(id)
                .map(feedBackEntityMapper::toDomain);
    }

    @Override
    public String delete(Long id) {
        feedBackRepository.deleteById(id);
        return String.format("Feedback by id %s was deleted by success.", id);
    }
}
