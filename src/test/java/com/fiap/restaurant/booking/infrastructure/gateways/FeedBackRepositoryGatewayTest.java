package com.fiap.restaurant.booking.infrastructure.gateways;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.infrastructure.persistence.mappers.FeedBackEntityMapper;
import com.fiap.restaurant.booking.infrastructure.persistence.repositories.FeedBackRepository;
import com.fiap.restaurant.booking.utils.InformationsFeedbackConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class FeedBackRepositoryGatewayTest {

    private FeedBackRepositoryGateway feedBackRepositoryGateway;
    private FeedBackRepository feedBackRepository;
    private FeedBackEntityMapper feedBackEntityMapper;

    @BeforeEach
    void init() {
        feedBackRepository = mock(FeedBackRepository.class);
        feedBackEntityMapper = mock(FeedBackEntityMapper.class);
        feedBackRepositoryGateway = new FeedBackRepositoryGateway(feedBackRepository, feedBackEntityMapper);
    }

    @Test
    void shouldCreateFeedBack() {
        final var expected = InformationsFeedbackConstants.buildFeedBackTest(1L,1L,1);
        final var entity = InformationsFeedbackConstants.FEEDBACK_BY_DOMAIN_WITH_ID(expected);

        when(feedBackEntityMapper.toEntity(expected)).thenReturn(entity);
        when(feedBackRepository.save(entity)).thenAnswer(i -> i.getArguments()[0]);
        when(feedBackEntityMapper.toDomain(entity)).thenReturn(expected);

        final var result = feedBackRepositoryGateway.create(expected);

        assertThat(result)
                .isNotNull()
                .isEqualTo(expected);

        verify(feedBackEntityMapper).toEntity(expected);
        verify(feedBackRepository).save(entity);
        verify(feedBackEntityMapper).toDomain(entity);
    }

    @Test
    void shouldFindAllFeedBacks() {
        final List<FeedBack> expected = List.of(InformationsFeedbackConstants.buildFeedBackTest(1L,1L,1));
        final var entities = List.of(InformationsFeedbackConstants.FEEDBACK_BY_DOMAIN_WITH_ID(expected.get(0)));
        when(feedBackRepository.findAll()).thenReturn(entities);
        when(feedBackEntityMapper.toDomains(entities)).thenReturn(expected);

        final var result = feedBackRepositoryGateway.findAll();

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(entities.size());

        verify(feedBackRepository).findAll();
        verify(feedBackEntityMapper).toDomains(entities);
    }

    @Test
    void shouldFindFeedBackById() {
        final var feedBackId = 1L;
        final FeedBack expected = InformationsFeedbackConstants.buildFeedBackTest(1L,1L,1);
        final var entity = InformationsFeedbackConstants.FEEDBACK_BY_DOMAIN_WITH_ID(expected);

        when(feedBackRepository.findById(feedBackId)).thenReturn(Optional.of(entity));
        when(feedBackEntityMapper.toDomain(entity)).thenReturn(expected);

        final var result = feedBackRepositoryGateway.findById(feedBackId);

        assertThat(result)
                .isNotNull()
                .isPresent()
                .hasValue(expected);

        verify(feedBackRepository).findById(feedBackId);
        verify(feedBackEntityMapper).toDomain(entity);
    }

    @Test
    void shouldFindFeedBacksByNomeCliente() {
        final var nomeCliente = "John Doe";
        final List<FeedBack> expected = List.of(InformationsFeedbackConstants.buildFeedBackTest(1L,1L,1));
        final var entities = List.of(InformationsFeedbackConstants.FEEDBACK_BY_DOMAIN_WITH_ID(expected.get(0)));

        when(feedBackRepository.findAllByNomeCliente(nomeCliente)).thenReturn(entities);
        when(feedBackEntityMapper.toDomains(entities)).thenReturn(expected);

        final var result = feedBackRepositoryGateway.findAllByNomeCliente(nomeCliente);

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(entities.size());

        verify(feedBackRepository).findAllByNomeCliente(nomeCliente);
        verify(feedBackEntityMapper).toDomains(entities);
    }

    @Test
    void shouldFindFeedBackByIdRestaurante() {
        final var restauranteId = 1L;
        final FeedBack expected = InformationsFeedbackConstants.buildFeedBackTest(1L,1L,1);
        final var entity = InformationsFeedbackConstants.FEEDBACK_BY_DOMAIN_WITH_ID(expected);

        when(feedBackRepository.findByRestauranteId(restauranteId)).thenReturn(Optional.of(entity));
        when(feedBackEntityMapper.toDomain(entity)).thenReturn(expected);

        final var result = feedBackRepositoryGateway.findByIdRestaurante(restauranteId);

        assertThat(result)
                .isNotNull()
                .isPresent()
                .hasValue(expected);

        verify(feedBackRepository).findByRestauranteId(restauranteId);
        verify(feedBackEntityMapper).toDomain(entity);
    }

    @Test
    void shouldDeleteFeedBack() {
        final var feedBackId = 1L;

        doNothing().when(feedBackRepository).deleteById(feedBackId);

        feedBackRepositoryGateway.delete(feedBackId);

        verify(feedBackRepository).deleteById(feedBackId);
    }
}
