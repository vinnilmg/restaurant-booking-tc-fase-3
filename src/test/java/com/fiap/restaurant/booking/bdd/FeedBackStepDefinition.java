package com.fiap.restaurant.booking.bdd;

import com.fiap.restaurant.booking.infrastructure.controllers.request.FeedBackRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.FeedBackResponse;
import com.fiap.restaurant.booking.utils.InformationsFeedbackConstants;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.fiap.restaurant.booking.utils.SchemaDefinitionConstants.FEEDBACK_LIST_SCHEMA;
import static com.fiap.restaurant.booking.utils.SchemaDefinitionConstants.FEEDBACK_SCHEMA;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FeedBackStepDefinition {
    private static final String ENDPOINT = "http://localhost:8090/api/feedbacks";
    private FeedBackRequest request;
    private Long id;
    private Response response;

    @Dado("Que desejo deixar um feedback para um restaurante")
    public void queDesejoDeixarUmFeedBackParaUmRestaurante() {
        request = InformationsFeedbackConstants.buildFeedbackRequest();
    }

    @Quando("o feedback for submetido")
    public void oFeedbackForSubmetido() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post(ENDPOINT);
    }

    @Entao("deve criar o feedback com sucesso")
    public void deveCriarOFeedBackComSucesso() {
        response
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @E("apresentar o feedback")
    public void apresentarOFeedBack() {
        response
                .then()
                .body(matchesJsonSchemaInClasspath(FEEDBACK_SCHEMA));
    }

    @Dado("que existam feedbacks")
    public void queExistamFeedBacks() {
        response = when()
                .get(ENDPOINT);
    }

    @Quando("efetuar a busca de todos os feedbacks")
    public void efetuarABuscaDeTodosOsFeedBacks() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(greaterThan(0)));
    }

    @Entao("deve apresentar todos os feedbacks")
    public void deveApresentarTodosOsFeedBacks() {
        response.then()
                .body(matchesJsonSchemaInClasspath(FEEDBACK_LIST_SCHEMA));
    }

    @Quando("efetuar a busca de feedbacks pelo nome do cliente")
    public void efetuarABuscaDeFeedbacksPeloNomeDoCliente() {
        String nomeCliente = "John Doe";
        response = when()
                .get(ENDPOINT.concat("/nome-cliente/{nome}"), nomeCliente);
    }

    @Entao("deve apresentar os feedbacks do cliente solicitado")
    public void deveApresentarOsFeedBacksDoClienteSolicitado() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(greaterThan(0)))
                .body(matchesJsonSchemaInClasspath(FEEDBACK_LIST_SCHEMA));
    }

    @Quando("efetuar a busca de feedbacks pelo id do restaurante")
    public void efetuarABuscaDeFeedBacksPeloIdDoRestaurante() {
        Long restauranteId = 1L;
        response = when()
                .get(ENDPOINT.concat("/restaurante/{id}"), restauranteId);
    }

    @Entao("deve apresentar os feedbacks do restaurante solicitado")
    public void deveApresentarOsFeedBacksDoRestauranteSolicitado() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(greaterThan(0)))
                .body(matchesJsonSchemaInClasspath(FEEDBACK_LIST_SCHEMA));
    }

    @Dado("que desejo deletar um feedback")
    public void queDesejoDeletarUmFeedBack() {
        efetuarABuscaDeFeedbacksPeloNomeDoCliente();
        id = response.then()
                .extract()
                .as(FeedBackResponse.class)
                .id();
    }

    @Quando("o feedback existir")
    public void oFeedBackExistir() {
        assertNotNull(id);
    }

    @Entao("deve deletar o feedback com sucesso")
    public void deveDeletarOFeedBackComSucesso() {
        when()
                .delete(ENDPOINT.concat("/{id}"), id)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
