package com.fiap.restaurant.booking.core.usecases.restaurante;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.domains.RestauranteDomain;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.FindRestauranteByEnderecoCidadeUseCaseImpl;
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
import static org.mockito.Mockito.when;

class FindRestauranteByEnderecoCidadeUseCaseTest {
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
        final var cidade = EnderecoDomainFixture.OTHER().getCidade();
        final var restaurant = new RestauranteDomain(
                DEFAULT_NOME,
                DEFAULT_CNPJ,
                EnderecoDomainFixture.OTHER(),
                DEFAULT_TIPO_CULINARIA,
                DEFAULT_TIME,
                DEFAULT_TIME,
                50,
                5.0);

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
