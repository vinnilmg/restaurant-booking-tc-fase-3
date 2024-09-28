package com.fiap.restaurant.booking.core.usecases.endereco;

import com.fiap.restaurant.booking.core.domains.Endereco;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.EnderecoGateway;
import com.fiap.restaurant.booking.core.usecases.endereco.impl.FindEnderecoByRuaUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.EnderecoDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_ENDERECO_RUA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class FindEnderecoByRuaUseCaseTest {
    private FindEnderecoByRuaUseCaseImpl findEnderecoByRuaUseCase;
    private EnderecoGateway enderecoGateway;

    @BeforeEach
    void init() {
        enderecoGateway = mock(EnderecoGateway.class);
        findEnderecoByRuaUseCase = new FindEnderecoByRuaUseCaseImpl(
                enderecoGateway
        );
    }

    @Test
    void shouldThrowValidationExceptionWhenRuaIsNull() {
        assertThatThrownBy(() -> findEnderecoByRuaUseCase.execute(null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Rua cannot be null");

        verifyNoInteractions(enderecoGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenRuaIsEmpty() {
        assertThatThrownBy(() -> findEnderecoByRuaUseCase.execute(""))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Rua cannot be null");

        verifyNoInteractions(enderecoGateway);
    }

    @Test
    void shouldFindEnderecoByRua() {
        final var rua = DEFAULT_ENDERECO_RUA;
        final List<Endereco> expected = List.of(EnderecoDomainFixture.NOVO());

        when(enderecoGateway.findByRua(rua)).thenReturn(expected);

        final var result = findEnderecoByRuaUseCase.execute(rua);

        assertThat(result)
                .isNotNull()
                .hasSize(expected.size())
                .isEqualTo(expected);

        verify(enderecoGateway).findByRua(rua);
    }
}
