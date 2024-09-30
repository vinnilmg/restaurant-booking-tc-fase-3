package com.fiap.restaurant.booking.bdd;

import com.fiap.restaurant.booking.infrastructure.controllers.request.RestauranteRequest;
import com.fiap.restaurant.booking.utils.fixture.RestauranteRequestFixture;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.fiap.restaurant.booking.utils.SchemaDefinitionConstants.RESTAURANTE_LIST_SCHEMA;
import static com.fiap.restaurant.booking.utils.SchemaDefinitionConstants.RESTAURANTE_SCHEMA;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;


public class RestauranteStepDefinition {
    private static final String ENDPOINT = "http://localhost:8090/api/restaurants";
    private RestauranteRequest request;
    private Long id;
    private Response response;


    @Dado("Que desejo cadastrar um restaurante")
    public void que_desejo_cadastrar_um_restaurante() {
        request = RestauranteRequestFixture.FULL();
    }

    @Quando("o cadastro for submetido")
    public void o_cadastro_for_submetido() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post(ENDPOINT);
    }

    @Entao("deve criar o restaurante com sucesso")
    public void deve_criar_o_restaurante_com_sucesso() {
        response
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @E("apresentar o restaurante")
    public void apresentar_o_restaurante() {
        response
                .then()
                .body(matchesJsonSchemaInClasspath(RESTAURANTE_SCHEMA));
    }

    @Dado("que existam restaurantes")
    public void que_existam_restaurantes() {
        response = when()
                .get(ENDPOINT);
    }

    @Quando("efetuar a busca de todos os restaurantes")
    public void efetuar_a_busca_de_todos_os_restaurantes() {
        response
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(greaterThan(0)));
    }

    @Entao("deve apresentar os restaurantes")
    public void deve_apresentar_todos_os_restaurantes() {
        response
                .then()
                .body(matchesJsonSchemaInClasspath(RESTAURANTE_LIST_SCHEMA));
    }

    @Quando("efetuar a busca de restaurante específico")
    public void efetuar_a_busca_de_restaurante_específico() {
        response = when()
                .get(ENDPOINT.concat("/{id}"), 1);
    }

    @Quando("efetuar a busca de um restaurante pelo nome dele")
    public void efetuar_a_busca_de_um_restaurante_pelo_nome_dele() {
        request = RestauranteRequestFixture.FULL();
        String nome = request.nome();
        response = when()
                .get(ENDPOINT.concat("/name/{name}"), nome);
    }

    @Quando("efetuar a busca de um restaurante pela rua dele")
    public void efetuar_a_busca_de_um_restaurante_pela_rua_dele() {
        request = RestauranteRequestFixture.FULL();
        String rua = request.endereco().rua();
        response = when()
                .get(ENDPOINT.concat("/street/{street}"), rua);
    }

    @Quando("efetuar a busca de um restaurante pelo bairro dele")
    public void efetuar_a_busca_de_um_restaurante_pelo_bairro_dele() {
        request = RestauranteRequestFixture.FULL();
        String bairro = request.endereco().bairro();
        response = when()
                .get(ENDPOINT.concat("/district/{district}"), bairro);
    }

    @Quando("efetuar a busca de um restaurante pela cidade dele")
    public void efetuar_a_busca_de_um_restaurante_pela_cidade_dele() {
        request = RestauranteRequestFixture.FULL();
        String cidade = request.endereco().cidade();
        response = when()
                .get(ENDPOINT.concat("/city/{city}"), cidade);
    }

    @Quando("efetuar a busca de um restaurante pela média de feedback dele")
    public void efetuar_a_busca_de_um_restaurante_pela_media_de_feedback_dele() {
        request = RestauranteRequestFixture.FULL();
        var mediaFeedback = request.mediaFeedback();
        response = when()
                .get(ENDPOINT.concat("/feedback/{feedback}"), mediaFeedback);
    }

    @Quando("efetuar a busca de um restaurante pelo tipo de culinária dele")
    public void efetuar_a_busca_de_um_restaurante_pelo_tipo_de_culinaria_dele() {
        request = RestauranteRequestFixture.FULL();
        String tipoCulinaria = request.tipoCulinaria();
        response = when()
                .get(ENDPOINT.concat("/cuisine/{cuisine}"), tipoCulinaria);
    }
}
