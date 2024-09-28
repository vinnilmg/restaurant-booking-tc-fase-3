package com.fiap.restaurant.booking.core.usecases.endereco;

import com.fiap.restaurant.booking.core.domains.Endereco;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.EnderecoGateway;
import com.fiap.restaurant.booking.core.usecases.endereco.impl.FindEnderecoByCidadeUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.EnderecoDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_ENDERECO_CIDADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class FindEnderecoByCidadeUseCaseTest {
    private FindEnderecoByCidadeUseCaseImpl findEnderecoByCidadeUseCase;
    private EnderecoGateway enderecoGateway;

    @BeforeEach
    void init() {
        enderecoGateway = mock(EnderecoGateway.class);
        findEnderecoByCidadeUseCase = new FindEnderecoByCidadeUseCaseImpl(
                enderecoGateway
        );
    }

    @Test
    void shouldThrowValidationExceptionWhenCidadeIsNull() {
        assertThatThrownBy(() -> findEnderecoByCidadeUseCase.execute(null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Cidade cannot be null");

        verifyNoInteractions(enderecoGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenCidadeIsEmpty() {
        assertThatThrownBy(() -> findEnderecoByCidadeUseCase.execute(""))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Cidade cannot be null");

        verifyNoInteractions(enderecoGateway);
    }

    @Test
    void shouldFindEnderecoByCidade() {
        final var cidade = DEFAULT_ENDERECO_CIDADE;
        final List<Endereco> expected = List.of(EnderecoDomainFixture.NOVO());

        when(enderecoGateway.findByCidade(cidade)).thenReturn(expected);

        final var result = findEnderecoByCidadeUseCase.execute(cidade);

        assertThat(result)
                .isNotNull()
                .hasSize(expected.size())
                .isEqualTo(expected);

        verify(enderecoGateway).findByCidade(cidade);
    }
}
