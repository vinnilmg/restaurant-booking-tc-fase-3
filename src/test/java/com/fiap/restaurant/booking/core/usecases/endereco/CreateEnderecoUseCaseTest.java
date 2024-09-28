package com.fiap.restaurant.booking.core.usecases.endereco;

import com.fiap.restaurant.booking.core.gateways.EnderecoGateway;
import com.fiap.restaurant.booking.core.usecases.endereco.impl.CreateEnderecoUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.EnderecoDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateEnderecoUseCaseTest {
    private CreateEnderecoUseCaseImpl createEnderecoUseCase;
    private EnderecoGateway enderecoGateway;

    @BeforeEach
    void init() {
        enderecoGateway = mock(EnderecoGateway.class);
        createEnderecoUseCase = new CreateEnderecoUseCaseImpl(
                enderecoGateway
        );
    }

    @Test
    void shouldCreateEndereco() {
        final var endereco = EnderecoDomainFixture.NOVO();

        when(enderecoGateway.create(endereco))
                .thenAnswer(i -> i.getArguments()[0]);

        final var result = createEnderecoUseCase.execute(endereco);

        assertThat(result)
                .isNotNull()
                .isEqualTo(endereco);

        verify(enderecoGateway).create(endereco);
    }
}
