package com.fiap.restaurant.booking.core.usecases.endereco;

import com.fiap.restaurant.booking.core.domains.Endereco;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.EnderecoGateway;
import com.fiap.restaurant.booking.core.usecases.endereco.impl.FindEnderecoByCepUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.EnderecoDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_ENDERECO_CEP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class FindEnderecoByCepUseCaseTest {
    private FindEnderecoByCepUseCaseImpl findEnderecoByCepUseCase;
    private EnderecoGateway enderecoGateway;

    @BeforeEach
    void init() {
        enderecoGateway = mock(EnderecoGateway.class);
        findEnderecoByCepUseCase = new FindEnderecoByCepUseCaseImpl(
                enderecoGateway
        );
    }

    @Test
    void shouldThrowValidationExceptionWhenCepIsNull() {
        assertThatThrownBy(() -> findEnderecoByCepUseCase.execute(null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Cep cannot be null");

        verifyNoInteractions(enderecoGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenCepIsEmpty() {
        assertThatThrownBy(() -> findEnderecoByCepUseCase.execute(""))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Cep cannot be null");

        verifyNoInteractions(enderecoGateway);
    }

    @Test
    void shouldFindEnderecoByCep() {
        final var cep = DEFAULT_ENDERECO_CEP;
        final List<Endereco> expected = List.of(EnderecoDomainFixture.NOVO());

        when(enderecoGateway.findByCep(cep)).thenReturn(expected);

        final var result = findEnderecoByCepUseCase.execute(cep);

        assertThat(result)
                .isNotNull()
                .hasSize(expected.size())
                .isEqualTo(expected);

        verify(enderecoGateway).findByCep(cep);
    }
}
