package com.fiap.restaurant.booking.core.domains;

import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static com.fiap.restaurant.booking.utils.DateTimeUtils.toLocalTime;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RestauranteDomainTest {

    @Test
    void shouldThrowValidationExceptionWhenNomeIsNull() {
        assertThatThrownBy(() -> new RestauranteDomain(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Restaurante Nome cannot be null");
    }

    @Test
    void shouldThrowValidationExceptionWhenCNPJIsNull() {
        assertThatThrownBy(() -> new RestauranteDomain(
                DEFAULT_NOME,
                null,
                null,
                null,
                null,
                null,
                null,
                null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Restaurante CNPJ cannot be null");
    }

    @Test
    void shouldThrowValidationExceptionWhenCNPJDoesNotContain14Positions() {
        assertThatThrownBy(() -> new RestauranteDomain(
                DEFAULT_NOME,
                "12345678910",
                null,
                null,
                null,
                null,
                null,
                null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Restaurante CNPJ must be 14 positions");
    }

    @Test
    void shouldThrowValidationExceptionWhenEnderecoIsNull() {
            assertThatThrownBy(() -> new RestauranteDomain(
                    DEFAULT_NOME,
                    DEFAULT_CNPJ,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null))
                    .isInstanceOf(ValidationException.class)
                    .hasMessage("Restaurante Endereco cannot be null");
    }

    @Test
    void shouldThrowValidationExceptionWhenTipoCulinariaIsNull() {
        assertThatThrownBy(() -> new RestauranteDomain(
                DEFAULT_NOME,
                DEFAULT_CNPJ,
                DEFAULT_ENDERECO_DOMAIN,
                null,
                null,
                null,
                null,
                null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Tipo Culinária cannot be null");
    }

    @Test
    void shouldThrowValidationExceptionWhenTipoCulinariaIsNotAccepted() {
        assertThatThrownBy(() -> new RestauranteDomain(
                DEFAULT_NOME,
                DEFAULT_CNPJ,
                DEFAULT_ENDERECO_DOMAIN,
                "whatever",
                null,
                null,
                null,
                null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Tipo Culinaria is invalid");
    }

    @Test
    void shouldThrowValidationExceptionWhenInicioFuncionamentoIsNull() {
        assertThatThrownBy(() -> new RestauranteDomain(
                DEFAULT_NOME,
                DEFAULT_CNPJ,
                DEFAULT_ENDERECO_DOMAIN,
                DEFAULT_TIPO_CULINARIA,
                null,
                null,
                null,
                null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Inicio Funcionamento do Restaurante cannot be null");
    }

    @Test
    void shouldThrowValidationExceptionWhenFimFuncionamentoIsNull() {
        assertThatThrownBy(() -> new RestauranteDomain(
                DEFAULT_NOME,
                DEFAULT_CNPJ,
                DEFAULT_ENDERECO_DOMAIN,
                DEFAULT_TIPO_CULINARIA,
                DEFAULT_TIME,
                null,
                null,
                null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Fim Funcionamento do Restaurante cannot be null");
    }

    @Test
    void shouldThrowValidationExceptionWhenIdIsNull() {
        assertThatThrownBy(() -> new RestauranteDomain(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Restaurante Id cannot be null");
    }

    @Test
    void shouldThrowValidationExceptionWhenIdIsNegative() {
        assertThatThrownBy(() -> new RestauranteDomain(
                -1L,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Restaurante Id cannot be negative");
    }

    @Test
    void shouldConstructRestauranteDomain() {
        final var nome = DEFAULT_NOME;
        final var cnpj = DEFAULT_CNPJ;
        final var endereco = DEFAULT_ENDERECO_DOMAIN;
        final var tipoCulinaria = DEFAULT_TIPO_CULINARIA;
        final var inicioFuncionameto = DEFAULT_TIME;
        final var fimFuncionamento = DEFAULT_TIME;
        final var capacidade = 50;
        final var mediaFeedback = 5.0;

        final var result = new RestauranteDomain(
                nome,
                cnpj,
                endereco,
                tipoCulinaria,
                inicioFuncionameto,
                fimFuncionamento,
                capacidade,
                mediaFeedback
        );

        assertThat(result)
                .isNotNull()
                .isInstanceOf(Restaurante.class)
                .extracting(
                        RestauranteDomain::getNome,
                        RestauranteDomain::getCnpj,
                        RestauranteDomain::getEndereco,
                        RestauranteDomain::getTipoCulinaria,
                        RestauranteDomain::getInicioFuncionamento,
                        RestauranteDomain::getFimFuncionamento,
                        RestauranteDomain::getCapacidade,
                        RestauranteDomain::getMediaFeedback)
                .containsExactly(
                        nome,
                        cnpj,
                        endereco,
                        tipoCulinaria,
                        toLocalTime(inicioFuncionameto),
                        toLocalTime(fimFuncionamento),
                        capacidade,
                        mediaFeedback);

        assertThat(result)
                .extracting(RestauranteDomain::getId)
                .isNull();

    }

    @Test
    void shouldConstructRestauranteDomainWithId() {
        final var id = 1L;
        final var nome = DEFAULT_NOME;
        final var cnpj = DEFAULT_CNPJ;
        final var endereco = DEFAULT_ENDERECO_DOMAIN;
        final var tipoCulinaria = DEFAULT_TIPO_CULINARIA;
        final var inicioFuncionameto = LocalTime.of(11,0);
        final var fimFuncionamento = LocalTime.of(20,0);
        final var capacidade = 50;
        final var mediaFeedback = 5.0;

        final var result = new RestauranteDomain(
                1L,
                nome,
                cnpj,
                endereco,
                tipoCulinaria,
                inicioFuncionameto,
                fimFuncionamento,
                capacidade,
                mediaFeedback
        );

        assertThat(result)
                .isNotNull()
                .isInstanceOf(Restaurante.class)
                .extracting(
                        RestauranteDomain::getId,
                        RestauranteDomain::getNome,
                        RestauranteDomain::getCnpj,
                        RestauranteDomain::getEndereco,
                        RestauranteDomain::getTipoCulinaria,
                        RestauranteDomain::getInicioFuncionamento,
                        RestauranteDomain::getFimFuncionamento,
                        RestauranteDomain::getCapacidade,
                        RestauranteDomain::getMediaFeedback)
                .containsExactly(
                        id,
                        nome,
                        cnpj,
                        endereco,
                        tipoCulinaria,
                        inicioFuncionameto,
                        fimFuncionamento,
                        capacidade,
                        mediaFeedback);

    }

}
