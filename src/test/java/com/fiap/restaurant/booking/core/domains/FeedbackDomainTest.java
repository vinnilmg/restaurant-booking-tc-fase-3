package com.fiap.restaurant.booking.core.domains;

import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.utils.fixture.RestauranteDomainFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FeedbackDomainTest {

    @Test
    void shouldThrowExceptionWhenRatingIsNull() {
        Assertions.assertThrows(ValidationException.class, () -> {
            new FeedBackDomain(
                    null,
                    RestauranteDomainFixture.NOVO(),
                    "test",
                    null,
                    "test",
                    LocalDateTime.now());
        });
    }

    @Test
    void shouldThrowExceptionWhenClientNameIsNull() {
        Assertions.assertThrows(ValidationException.class, () -> {
            new FeedBackDomain(
                    Long.parseLong("1"),
                    RestauranteDomainFixture.NOVO(),
                    null,
                    1,
                    "test",
                    LocalDateTime.now());
        });
    }

    @Test
    void shouldThrowExceptionWhenRatingIsLessThanOne() {
        Assertions.assertThrows(ValidationException.class, () -> {
            new FeedBackDomain(
                    Long.parseLong("1"),
                    RestauranteDomainFixture.NOVO(),
                    "test",
                    0,
                    "test",
                    LocalDateTime.now());
        });
    }

    @Test
    void shouldThrowExceptionWhenRatingIsGreaterThanFive() {
        Assertions.assertThrows(ValidationException.class, () -> {
            new FeedBackDomain(
                    Long.parseLong("1"),
                    RestauranteDomainFixture.NOVO(),
                    "test",
                    6,
                    "test",
                    LocalDateTime.now());
        });
    }

    @Test
    void shouldThrowExceptionWhenClientNameIsEmpty() {
        Long id = 1L;
        Long restaurantID = 2L;
        String clientName = "";
        Integer rating = 4;
        String comment = "Great service!";

        Assertions.assertThrows(ValidationException.class, () -> {
            new FeedBackDomain(
                    id,
                    RestauranteDomainFixture.NOVO(),
                    clientName,
                    rating,
                    comment,
                    LocalDateTime.now()
            );
        });
    }

    @Test
    void shouldCreateFeedbackSuccessfully() {
        Long id = 1L;
        String clientName = "John";
        Integer rating = 4;
        String comment = "Great service!";

        FeedBackDomain feedback = new FeedBackDomain(
                id,
                RestauranteDomainFixture.NOVO(),
                clientName,
                rating,
                comment,
                LocalDateTime.now()
        );

        assertNotNull(feedback);
        assertEquals(id, feedback.getId());
        assertEquals(clientName, feedback.getNomeCliente());
        assertEquals(rating, feedback.getAvaliacao());
        assertEquals(comment, feedback.getComentario());
    }
}
