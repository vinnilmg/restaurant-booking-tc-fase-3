package com.fiap.restaurant.booking.bdd;

import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;
import com.fiap.restaurant.booking.infrastructure.controllers.request.ReservaRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.ReservaResponse;
import com.fiap.restaurant.booking.utils.fixture.ReservaRequestFixture;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.fiap.restaurant.booking.utils.SchemaDefinitionConstants.RESERVA_LIST_SCHEMA;
import static com.fiap.restaurant.booking.utils.SchemaDefinitionConstants.RESERVA_SCHEMA;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReservaStepDefinition {
    private static final String ENDPOINT = "http://localhost:8090/api/bookings";
    private ReservaRequest request;
    private Long id;
    private Response response;

    @Dado("Que desejo reservar uma mesa em um restaurante")
    public void que_desejo_reservar_uma_mesa_em_um_restaurante() {
        request = ReservaRequestFixture.FULL();
    }

    @Quando("a mesa estiver disponível")
    public void a_mesa_estiver_disponivel() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post(ENDPOINT);
    }

    @Entao("deve realizar uma reserva na mesa do restaurante escolhido")
    public void deve_realizar_uma_reserva_na_mesa_do_restaurante_escolhido() {
        response
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @E("apresentar o resultado")
    public void apresentarOResultado() {
        response
                .then()
                .body(matchesJsonSchemaInClasspath(RESERVA_SCHEMA));
    }

    @Dado("que existam reservas")
    public void queExistamReservas() {
        response = when()
                .get(ENDPOINT);
    }

    @Quando("efetuar a busca de todas as reservas")
    public void efetuarABuscaDeTodasAsReservas() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(greaterThan(0)));
    }

    @Entao("deve apresentar todas as reservas")
    public void deveApresentarTodasAsReservas() {
        response.then()
                .body(matchesJsonSchemaInClasspath(RESERVA_LIST_SCHEMA));
    }

    @Quando("efetuar a busca de uma reserva específica")
    public void efetuarABuscaDeUmaReservaEspecifica() {
        response = when()
                .get(ENDPOINT.concat("/{id}"), 1);
    }

    @Entao("deve apresentar o resultado")
    public void deveApresentarOResultado() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath(RESERVA_SCHEMA));
    }

    @Dado("que alguma reserva tenha sido solicitada")
    public void queAlgumaReservaTenhaSidoSolicitada() {
        efetuarABuscaDeUmaReservaEspecifica();
    }

    @Quando("efetuar a busca das reservas solicitadas")
    public void efetuarABuscaDasReservasSolicitadas() {
        response = when()
                .get(ENDPOINT.concat("/requested"));
    }

    @Entao("deve apresentar todas as reservas que foram solicitadas")
    public void deveApresentarTodasAsReservasQueForamSolicitadas() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(greaterThan(0)))
                .body(matchesJsonSchemaInClasspath(RESERVA_LIST_SCHEMA));
    }

    @Dado("que desejo cancelar uma reserva")
    public void queDesejoCancelarUmaReserva() {
        efetuarABuscaDeUmaReservaEspecifica();
        id = response.then()
                .extract()
                .as(ReservaResponse.class)
                .id();
    }

    @Quando("a reserva existir")
    public void aReservaExistir() {
        assertNotNull(id);
    }

    @Entao("deve cancelar a reserva com sucesso")
    public void deveCancelarAReservaComSucesso() {
        when()
                .put(ENDPOINT.concat("/cancel/{id}"), id)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Dado("que alguma reserva tenha sido cancelada")
    public void queAlgumaReservaTenhaSidoCancelada() {
        efetuarABuscaDeUmaReservaEspecifica();
        final var status = response.then()
                .extract()
                .as(ReservaResponse.class)
                .status();

        assertThat(status)
                .isNotNull()
                .isEqualTo(StatusReservaEnum.CANCELADA.toString());
    }

    @Quando("efetuar a busca das reservas canceladas")
    public void efetuarABuscaDasReservasCanceladas() {
        response = when()
                .get(ENDPOINT.concat("/canceled"));
    }

    @Entao("deve apresentar todas as reservas que foram canceladas")
    public void deveApresentarTodasAsReservasQueForamCanceladas() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(greaterThan(0)))
                .body(matchesJsonSchemaInClasspath(RESERVA_LIST_SCHEMA));
    }

    @Dado("que desejo confirmar uma reserva")
    public void queDesejoConfirmarUmaReserva() {
        efetuarABuscaDeUmaReservaEspecifica();
        id = response.then()
                .extract()
                .as(ReservaResponse.class)
                .id();
    }

    @Entao("deve confirmar a reserva com sucesso")
    public void deveConfirmarAReservaComSucesso() {
        when()
                .put(ENDPOINT.concat("/confirm/{id}"), id)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Dado("que alguma reserva tenha sido confirmada")
    public void queAlgumaReservaTenhaSidoConfirmada() {
        efetuarABuscaDeUmaReservaEspecifica();
        final var status = response.then()
                .extract()
                .as(ReservaResponse.class)
                .status();

        assertThat(status)
                .isNotNull()
                .isEqualTo(StatusReservaEnum.CONFIRMADA.toString());
    }

    @Quando("efetuar a busca das reservas confirmadas")
    public void efetuarABuscaDasReservasConfirmadas() {
        response = when()
                .get(ENDPOINT.concat("/confirmed"));
    }

    @Entao("deve apresentar todas as reservas que foram confirmadas")
    public void deveApresentarTodasAsReservasQueForamConfirmadas() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(greaterThan(0)))
                .body(matchesJsonSchemaInClasspath(RESERVA_LIST_SCHEMA));
    }
}
