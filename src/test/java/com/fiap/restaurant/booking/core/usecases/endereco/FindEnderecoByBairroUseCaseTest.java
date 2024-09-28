package com.fiap.restaurant.booking.core.usecases.endereco;

import com.fiap.restaurant.booking.core.domains.Endereco;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.EnderecoGateway;
import com.fiap.restaurant.booking.core.usecases.endereco.impl.FindEnderecoByBairroUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.EnderecoDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_ENDERECO_BAIRRO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class FindEnderecoByBairroUseCaseTest {
    private FindEnderecoByBairroUseCaseImpl findEnderecoByBairroUseCase;
    private EnderecoGateway enderecoGateway;

    @BeforeEach
    void init() {
        enderecoGateway = mock(EnderecoGateway.class);
        findEnderecoByBairroUseCase = new FindEnderecoByBairroUseCaseImpl(
                enderecoGateway
        );
    }

    @Test
    void shouldThrowValidationExceptionWhenBairroIsNull() {
        assertThatThrownBy(() -> findEnderecoByBairroUseCase.execute(null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Bairro cannot be null");

        verifyNoInteractions(enderecoGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenBairroIsEmpty() {
        assertThatThrownBy(() -> findEnderecoByBairroUseCase.execute(""))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Bairro cannot be null");

        verifyNoInteractions(enderecoGateway);
    }

    @Test
    void shouldFindEnderecoByBairro() {
        final var bairro = DEFAULT_ENDERECO_BAIRRO;
        final List<Endereco> expected = List.of(EnderecoDomainFixture.NOVO());

        when(enderecoGateway.findByBairro(bairro)).thenReturn(expected);

        final var result = findEnderecoByBairroUseCase.execute(bairro);

        assertThat(result)
                .isNotNull()
                .hasSize(expected.size())
                .isEqualTo(expected);

        verify(enderecoGateway).findByBairro(bairro);
    }
}
