package com.fiap.restaurant.booking.core.usecases.endereco;

import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.EnderecoGateway;
import com.fiap.restaurant.booking.core.usecases.endereco.impl.FindEnderecoByIdUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.EnderecoDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_ENDERECO_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class FindEnderecoByIdUseCaseTest {
    private FindEnderecoByIdUseCaseImpl findEnderecoByIdUseCase;
    private EnderecoGateway enderecoGateway;

    @BeforeEach
    void init() {
        enderecoGateway = mock(EnderecoGateway.class);
        findEnderecoByIdUseCase = new FindEnderecoByIdUseCaseImpl(
                enderecoGateway
        );
    }

    @Test
    void shouldThrowValidationExceptionWhenIdIsNull() {
        assertThatThrownBy(() -> findEnderecoByIdUseCase.execute(null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Endereço ID cannot be null or negative");

        verifyNoInteractions(enderecoGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenIdIsNegative() {
        assertThatThrownBy(() -> findEnderecoByIdUseCase.execute(-1L))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Endereço ID cannot be null or negative");

        verifyNoInteractions(enderecoGateway);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenEnderecoIsNotFound() {
        final var id = DEFAULT_ENDERECO_ID;

        when(enderecoGateway.findByid(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> findEnderecoByIdUseCase.execute(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Endereço not found");

        verify(enderecoGateway).findByid(id);
    }

    @Test
    void shouldFindEnderecoById() {
        final var id = DEFAULT_ENDERECO_ID;
        final var expected = EnderecoDomainFixture.NOVO();

        when(enderecoGateway.findByid(id)).thenReturn(Optional.of(expected));

        final var result = findEnderecoByIdUseCase.execute(id);

        assertThat(result)
                .isNotNull()
                .isEqualTo(expected);

        verify(enderecoGateway).findByid(id);
    }
}
