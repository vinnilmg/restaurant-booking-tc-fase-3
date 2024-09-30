package com.fiap.restaurant.booking.core.usecases.restaurante;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.FindRestauranteByCnpjUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.RestauranteDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class FindRestauranteByCnpjUseCaseTest {
    private FindRestauranteByCnpjUseCaseImpl findRestauranteByCnpjUseCase;
    private RestauranteGateway restauranteGateway;

    @BeforeEach
    void init() {
        restauranteGateway = mock(RestauranteGateway.class);
        findRestauranteByCnpjUseCase = new FindRestauranteByCnpjUseCaseImpl(restauranteGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenCnpjIsNull() {
        assertThatThrownBy(() -> findRestauranteByCnpjUseCase.execute(null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Restaurante CNPJ invalid");

        verifyNoInteractions(restauranteGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenCnpjIsEmpty() {
        assertThatThrownBy(() -> findRestauranteByCnpjUseCase.execute(""))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Restaurante CNPJ invalid");
    }

    @Test
    void shouldThrowValidationExceptionWhenCnpjLenghtIsNot14() {
        assertThatThrownBy(() -> findRestauranteByCnpjUseCase.execute("123456"))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Restaurante CNPJ invalid");
    }

    @Test
    void shouldFindRestauranteByCnpj() {
        final var cnpj = DEFAULT_CNPJ;
        final var restaurante = RestauranteDomainFixture.NOVO();

        final Optional<Restaurante> expected = Optional.of(restaurante);

        when(restauranteGateway.findByCnpj(cnpj)).thenReturn(expected);

        final var result = findRestauranteByCnpjUseCase.execute(cnpj);

        assertThat(result)
                .isNotNull()
                .isEqualTo(expected);

        verify(restauranteGateway).findByCnpj(cnpj);

    }
}
