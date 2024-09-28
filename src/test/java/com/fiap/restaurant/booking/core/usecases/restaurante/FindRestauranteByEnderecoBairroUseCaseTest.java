package com.fiap.restaurant.booking.core.usecases.restaurante;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.domains.RestauranteDomain;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.FindRestauranteByEnderecoBairroUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.*;
import static org.mockito.Mockito.*;

public class FindRestauranteByEnderecoBairroUseCaseTest {
    private FindRestauranteByEnderecoBairroUseCaseImpl findRestauranteByEnderecoBairroUseCase;
    private RestauranteGateway restauranteGateway;

    @BeforeEach
    void init() {
        restauranteGateway = mock(RestauranteGateway.class);
        findRestauranteByEnderecoBairroUseCase = new FindRestauranteByEnderecoBairroUseCaseImpl(restauranteGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenBairroIsNull() {
        assertThatThrownBy(() -> findRestauranteByEnderecoBairroUseCase.execute(null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Bairro cannot be null");

        verifyNoInteractions(restauranteGateway);
    }

    @Test
    void shouldFindRestauranteByEnderecoBairro() {
        final var bairro = DEFAULT_ENDERECO_DOMAIN.getBairro();
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

        when(restauranteGateway.findByEnderecoBairro(bairro)).thenReturn(expected);

        final var result = findRestauranteByEnderecoBairroUseCase.execute(bairro);

        assertThat(result)
                .isNotNull()
                .hasSize(expected.size())
                .isEqualTo(expected);

        verify(restauranteGateway).findByEnderecoBairro(bairro);
    }
}
