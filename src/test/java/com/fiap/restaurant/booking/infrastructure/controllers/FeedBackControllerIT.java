package com.fiap.restaurant.booking.infrastructure.controllers;

import com.fiap.restaurant.booking.infrastructure.controllers.request.FeedBackRequest;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.FeedBackEntity;
import com.fiap.restaurant.booking.utils.FeedBackValidationsMessages;
import com.fiap.restaurant.booking.utils.InformationsFeedbackConstants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static com.fiap.restaurant.booking.utils.SchemaDefinitionConstants.FEEDBACK_LIST_SCHEMA;
import static com.fiap.restaurant.booking.utils.SchemaDefinitionConstants.FEEDBACK_SCHEMA;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase
class FeedBackControllerIT {

    private static final String ENDPOINT = "/api/feedbacks";

    private final FeedBackEntity feedBackEntity = InformationsFeedbackConstants.buildFeedBackEntityFull();
    private final FeedBackRequest feedBackRequest = InformationsFeedbackConstants.buildFeedbackRequestFromEntity(feedBackEntity);

    @LocalServerPort
    private int port;


    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Nested
    class Create {
        @Test
        void shouldCreateFeedBack() {

            given()
                    .filter(new AllureRestAssured())
                    .contentType(APPLICATION_JSON_VALUE)
                    .body(feedBackRequest)
                    .when()
                    .post(ENDPOINT)
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body(matchesJsonSchemaInClasspath(FEEDBACK_SCHEMA))
                    .body("nomeCliente", equalTo(feedBackRequest.nomeCliente()))
                    .body("comentario", equalTo(feedBackRequest.comentario()));
        }
    }

    @Nested
    class Delete {
        @Test
        void shouldDeleteFeedBack() {
            final var id = 1L;

            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .delete(ENDPOINT.concat("/{id}"), id)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("message", equalTo(FeedBackValidationsMessages.getMessageWhenDeleteAFeedback(id)));
        }

        @Test
        void shouldThrowNotFoundWhenDeleteFeedBackWithNonExistentId() {
            final var id = 10000L;

            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .delete(ENDPOINT.concat("/{id}"), id)
                    .then()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .body("message", equalTo(FeedBackValidationsMessages.getMessageIdFeedbackNotFound(id)));
        }
    }


    @Nested
    class Get {
        @Test
        void shouldGetAllFeedBacks() {
            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(ENDPOINT)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)))
                    .body(matchesJsonSchemaInClasspath(FEEDBACK_LIST_SCHEMA));
        }

        @Test
        void shouldGetFeedBackById() {
            final var id = 1;

            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(ENDPOINT.concat("/{id}"), id)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("id", equalTo(id))
                    .body(matchesJsonSchemaInClasspath(FEEDBACK_SCHEMA));
        }

        @Test
        void shouldThrowNotFoundWhenFeedBackIdIsNotExists() {
            final var id = 10000L;

            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(ENDPOINT.concat("/{id}"), id)
                    .then()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .body("message", equalTo(FeedBackValidationsMessages.getMessageIdFeedbackNotFound(id)));


        }

        @Test
        void shouldGetFeedBacksByNomeCliente() {
            final var nomeCliente = feedBackRequest.nomeCliente();

            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(ENDPOINT.concat("/nome-cliente/{nomeCliente}"), nomeCliente)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)))
                    .body("[0].nomeCliente", equalTo(nomeCliente))
                    .body(matchesJsonSchemaInClasspath(FEEDBACK_LIST_SCHEMA));
        }
    }

}
