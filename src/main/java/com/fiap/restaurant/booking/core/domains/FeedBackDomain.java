package com.fiap.restaurant.booking.core.domains;

import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class FeedBackDomain {

    private Long id;

    private Long restauranteId;

    private String nomeCliente;

    private Integer avaliacao;

    private String comentario;

    private LocalDateTime dataHoraCriacao;

    public FeedBackDomain(Long id, Long restauranteId, String nomeCliente, Integer avaliacao, String comentario) {
        validationToCreateInstance(restauranteId, nomeCliente, avaliacao);
        this.id = id;
        this.restauranteId = restauranteId;
        this.nomeCliente = nomeCliente;
        this.avaliacao = avaliacao;
        this.comentario = comentario;
    }

    private void validationToCreateInstance( Long restauranteId, String nomeCliente, Integer avaliacao) {
        if (Objects.isNull(avaliacao))
            throw ValidationException.of("Nota de avaliação invalida", "Nota de avaliação tem que ser entre 1 ou 5");
        if (avaliacao <= 0 || avaliacao > 5)
            throw ValidationException.of("Nota de avaliação invalida", "Nota de avalialção tem que ser entre 1 ou 5");
        if (Objects.isNull(nomeCliente) || nomeCliente.isBlank())
            throw ValidationException.of("Nome do cliente inválido", "Nome do cliente não pode ser nulo ou vazio");
        if (Objects.isNull(restauranteId))
            throw ValidationException.of("Id do restaurante ausente", "Deve-se informar o Id do restaurante");
    }
}
