package com.fiap.restaurant.booking.core.usecases.endereco;

import com.fiap.restaurant.booking.core.domains.Endereco;
import com.fiap.restaurant.booking.core.gateways.EnderecoGateway;
import com.fiap.restaurant.booking.core.usecases.endereco.impl.GetAllEnderecosUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.EnderecoDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetAllEnderecosUseCaseTest {
    private GetAllEnderecosUseCaseImpl getAllEnderecosUseCase;
    private EnderecoGateway enderecoGateway;

    @BeforeEach
    void init() {
        enderecoGateway = mock(EnderecoGateway.class);
        getAllEnderecosUseCase = new GetAllEnderecosUseCaseImpl(
                enderecoGateway
        );
    }

    @Test
    void shouldGetAllEnderecos() {
        final List<Endereco> enderecos = List.of(
                EnderecoDomainFixture.NOVO()
        );

        when(enderecoGateway.getAll()).thenReturn(enderecos);

        final var result = getAllEnderecosUseCase.execute();

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(enderecos.size());

        verify(enderecoGateway).getAll();
    }
}
