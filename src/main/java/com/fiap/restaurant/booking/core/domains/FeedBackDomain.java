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

    public static FeedBackDomain createInstanceRequestValidation(Long idRestaurante,String nomeCliente, Integer avaliacao, String comentario) {
        validationFromRequest(idRestaurante,nomeCliente, avaliacao);
     return new FeedBackDomain(nomeCliente, avaliacao, comentario);
    }
    private FeedBackDomain(String nomeCliente, Integer avaliacao, String comentario) {
        this.nomeCliente = nomeCliente;
        this.avaliacao = avaliacao;
        this.comentario = comentario;
    }
    public FeedBackDomain(Long id, Restaurante restaurante, String nomeCliente, Integer avaliacao, String comentario, LocalDateTime dataHoraCriacao) {
        validationToCreateInstance(restaurante, nomeCliente, avaliacao);
        this.id = id;
        this.restauranteId = restaurante;
        this.nomeCliente = nomeCliente;
        this.avaliacao = avaliacao;
        this.comentario = comentario;
        this.dataHoraCriacao = dataHoraCriacao;
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
        if (restauranteId.getId()<1)
            throw ValidationException.of("Id do restaurante inválido", "id restaurante deve ser maior que zero");
    }
    private static void validationFromRequest(Long restauranteId, String nomeCliente, Integer avaliacao) {
        if (Objects.isNull(avaliacao))
            throw ValidationException.of("Nota de avaliação invalida", "Nota de avaliação tem que ser entre 1 ou 5");
        if (avaliacao <= 0 || avaliacao > 5)
            throw ValidationException.of("Nota de avaliação invalida", "Nota de avalialção tem que ser entre 1 ou 5");
        if (Objects.isNull(nomeCliente) || nomeCliente.isBlank())
            throw ValidationException.of("Nome do cliente inválido", "Nome do cliente não pode ser nulo ou vazio");
        if (Objects.isNull(restauranteId))
            throw ValidationException.of("Id do restaurante ausente", "Deve-se informar o Id do restaurante");
        if (restauranteId<1)
            throw ValidationException.of("Id do restaurante inválido", "id restaurante deve ser maior que zero");
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
