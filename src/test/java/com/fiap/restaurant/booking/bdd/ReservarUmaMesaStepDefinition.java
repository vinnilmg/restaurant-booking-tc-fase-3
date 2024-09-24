package com.fiap.restaurant.booking.bdd;

import com.fiap.restaurant.booking.infrastructure.controllers.request.ReservaRequest;
import com.fiap.restaurant.booking.utils.fixture.ReservaRequestFixture;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.fiap.restaurant.booking.utils.SchemaDefinitionConstants.RESERVA_SCHEMA;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ReservarUmaMesaStepDefinition {
    private static final String ENDPOINT = "http://localhost:8090/api/bookings";
    private ReservaRequest request;
    private Response response;

    @Dado("Que desejo reservar uma mesa em um restaurante")
    public void que_desejo_reservar_uma_mesa_em_um_restaurante() {
        request = ReservaRequestFixture.FULL();
    }

    @Quando("a mesa estiver disponível")
    public void a_mesa_estiver_disponível() {
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
}
