package com.fiap.restaurant.booking.infrastructure.persistence.repositories;

import com.fiap.restaurant.booking.infrastructure.persistence.entities.EnderecoEntity;
import com.fiap.restaurant.booking.utils.fixture.EnderecoEntityFixture;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_ENDERECO_ID;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@Transactional
class EnderecoRepositoryIT {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Test
    void shouldCountEnderecos() {
        final var result = enderecoRepository.count();
        assertThat(result)
                .isPositive();
    }

    @Test
    void shouldFindEnderecoById() {
        final var id = DEFAULT_ENDERECO_ID;
        final var result = enderecoRepository.findById(id);

        assertThat(result)
                .isNotNull()
                .isPresent();
    }

    @Test
    void shouldFindEnderecoByRua() {
        final var rua = "Rua numero 1";
        final var result = enderecoRepository.findByRua(rua);

        assertThat(result)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void shouldFindEnderecoByBairro() {
        final var bairro = "Vila Bela";
        final var result = enderecoRepository.findByBairro(bairro);

        assertThat(result)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void shouldFindEnderecoByCidade() {
        final var cidade = "Brusque";
        final var result = enderecoRepository.findByCidade(cidade);

        assertThat(result)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void shouldFindEnderecoByCep() {
        final var cep = "02998050";
        final var result = enderecoRepository.findByCep(cep);

        assertThat(result)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void shouldFindAllEnderecos() {
        final var result = enderecoRepository.findAll();

        assertThat(result)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void shouldSaveEndereco() {
        final var endereco = EnderecoEntityFixture.WITHOUT_ID();
        final var result = enderecoRepository.save(endereco);

        assertThat(result)
                .isNotNull()
                .extracting(
                        EnderecoEntity::getBairro,
                        EnderecoEntity::getCep,
                        EnderecoEntity::getRua,
                        EnderecoEntity::getEstado,
                        EnderecoEntity::getCidade,
                        EnderecoEntity::getBairro,
                        EnderecoEntity::getComplemento
                ).containsExactly(
                        endereco.getBairro(),
                        endereco.getCep(),
                        endereco.getRua(),
                        endereco.getEstado(),
                        endereco.getCidade(),
                        endereco.getBairro(),
                        endereco.getComplemento()
                );

        assertThat(result.getId())
                .isNotNull();
    }
}
