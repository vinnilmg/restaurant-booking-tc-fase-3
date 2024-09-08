package com.fiap.restaurant.booking.infrastructure.gateways;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.core.exception.ConvertModelToEntityException;
import com.fiap.restaurant.booking.core.exception.NotCreatedEntityException;
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
    public FeedBack create(Optional<FeedBack> feedBack) {
        return Optional.of(feedBackRepository.save(feedBack
                        .map(feedBackEntityMapper::toEntity)
                        .orElseThrow(()
                                -> new ConvertModelToEntityException(FeedBack.class))))
                .map(feedBackEntityMapper::toDomain)
                .orElseThrow(() -> new NotCreatedEntityException(FeedBack.class));
    }

    @Override
    public List<FeedBack> findAll() {
        return feedBackRepository.findAll()
                .stream()
                .map(feedBackEntityMapper::toDomain)
                .toList();
    }
}
