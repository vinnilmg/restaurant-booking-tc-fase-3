package com.fiap.restaurant.booking.core.domains;

import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.utils.InformationsRestauranteConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FeedbackDomainTest {

    @Test
    void shouldThrowExceptionWhenRatingIsNull() {
        Assertions.assertThrows(ValidationException.class, () -> {new FeedBackDomain(
                null,
                InformationsRestauranteConstants.buildRestauranteTest(2L),
                "test",
                null,
                "test",
                LocalDateTime.now());
        });
    }

    @Test
    void shouldThrowExceptionWhenRestaurantIdIsNull() {
        Assertions.assertThrows(ValidationException.class, () -> {new FeedBackDomain(
                Long.parseLong("1"),
                InformationsRestauranteConstants.buildRestauranteTest(null),
                "test",
                1,
                "test",
                LocalDateTime.now());
        });
    }

    @Test
    void shouldThrowExceptionWhenClientNameIsNull() {
        Assertions.assertThrows(ValidationException.class, () -> {new FeedBackDomain(
                Long.parseLong("1"),
                InformationsRestauranteConstants.buildRestauranteTest(2L),
                null,
                1,
                "test",
                LocalDateTime.now());
        });
    }

    @Test
    void shouldThrowExceptionWhenRatingIsLessThanOne() {
        Assertions.assertThrows(ValidationException.class, () -> {new FeedBackDomain(
                Long.parseLong("1"),
                InformationsRestauranteConstants.buildRestauranteTest(2L),
                "test",
                0,
                "test",
                LocalDateTime.now());
        });
    }

    @Test
    void shouldThrowExceptionWhenRatingIsGreaterThanFive() {
        Assertions.assertThrows(ValidationException.class, () -> {new FeedBackDomain(
                Long.parseLong("1"),
                InformationsRestauranteConstants.buildRestauranteTest(2L),
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
            new FeedBackDomain(id, InformationsRestauranteConstants.buildRestauranteTest(2L), clientName, rating, comment, LocalDateTime.now());
        });
    }

    @Test
    void shouldCreateFeedbackSuccessfully() {
        Long id = 1L;
        Long restaurantID = 2L;
        String clientName = "John";
        Integer rating = 4;
        String comment = "Great service!";

        FeedBackDomain feedback = new FeedBackDomain(id, InformationsRestauranteConstants.buildRestauranteTest(2L), clientName, rating, comment, LocalDateTime.now());

        assertNotNull(feedback);
        assertEquals(id, feedback.getId());
        assertEquals(restaurantID, feedback.getRestaurante().getId());
        assertEquals(clientName, feedback.getNomeCliente());
        assertEquals(rating, feedback.getAvaliacao());
        assertEquals(comment, feedback.getComentario());
    }

    @Test
    void shouldCreateRequestValidationFeedbackSuccessfully() {
        Long id = 1L;
        Long restaurantID = 2L;
        String clientName = "John";
        Integer rating = 4;
        String comment = "Great service!";

        FeedBackDomain feedback = FeedBackDomain.createInstanceRequestValidation(restaurantID, clientName, rating, comment);

        assertNotNull(feedback);
        assertEquals(clientName, feedback.getNomeCliente());
        assertEquals(rating, feedback.getAvaliacao());
        assertEquals(comment, feedback.getComentario());
    }

    @Test
    void shouldThrowExceptionWhenClientNameIsEmptyInRequestValidation() {
        Long id = 1L;
        Long restaurantID = 2L;
        String clientName = "";
        Integer rating = 4;
        String comment = "Great service!";

        Assertions.assertThrows(ValidationException.class, () -> {
            FeedBackDomain.createInstanceRequestValidation(restaurantID, clientName, rating, comment);
        });
    }

    @Test
    void shouldThrowExceptionWhenClientNameIsNullInRequestValidation() {
        Long id = 1L;
        Long restaurantID = 2L;
        String clientName = null;
        Integer rating = 4;
        String comment = "Great service!";

        Assertions.assertThrows(ValidationException.class, () -> {
            FeedBackDomain.createInstanceRequestValidation(restaurantID, clientName, rating, comment);
        });
    }

    @Test
    void shouldThrowExceptionWhenRatingIsNullInRequestValidation() {
        Long id = 1L;
        Long restaurantID = 2L;
        String clientName = "test";
        Integer rating = null;
        String comment = "Great service!";

        Assertions.assertThrows(ValidationException.class, () -> {
            FeedBackDomain.createInstanceRequestValidation(restaurantID, clientName, rating, comment);
        });
    }

    @Test
    void shouldThrowExceptionWhenRatingIsLessThanOneInRequestValidation() {
        Long id = 1L;
        Long restaurantID = 2L;
        String clientName = "test";
        Integer rating = 0;
        String comment = "Great service!";

        Assertions.assertThrows(ValidationException.class, () -> {
            FeedBackDomain.createInstanceRequestValidation(restaurantID, clientName, rating, comment);
        });
    }

    @Test
    void shouldThrowExceptionWhenCreatingRequestValidationFeedbackWithId() {
        Long id = 1L;
        Long restaurantID = 2L;
        String clientName = "test";
        Integer rating = 0;
        String comment = "Great service!";

        Assertions.assertThrows(ValidationException.class, () -> {
            FeedBackDomain.createInstanceRequestValidation(restaurantID, clientName, rating, comment);
        });
    }
}
