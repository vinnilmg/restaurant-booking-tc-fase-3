package com.fiap.restaurant.booking.core.domains;

import com.fiap.restaurant.booking.core.domains.enums.TipoCulinariaEnum;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.utils.DateTimeUtils;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;

public class RestauranteDomain implements Restaurante {
    private final String cnpj;
    private Long id;
    private String nome;
    private TipoCulinariaEnum tipoCulinaria;
    private LocalDateTime inicioFuncionamento;
    private LocalDateTime fimFuncionamento;
    private Integer capacidade;
    private Double mediaFeedback;

    public RestauranteDomain(final Long id,
                             final String nome,
                             final String cnpj,
                             final String tipoCulinaria,
                             final LocalDateTime inicioFuncionamento,
                             final LocalDateTime fimFuncionamento,
                             final Integer capacidade,
                             final Double mediaFeedback) {
        this.id = idValidation(id);
        this.nome = nomeValidation(nome);
        this.cnpj = cnpfValidation(cnpj);
        this.tipoCulinaria = tipoCulinariaValidation(tipoCulinaria);
        this.inicioFuncionamento = inicioFuncionamento;
        this.fimFuncionamento = fimFuncionamento;
        this.capacidade = capacidadeValidation(capacidade);
        this.mediaFeedback = mediaFeedbackValidation(mediaFeedback);
    }

    public RestauranteDomain(String nome,
                             String cnpj,
                             String tipoCulinaria,
                             String inicioFuncionamento,
                             String fimFuncionamento,
                             Integer capacidade,
                             Double mediaFeedback) {
        this.nome = nomeValidation(nome);
        this.cnpj = cnpfValidation(cnpj);
        this.tipoCulinaria = tipoCulinariaValidation(tipoCulinaria);
        this.inicioFuncionamento = horarioValidation(inicioFuncionamento);
        this.fimFuncionamento = horarioValidation(fimFuncionamento);
        this.capacidade = capacidadeValidation(capacidade);
        this.mediaFeedback = mediaFeedbackValidation(mediaFeedback);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getTipoCulinaria() {
        return tipoCulinaria.name();
    }

    @Override
    public LocalDateTime getInicioFuncionamento() {
        return inicioFuncionamento;
    }

    @Override
    public LocalDateTime getFimFuncionamento() {
        return fimFuncionamento;
    }

    @Override
    public Integer getCapacidade() {
        return capacidade;
    }

    @Override
    public Double getMediaFeedback() {
        return mediaFeedback;
    }

    @Override
    public void updateMedia(final Double mediaFeedback) {
        this.mediaFeedback = mediaFeedback;
    }

    private static Long idValidation(final Long id) {
        if (isNull(id)) throw ValidationException.of("Restaurante Id", "cannot be null");
        if (id < 0) throw ValidationException.of("Restaurante Id", "cannot be negative");
        return id;
    }

    private static String nomeValidation(final String nome) {
        if (isNull(nome)) throw ValidationException.of("Restaurante Nome", "cannot be null");
        return nome;
    }

    private static String cnpfValidation(final String cnpj) {
        if (isNull(cnpj)) throw ValidationException.of("Restaurante CNPJ", "cannot be null");
        if (cnpj.length() < 14) throw ValidationException.of("Restaurante CNPJ", "must be 14 positions");
        return cnpj;
    }

    private static TipoCulinariaEnum tipoCulinariaValidation(final String tipoCulinaria) {
        if (isNull(tipoCulinaria)) throw ValidationException.of("Tipo Culinária", "cannot be null");
        final var tipoCulinariaEnum = TipoCulinariaEnum.toEnum(tipoCulinaria);
        if (tipoCulinariaEnum.isEmpty()) throw ValidationException.of("Tipo Culinaria", "is invalid");
        return tipoCulinariaEnum.get();
    }

    private static LocalDateTime horarioValidation(final String horarioValidation) {
        if (isNull(horarioValidation))
            throw ValidationException.of("Inicio Funcionamento do Restaurante",
                "cannot be null");
        return DateTimeUtils.toLocalDateTime(horarioValidation);
    }

    private static Integer capacidadeValidation(final Integer capacidade) {
        if (isNull(capacidade)) throw ValidationException.of("Restaurante Capacidade", "cannot be null");
        if (capacidade <= 0) throw ValidationException.of("Restaurante Capacidade", "must be greater than 0");
        return capacidade;
    }

    private static Double mediaFeedbackValidation(final Double mediaFeedback) {
        if (isNull(mediaFeedback)) throw ValidationException.of("Média Feedback", "cannot be null");
        if (mediaFeedback < 0) throw ValidationException.of("Média Feedback", "cannot be negative");
        if (mediaFeedback > 5) throw ValidationException.of("Média Feedback", "must be from 1 to 5");
        return mediaFeedback;
    }
}
