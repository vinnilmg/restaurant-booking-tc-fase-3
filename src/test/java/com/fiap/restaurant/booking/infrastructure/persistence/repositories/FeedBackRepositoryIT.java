package com.fiap.restaurant.booking.infrastructure.persistence.repositories;

import com.fiap.restaurant.booking.infrastructure.persistence.entities.FeedBackEntity;
import com.fiap.restaurant.booking.utils.InformationsFeedbackConstants;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@Transactional
class FeedBackRepositoryIT {

    @Autowired
    private FeedBackRepository feedBackRepository;

    @Test
    void shouldCountFeedBacks() {
        final long result = feedBackRepository.count();
        assertThat(result)
                .isPositive();
    }

    @Test
    void shouldSaveFeedBack() {
        final var entity = InformationsFeedbackConstants.buildFeedBackTestEntity();

        final var result = feedBackRepository.save(entity);

        assertThat(result)
                .isNotNull()
                .extracting(
                        FeedBackEntity::getId,
                        FeedBackEntity::getNomeCliente,
                        FeedBackEntity::getRestaurante,
                        FeedBackEntity::getComentario
                ).containsExactly(
                        entity.getId(),
                        entity.getNomeCliente(),
                        entity.getRestaurante(),
                        entity.getComentario()
                );

        assertThat(result.getId())
                .isNotNull();
    }

    @Test
    void shouldFindAllFeedBacks() {

        final var result = feedBackRepository.findAll();

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(result.size());
    }

    @Test
    void shouldFindFeedBackById() {


        final var result = feedBackRepository.findById(1L);

        assertThat(result)
                .isNotNull()
                .isPresent();
    }

    @Test
    void shouldFindFeedBacksByNomeCliente() {
        final var nomeCliente = "John Doe";


        final var result = feedBackRepository.findAllByNomeCliente(nomeCliente);

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(result.size());
    }

    @Test
    void shouldDeleteFeedBack() {
        final var expected = InformationsFeedbackConstants.buildFeedBackTest(1L, 1);
        final var entity = InformationsFeedbackConstants.FEEDBACK_BY_DOMAIN_WITH_ID(expected);

        feedBackRepository.save(entity);
        final var id = entity.getId();

        feedBackRepository.deleteById(id);
        final var result = feedBackRepository.findById(id);

        assertThat(result)
                .isNotPresent();
    }
}
