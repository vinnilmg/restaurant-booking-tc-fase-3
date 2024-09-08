package com.fiap.restaurant.booking.core.domains;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class FeedBack {

    private Long id;

    private Long restauranteId;

    private String nomeCliente;

    private Integer avaliacao;

    private String comentario;

    private LocalDateTime dataHoraCriacao;

    public FeedBack(Long id, Long restauranteId, String nomeCliente, Integer avaliacao, String comentario) {
        this.id = id;
        this.restauranteId = restauranteId;
        this.nomeCliente = nomeCliente;
        this.avaliacao = avaliacao;
        this.comentario = comentario;
        validationToCreateInstance();

    }



    private void validationToCreateInstance() {
        if(Objects.isNull(this.avaliacao)) {
            throw new IllegalArgumentException("Campo avaliação não pode ser nulo");
        } else if(this.avaliacao <= 0 || this.avaliacao > 5) {
            throw new IllegalArgumentException("Nota de avalialção tem que ser entre 1 ou 5");
        }

        if(Objects.isNull(this.nomeCliente) || this.nomeCliente.isBlank()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser nulo ou vazio");
        }

        if(Objects.isNull(this.restauranteId)) {
            throw new IllegalArgumentException("Deve-se informar o Id do restaurante");
        }
    }
}
