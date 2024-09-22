package com.fiap.restaurant.booking.core.domains;

import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@AllArgsConstructor
public class FeedBackDomain implements FeedBack {

    private Long id;

    private Restaurante restauranteId;

    private String nomeCliente;

    private Integer avaliacao;

    private String comentario;

    private LocalDateTime dataHoraCriacao;

    public FeedBackDomain(Long id, Restaurante restauranteId, String nomeCliente, Integer avaliacao, String comentario) {
        validationToCreateInstance(restauranteId, nomeCliente, avaliacao);
        this.id = id;
        this.restauranteId = restauranteId;
        this.nomeCliente = nomeCliente;
        this.avaliacao = avaliacao;
        this.comentario = comentario;
    }

    private void validationToCreateInstance(Restaurante restauranteId, String nomeCliente, Integer avaliacao) {
        if (Objects.isNull(avaliacao))
            throw ValidationException.of("Nota de avaliação invalida", "Nota de avaliação tem que ser entre 1 ou 5");
        if (avaliacao <= 0 || avaliacao > 5)
            throw ValidationException.of("Nota de avaliação invalida", "Nota de avalialção tem que ser entre 1 ou 5");
        if (Objects.isNull(nomeCliente) || nomeCliente.isBlank())
            throw ValidationException.of("Nome do cliente inválido", "Nome do cliente não pode ser nulo ou vazio");
        if (Objects.isNull(restauranteId.getId()))
            throw ValidationException.of("Id do restaurante ausente", "Deve-se informar o Id do restaurante");
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public Restaurante getRestaurante() {
        return this.restauranteId;
    }

    @Override
    public Restaurante setRestaurante(Restaurante restaurante) {
        return this.restauranteId = restaurante;
    }

    @Override
    public String getNomeCliente() {
        return this.nomeCliente;
    }

    @Override
    public Integer getAvaliacao() {
        return this.avaliacao;
    }

    @Override
    public String getComentario() {
        return this.comentario;
    }

    @Override
    public LocalDateTime getDataHoraCriacao() {
        return this.dataHoraCriacao;
    }
}
