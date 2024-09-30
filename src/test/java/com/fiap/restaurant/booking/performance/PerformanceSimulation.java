package com.fiap.restaurant.booking.performance;

import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.Duration;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.JSON_PATH_LIST_ID;
import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.core.CoreDsl.rampUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.internal.HttpCheckBuilders.status;

public class PerformanceSimulation extends Simulation {
    private static final HttpProtocolBuilder httpProtocol =
            http.baseUrl("http://localhost:8090")
                    .header("Content-type", MediaType.APPLICATION_JSON_VALUE);
    ActionBuilder buscarReservasRequest = http("Buscar reservas")
            .get("/api/bookings")
            .check(status().is(HttpStatus.OK.value()))
            .check(jsonPath(JSON_PATH_LIST_ID).saveAs("reservaId"));

    ActionBuilder buscarReservaPorIdRequest = http("Buscar reserva por ID")
            .get("/api/bookings/#{reservaId}")
            .check(status().is(HttpStatus.OK.value()));

    ActionBuilder buscarReservasCanceladasRequest = http("Buscar reservas canceladas")
            .get("/api/bookings/canceled")
            .check(status().is(HttpStatus.OK.value()));

    ActionBuilder buscarReservasConfirmadasRequest = http("Buscar reservas confirmadas")
            .get("/api/bookings/confirmed")
            .check(status().is(HttpStatus.OK.value()));

    ActionBuilder buscarReservasSolicitadasRequest = http("Buscar reservas solicitadas")
            .get("/api/bookings/requested")
            .check(status().is(HttpStatus.OK.value()));

    ActionBuilder buscarRestaurantesRequest = http("Buscar restaurantes")
            .get("/api/restaurants")
            .check(status().is(HttpStatus.OK.value()))
            .check(jsonPath(JSON_PATH_LIST_ID).saveAs("restauranteId"));

    ActionBuilder buscarRestaurantePorIdRequest = http("Buscar restaurante por ID")
            .get("/api/restaurants/#{restauranteId}")
            .check(status().is(HttpStatus.OK.value()));

    ScenarioBuilder cenarioBuscarReservas = scenario("Buscar reservas")
            .exec(buscarReservasRequest);

    ScenarioBuilder cenarioBuscarReservaPorId = scenario("Buscar reserva por ID")
            .exec(buscarReservasRequest)
            .exec(buscarReservaPorIdRequest);

    ScenarioBuilder cenarioBuscarReservasCanceladas = scenario("Buscar reservas canceladas")
            .exec(buscarReservasCanceladasRequest);

    ScenarioBuilder cenarioBuscarReservasConfirmadas = scenario("Buscar reservas confirmadas")
            .exec(buscarReservasSolicitadasRequest);

    ScenarioBuilder cenarioBuscarReservasSolicitadas = scenario("Buscar reservas solicitadas")
            .exec(buscarReservasConfirmadasRequest);

    ScenarioBuilder cenarioBuscarRestaurantes = scenario("Buscar restaurantes")
            .exec(buscarRestaurantesRequest);

    ScenarioBuilder cenarioBuscarRestaurantePorId = scenario("Buscar restaurante por ID")
            .exec(buscarRestaurantesRequest)
            .exec(buscarRestaurantePorIdRequest);

    {
        setUp(
                cenarioBuscarReservas.injectOpen(
                        rampUsersPerSec(1)
                                .to(2)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(2)
                                .during(Duration.ofSeconds(20)),
                        rampUsersPerSec(2)
                                .to(1)
                                .during(Duration.ofSeconds(10))
                ),
                cenarioBuscarReservaPorId.injectOpen(
                        rampUsersPerSec(1)
                                .to(10)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(10)
                                .during(Duration.ofSeconds(20)),
                        rampUsersPerSec(10)
                                .to(1)
                                .during(Duration.ofSeconds(10))
                ),
                cenarioBuscarReservasCanceladas.injectOpen(
                                rampUsersPerSec(1)
                                        .to(5)
                                        .during(Duration.ofSeconds(10)),
                                constantUsersPerSec(5)
                                        .during(Duration.ofSeconds(20)),
                                rampUsersPerSec(5)
                                        .to(1)
                                        .during(Duration.ofSeconds(10))),
                cenarioBuscarReservasConfirmadas.injectOpen(
                        rampUsersPerSec(1)
                                .to(5)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(5)
                                .during(Duration.ofSeconds(20)),
                        rampUsersPerSec(5)
                                .to(1)
                                .during(Duration.ofSeconds(10))),
                cenarioBuscarReservasSolicitadas.injectOpen(
                        rampUsersPerSec(1)
                                .to(5)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(5)
                                .during(Duration.ofSeconds(20)),
                        rampUsersPerSec(5)
                                .to(1)
                                .during(Duration.ofSeconds(10)))
        )
                .protocols(httpProtocol)
                .assertions(
                        global().responseTime().max().lt(800),
                        global().failedRequests().count().is(0L)
                );
    }

}
