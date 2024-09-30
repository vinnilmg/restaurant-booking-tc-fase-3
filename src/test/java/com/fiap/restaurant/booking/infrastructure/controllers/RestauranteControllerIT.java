package com.fiap.restaurant.booking.infrastructure.controllers;

import com.fiap.restaurant.booking.utils.fixture.RestauranteRequestFixture;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import io.qameta.allure.restassured.AllureRestAssured;

import static com.fiap.restaurant.booking.utils.SchemaDefinitionConstants.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase
public class RestauranteControllerIT {
    private static final String ENDPOINT = "/api/restaurants";

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
        void shouldCreateRestaurante() {
            final var restauranteRequest = RestauranteRequestFixture.FULL();

            given()
                    .filter(new AllureRestAssured())
                    .contentType(APPLICATION_JSON_VALUE)
                    .body(restauranteRequest)
                    .when()
                    .post(ENDPOINT)
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body(matchesJsonSchemaInClasspath(RESTAURANTE_SCHEMA))
                    .body("nome", equalTo(restauranteRequest.nome()))
                    .body("cnpj", equalTo(restauranteRequest.cnpj()))
                    .body("tipoCulinaria", equalTo(restauranteRequest.tipoCulinaria()))
                    .body("inicioFuncionamento", equalTo(restauranteRequest.inicioFuncionamento().concat(":00")))
                    .body("fimFuncionamento", equalTo(restauranteRequest.fimFuncionamento().concat(":00")))
                    .body("capacidade", equalTo(restauranteRequest.capacidade()))
                    .body("mediaFeedback", equalTo(restauranteRequest.mediaFeedback().floatValue()));
        }
    }

    @Nested
    class Get {
        @Test
        void shouldGetAllRestaurants() {
            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(ENDPOINT)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)))
                    .body(matchesJsonSchemaInClasspath(RESTAURANTE_LIST_SCHEMA));
        }

        @Test
        void shouldGetRestaurantById() {
            final var id = 1;

            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(ENDPOINT.concat("/{id}"), id)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("id", equalTo(id))
                    .body(matchesJsonSchemaInClasspath(RESTAURANTE_SCHEMA));
        }

        @Test
        void shouldThrowNotFoundWhenRestaurantIdDontExist() {
            final var id = 10000;

            when()
                    .get(ENDPOINT.concat("/{id}"), id)
                    .then()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .body("message", equalTo("Restaurante not found"));
        }

        @Test
        void shouldGetRestaurantByName() {
            final var name = "Restaurante Exemplo";

            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(ENDPOINT.concat("/name/{name}"), name)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)))
                    .body("[0].nome", equalTo(name))
                    .body(matchesJsonSchemaInClasspath(RESTAURANTE_LIST_SCHEMA));
        }

        @Test
        void shouldGetRestaurantByRua() {
            final var street = "Praça da República";

            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(ENDPOINT.concat("/street/{street}"), street)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)))
                    .body("[0].endereco.rua", equalTo(street))
                    .body(matchesJsonSchemaInClasspath(RESTAURANTE_LIST_SCHEMA));
        }

        @Test
        void shouldGetRestaurantByBairro() {
            final var district = "Vila Bela";

            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(ENDPOINT.concat("/district/{district}"), district)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)))
                    .body("[0].endereco.bairro", equalTo(district))
                    .body(matchesJsonSchemaInClasspath(RESTAURANTE_LIST_SCHEMA));
        }

        @Test
        void shouldGetRestaurantByCidade() {
            final var city = "São Paulo";

            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(ENDPOINT.concat("/city/{city}"), city)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)))
                    .body("[0].endereco.cidade", equalTo(city))
                    .body(matchesJsonSchemaInClasspath(RESTAURANTE_LIST_SCHEMA));
        }

        @Test
        void shouldGetRestaurantByTipoCulinaria() {
            final var tipoCulinaria = "BRASILEIRA";

            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(ENDPOINT.concat("/cuisine/{cuisine}"), tipoCulinaria)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)))
                    .body("[0].tipoCulinaria", equalTo(tipoCulinaria))
                    .body(matchesJsonSchemaInClasspath(RESTAURANTE_LIST_SCHEMA));
        }

        @Test
        void shouldGetRestaurantByMediaFeedback() {
            final var mediaFeedback = 5.0F;

            given()
                    .filter(new AllureRestAssured())
                    .when()
                    .get(ENDPOINT.concat("/feedback/{feedback}"), mediaFeedback)
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("$", hasSize(greaterThan(0)))
                    .body("[0].mediaFeedback", equalTo(mediaFeedback))
                    .body(matchesJsonSchemaInClasspath(RESTAURANTE_LIST_SCHEMA));
        }

    }
}
