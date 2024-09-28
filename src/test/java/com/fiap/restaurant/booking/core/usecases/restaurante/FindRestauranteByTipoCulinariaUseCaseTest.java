package com.fiap.restaurant.booking.core.usecases.restaurante;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.domains.RestauranteDomain;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.FindRestauranteByTipoCulinariaUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.*;
import static org.mockito.Mockito.*;

public class FindRestauranteByTipoCulinariaUseCaseTest {
    private FindRestauranteByTipoCulinariaUseCaseImpl findRestauranteByTipoCulinariaUseCase;
    private RestauranteGateway restauranteGateway;

    @BeforeEach
    void init() {
        restauranteGateway = mock(RestauranteGateway.class);
        findRestauranteByTipoCulinariaUseCase = new FindRestauranteByTipoCulinariaUseCaseImpl(restauranteGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenTipoCulinariaIsNull() {
        assertThatThrownBy(() -> findRestauranteByTipoCulinariaUseCase.execute(null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Tipo Culinária cannot be null");

        verifyNoInteractions(restauranteGateway);
    }

    @Test
    void shouldFindRestauranteByTipoCulinaria() {
        final var tipoCulinaria = DEFAULT_TIPO_CULINARIA;
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

        when(restauranteGateway.findByTipoCulinaria(tipoCulinaria)).thenReturn(expected);

        final var result = findRestauranteByTipoCulinariaUseCase.execute(tipoCulinaria);

        assertThat(result)
                .isNotNull()
                .hasSize(expected.size())
                .isEqualTo(expected);

        verify(restauranteGateway).findByTipoCulinaria(tipoCulinaria);
    }
}
