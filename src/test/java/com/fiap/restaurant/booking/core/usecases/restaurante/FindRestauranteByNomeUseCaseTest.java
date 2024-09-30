package com.fiap.restaurant.booking.core.usecases.restaurante;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.FindRestauranteByNomeUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.RestauranteDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.*;
import static org.mockito.Mockito.*;

class FindRestauranteByNomeUseCaseTest {
    private FindRestauranteByNomeUseCaseImpl findRestauranteByNomeUseCase;
    private RestauranteGateway restauranteGateway;

    @BeforeEach
    void init() {
        restauranteGateway = mock(RestauranteGateway.class);
        findRestauranteByNomeUseCase = new FindRestauranteByNomeUseCaseImpl(restauranteGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenNomeIsNull() {
        assertThatThrownBy(() -> findRestauranteByNomeUseCase.execute(null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Nome cannot be null");

        verifyNoInteractions(restauranteGateway);
    }

    @Test
    void shouldFindRestauranteByNome() {
        final var nome = DEFAULT_NOME;
        final var restaurante = RestauranteDomainFixture.NOVO();

        final List<Restaurante> expected = List.of(restaurante);

        when(restauranteGateway.findByNome(nome)).thenReturn(expected);

        final var result = findRestauranteByNomeUseCase.execute(nome);

        assertThat(result)
                .isNotNull()
                .hasSize(expected.size())
                .isEqualTo(expected);

        verify(restauranteGateway).findByNome(nome);
    }
}
