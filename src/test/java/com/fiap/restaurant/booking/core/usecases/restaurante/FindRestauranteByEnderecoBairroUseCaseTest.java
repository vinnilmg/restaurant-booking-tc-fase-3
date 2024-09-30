package com.fiap.restaurant.booking.core.usecases.restaurante;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.FindRestauranteByEnderecoBairroUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.RestauranteDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_ENDERECO_DOMAIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

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
        final var restaurante = RestauranteDomainFixture.NOVO();

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
