package com.fiap.restaurant.booking.core.usecases.restaurante;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.FindRestauranteByEnderecoCidadeUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.RestauranteDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.*;
import static org.mockito.Mockito.*;

public class FindRestauranteByEnderecoCidadeUseCaseTest {
    private FindRestauranteByEnderecoCidadeUseCaseImpl findRestauranteByEnderecoCidadeUseCase;
    private RestauranteGateway restauranteGateway;

    @BeforeEach
    void init() {
        restauranteGateway = mock(RestauranteGateway.class);
        findRestauranteByEnderecoCidadeUseCase = new FindRestauranteByEnderecoCidadeUseCaseImpl(restauranteGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenCidadeIsNull() {
        assertThatThrownBy(() -> findRestauranteByEnderecoCidadeUseCase.execute(null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Cidade cannot be null");
    }

    @Test
    void shouldFindRestaurantByEnderecoCidade() {
        final var cidade = DEFAULT_ENDERECO_DOMAIN.getCidade();
        final var restaurant = RestauranteDomainFixture.NOVO();

        final List<Restaurante> expected = List.of(restaurant);

        when(restauranteGateway.findByEnderecoCidade(cidade)).thenReturn(expected);

        final var result = findRestauranteByEnderecoCidadeUseCase.execute(cidade);

        assertThat(result)
                .isNotNull()
                .hasSize(expected.size())
                .isEqualTo(expected);

        verify(restauranteGateway).findByEnderecoCidade(cidade);
    }
}
