package com.fiap.restaurant.booking.infrastructure.controllers;

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

import static com.fiap.restaurant.booking.utils.SchemaDefinitionConstants.ENDERECO_LIST_SCHEMA;
import static com.fiap.restaurant.booking.utils.SchemaDefinitionConstants.ENDERECO_SCHEMA;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase
public class EnderecoControllerIT {
    private static final String ENDPOINT = "/api/addresses";

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Nested
    class Get {
        @Test
        void shouldGetAllAddresses() {
            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(ENDPOINT)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)))
                    .body(matchesJsonSchemaInClasspath(ENDERECO_LIST_SCHEMA));
        }

        @Test
        void shouldGetAddressById() {
            final var id = 1;

            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(ENDPOINT.concat("/{id}"), id)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("id", equalTo(id))
                    .body(matchesJsonSchemaInClasspath(ENDERECO_SCHEMA));
        }

        @Test
        void shouldGetAddressByStreet() {
            final var rua = "Rua numero 1";

            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(ENDPOINT.concat("/street/{street}"), rua)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)))
                    .body(matchesJsonSchemaInClasspath(ENDERECO_LIST_SCHEMA));
        }

        @Test
        void shouldGetAddressByCity() {
            final var cidade = "Brusque";

            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(ENDPOINT.concat("/city/{city}"), cidade)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)))
                    .body(matchesJsonSchemaInClasspath(ENDERECO_LIST_SCHEMA));
        }

        @Test
        void shouldGetAddressByPostalCode() {
            final var cep = "02998050";

            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(ENDPOINT.concat("/postalCode/{cep}"), cep)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)))
                    .body(matchesJsonSchemaInClasspath(ENDERECO_LIST_SCHEMA));
        }

        @Test
        void shouldGetAddressByNeighborhood() {
            final var bairro = "República";

            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(ENDPOINT.concat("/neighborhood/{bairro}"), bairro)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)))
                    .body(matchesJsonSchemaInClasspath(ENDERECO_LIST_SCHEMA));
        }
    }
}
