package com.fiap.restaurant.booking.core.domains;

import com.fiap.restaurant.booking.core.exceptions.ValidationException;

import java.time.LocalDateTime;
import java.util.Objects;

public class FeedBackDomain implements FeedBack {

    private Long id;

    private Restaurante restauranteId;

    private String nomeCliente;

    private Integer avaliacao;

    private String comentario;

    private LocalDateTime dataHoraCriacao;

    public FeedBackDomain(Long id, Restaurante restaurante, String nomeCliente, Integer avaliacao, String comentario, LocalDateTime dataHoraCriacao) {
        validationToCreateInstance(id, restaurante, nomeCliente, avaliacao, dataHoraCriacao);
        this.id = id;
        this.restauranteId = restaurante;
        this.nomeCliente = nomeCliente;
        this.avaliacao = avaliacao;
        this.comentario = comentario;
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public FeedBackDomain(Restaurante restaurante, String nomeCliente, Integer avaliacao, String comentario) {
        validationToCreateInstance(restaurante, nomeCliente, avaliacao);
        this.restauranteId = restaurante;
        this.nomeCliente = nomeCliente;
        this.avaliacao = avaliacao;
        this.comentario = comentario;
    }


    private void validationToCreateInstance(Long id, Restaurante restauranteId, String nomeCliente, Integer avaliacao, LocalDateTime dataHoraCriacao) {
        validateAvaliacao(avaliacao);
        validateNomeCliente(nomeCliente);
        validaDataHoraCriacao(dataHoraCriacao);
        validateIdFeedback(id);
    }

    private void validationToCreateInstance(Restaurante restauranteId, String nomeCliente, Integer avaliacao) {
        validateAvaliacao(avaliacao);
        validateNomeCliente(nomeCliente);
    }

    public void validateAvaliacao(Integer avaliacao) {
        if (Objects.isNull(avaliacao))
            throw ValidationException.of("Nota de avaliação invalida", "Nota de avaliação não pode ser nulo");
        if (avaliacao <= 0 || avaliacao > 5)
            throw ValidationException.of("Nota de avaliação invalida", "Nota de avalialção tem que ser entre 1 ou 5");
    }

    public void validateNomeCliente(String nomeCliente) {
        if (Objects.isNull(nomeCliente) || nomeCliente.isBlank())
            throw ValidationException.of("Nome do cliente inválido", "Nome do cliente não pode ser nulo ou vazio");
    }

    public void validateIdFeedback(Long idFeedback) {
        if (Objects.isNull(idFeedback))
            throw ValidationException.of("id do feedback invalido", "id não pode ser null");
    }

    public void validaDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        if (Objects.isNull(dataHoraCriacao))
            throw ValidationException.of("data hora criação invalido", "data hora criação é nulo ");
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
