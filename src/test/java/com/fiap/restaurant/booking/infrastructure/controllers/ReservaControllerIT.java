package com.fiap.restaurant.booking.infrastructure.controllers;

import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;
import com.fiap.restaurant.booking.utils.fixture.ReservaRequestFixture;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
                    .contentType(APPLICATION_JSON_VALUE)
                    .body(reservaRequest)
                    .when()
                    .post(ENDPOINT)
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("$", hasKey("id"))
                    .body("$", hasKey("dataHoraCriacao"))
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
                    .when()
                    .get(ENDPOINT)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)));
        }

        @Test
        void shouldGetBookingsByCpf() {
            final var cpf = "26407243041";

            given()
                    .when()
                    .get(ENDPOINT.concat("/customers/{cpf}"), cpf)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("[0].cpfCliente", equalTo(cpf))
                    .body("[0]", hasKey("id"))
                    .body("[0]", hasKey("dataHoraReserva"))
                    .body("[0]", hasKey("status"))
                    .body("[0]", hasKey("dataHoraCriacao"));
        }

        @Test
        void shouldGetBookingById() {
            final var id = 1;

            given()
                    .when()
                    .get(ENDPOINT.concat("/{id}"), id)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("id", equalTo(id))
                    .body("$", hasKey("cpfCliente"))
                    .body("$", hasKey("dataHoraReserva"))
                    .body("$", hasKey("status"))
                    .body("$", hasKey("dataHoraCriacao"));
        }

        @Test
        void shouldGetCanceledBookings() {
            given()
                    .when()
                    .get("api/bookings/canceled")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)));
        }

        @Test
        void shouldGetRequestedBookings() {
            given()
                    .when()
                    .get("api/bookings/requested")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)));
        }

        @Test
        void shouldGetConfirmedBookings() {
            given()
                    .when()
                    .get("api/bookings/confirmed")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)));
        }
    }

    @Nested
    class Update {
        @Test
        void shouldCancelBooking() {
            final var id = 1L;

            given()
                    .when()
                    .put(ENDPOINT.concat("/cancel/{id}"), id)
                    .then()
                    .statusCode(HttpStatus.NO_CONTENT.value());
        }

        @Test
        void shouldConfirmBooking() {
            final var id = 1L;

            given()
                    .when()
                    .put(ENDPOINT.concat("/confirm/{id}"), id)
                    .then()
                    .statusCode(HttpStatus.NO_CONTENT.value());
        }
    }
}
