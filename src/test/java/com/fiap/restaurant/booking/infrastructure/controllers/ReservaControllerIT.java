package com.fiap.restaurant.booking.infrastructure.controllers;

import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;
import com.fiap.restaurant.booking.utils.fixture.ReservaRequestFixture;
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

import static com.fiap.restaurant.booking.utils.SchemaDefinitionConstants.RESERVA_LIST_SCHEMA;
import static com.fiap.restaurant.booking.utils.SchemaDefinitionConstants.RESERVA_SCHEMA;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase
class ReservaControllerIT {
    private static final String ENDPOINT = "/api/bookings";

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
        void shouldCreateReserva() {
            final var reservaRequest = ReservaRequestFixture.FULL();

            given()
                    .filter(new AllureRestAssured())
                    .contentType(APPLICATION_JSON_VALUE)
                    .body(reservaRequest)
                    .when()
                    .post(ENDPOINT)
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body(matchesJsonSchemaInClasspath(RESERVA_SCHEMA))
                    .body("cpfCliente", equalTo(reservaRequest.cpfCliente()))
                    .body("dataHoraReserva", equalTo(reservaRequest.dataHoraReserva()))
                    .body("status", equalTo(StatusReservaEnum.SOLICITADA.toString()));
        }
    }

    @Nested
    class Get {
        @Test
        void shouldGetAllBookings() {
            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(ENDPOINT)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)))
                    .body(matchesJsonSchemaInClasspath(RESERVA_LIST_SCHEMA));
        }

        @Test
        void shouldGetBookingsByCpf() {
            final var cpf = "26407243041";

            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(ENDPOINT.concat("/customers/{cpf}"), cpf)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)))
                    .body("[0].cpfCliente", equalTo(cpf))
                    .body(matchesJsonSchemaInClasspath(RESERVA_LIST_SCHEMA));
        }

        @Test
        void shouldGetBookingById() {
            final var id = 1;

            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(ENDPOINT.concat("/{id}"), id)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("id", equalTo(id))
                    .body(matchesJsonSchemaInClasspath(RESERVA_SCHEMA));
        }

        @Test
        void shouldThrowNotFoundWhenBookingIdIsNotExists() {
            final var id = 10000;

            when()
                    .get(ENDPOINT.concat("/{id}"), id)
                    .then()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .body("message", equalTo("Reserva not found"));
        }

        @Test
        void shouldGetCanceledBookings() {
            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get("api/bookings/canceled")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)))
                    .body(matchesJsonSchemaInClasspath(RESERVA_LIST_SCHEMA));
        }

        @Test
        void shouldGetRequestedBookings() {
            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get("api/bookings/requested")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)))
                    .body(matchesJsonSchemaInClasspath(RESERVA_LIST_SCHEMA));
        }

        @Test
        void shouldGetConfirmedBookings() {
            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get("api/bookings/confirmed")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)))
                    .body(matchesJsonSchemaInClasspath(RESERVA_LIST_SCHEMA));
        }
    }

    @Nested
    class Update {
        @Test
        void shouldCancelBooking() {
            final var id = 1L;

            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .put(ENDPOINT.concat("/cancel/{id}"), id)
                    .then()
                    .statusCode(HttpStatus.NO_CONTENT.value());
        }

        @Test
        void shouldConfirmBooking() {
            final var id = 1L;

            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .put(ENDPOINT.concat("/confirm/{id}"), id)
                    .then()
                    .statusCode(HttpStatus.NO_CONTENT.value());
        }
    }
}
