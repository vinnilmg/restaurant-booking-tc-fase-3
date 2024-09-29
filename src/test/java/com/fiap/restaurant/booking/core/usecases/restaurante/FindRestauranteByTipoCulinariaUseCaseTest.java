package com.fiap.restaurant.booking.core.usecases.restaurante;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.FindRestauranteByTipoCulinariaUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.RestauranteDomainFixture;
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
        final var restaurante = RestauranteDomainFixture.NOVO();

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
