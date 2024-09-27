package com.fiap.restaurant.booking.core.usecases.restaurante;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.domains.RestauranteDomain;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.FindRestauranteByEnderecoRuaUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.*;
import static org.mockito.Mockito.*;

public class FindRestauranteByEnderecoRuaUseCaseTest {
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
        final var rua = DEFAULT_ENDERECO_DOMAIN.getRua();
        final var restaurante = new RestauranteDomain(
                DEFAULT_NOME,
                DEFAULT_CNPJ,
                DEFAULT_ENDERECO_DOMAIN,
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
