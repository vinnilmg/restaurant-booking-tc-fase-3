package com.fiap.restaurant.booking.core.usecases.restaurante;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.domains.RestauranteDomain;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.FindRestauranteByEnderecoRuaUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.EnderecoDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_CNPJ;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_NOME;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_TIME;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_TIPO_CULINARIA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class FindRestauranteByEnderecoRuaUseCaseTest {
    private FindRestauranteByEnderecoRuaUseCaseImpl findRestauranteByEnderecoRuaUseCase;
    private RestauranteGateway restauranteGateway;

    @BeforeEach
    void init() {
        restauranteGateway = mock(RestauranteGateway.class);
        findRestauranteByEnderecoRuaUseCase = new FindRestauranteByEnderecoRuaUseCaseImpl(restauranteGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenRuaIsNull() {
        assertThatThrownBy(() -> findRestauranteByEnderecoRuaUseCase.execute(null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Rua cannot be null");

        verifyNoInteractions(restauranteGateway);
    }

    @Test
    void shouldFindRestauranteByEnderecoRua() {
        final var rua = EnderecoDomainFixture.OTHER().getRua();
        final var restaurante = new RestauranteDomain(
                DEFAULT_NOME,
                DEFAULT_CNPJ,
                EnderecoDomainFixture.OTHER(),
                DEFAULT_TIPO_CULINARIA,
                DEFAULT_TIME,
                DEFAULT_TIME,
                50,
                5.0);

        final List<Restaurante> expected = List.of(restaurante);

        when(restauranteGateway.findByEnderecoRua(rua)).thenReturn(expected);

        final var result = findRestauranteByEnderecoRuaUseCase.execute(rua);

        assertThat(result)
                .isNotNull()
                .hasSize(expected.size())
                .isEqualTo(expected);

        verify(restauranteGateway).findByEnderecoRua(rua);
    }
}
