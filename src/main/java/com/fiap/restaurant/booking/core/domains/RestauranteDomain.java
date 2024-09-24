package com.fiap.restaurant.booking.core.domains;

import com.fiap.restaurant.booking.core.domains.enums.TipoCulinariaEnum;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.utils.DateTimeUtils;

import java.time.LocalTime;

import static java.util.Objects.isNull;

public class RestauranteDomain implements Restaurante {
    private final String cnpj;
    private Long id;
    private String nome;
    private Endereco endereco;
    private TipoCulinariaEnum tipoCulinaria;
    private LocalTime inicioFuncionamento;
    private LocalTime fimFuncionamento;
    private Integer capacidade;
    private Double mediaFeedback;

    public RestauranteDomain(final Long id,
                             final String nome,
                             final String cnpj,
                             final Endereco endereco,
                             final String tipoCulinaria,
                             final LocalTime inicioFuncionamento,
                             final LocalTime fimFuncionamento,
                             final Integer capacidade,
                             final Double mediaFeedback) {
        this.id = idValidation(id);
        this.nome = nomeValidation(nome);
        this.cnpj = cnpjValidation(cnpj);
        this.endereco = enderecoValidation(endereco);
        this.tipoCulinaria = tipoCulinariaValidation(tipoCulinaria);
        this.inicioFuncionamento = inicioFuncionamento;
        this.fimFuncionamento = fimFuncionamento;
        this.capacidade = capacidadeValidation(capacidade);
        this.mediaFeedback = mediaFeedbackValidation(mediaFeedback);
    }

    public RestauranteDomain(String nome,
                             String cnpj,
                             Endereco endereco,
                             String tipoCulinaria,
                             String inicioFuncionamento,
                             String fimFuncionamento,
                             Integer capacidade,
                             Double mediaFeedback) {
        this.nome = nomeValidation(nome);
        this.cnpj = cnpjValidation(cnpj);
        this.endereco = enderecoValidation(endereco);
        this.tipoCulinaria = tipoCulinariaValidation(tipoCulinaria);
        this.inicioFuncionamento = inicioHorarioValidation(inicioFuncionamento);
        this.fimFuncionamento = fimHorarioValidation(fimFuncionamento);
        this.capacidade = capacidadeValidation(capacidade);
        this.mediaFeedback = mediaFeedbackValidation(mediaFeedback);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getCnpj() {
        return cnpj;
    }

    @Override
    public Endereco getEndereco() {
        return endereco;
    }

    @Override
    public String getTipoCulinaria() {
        return tipoCulinaria.name();
    }

    @Override
    public LocalTime getInicioFuncionamento() {
        return inicioFuncionamento;
    }

    @Override
    public LocalTime getFimFuncionamento() {
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


    private static Long idValidation(final Long id) {
        if (isNull(id)) throw ValidationException.of("Restaurante Id", "cannot be null");
        if (id < 0) throw ValidationException.of("Restaurante Id", "cannot be negative");
        return id;
    }

    private static String nomeValidation(final String nome) {
        if (isNull(nome)) throw ValidationException.of("Restaurante Nome", "cannot be null");
        return nome;
    }

    private static String cnpjValidation(final String cnpj) {
        if (isNull(cnpj)) throw ValidationException.of("Restaurante CNPJ", "cannot be null");
        if (cnpj.length() < 14) throw ValidationException.of("Restaurante CNPJ", "must be 14 positions");
        return cnpj;
    }

    private static Endereco enderecoValidation(final Endereco endereco) {
        if (isNull(endereco)) throw ValidationException.of("Restaurante Endereco", "cannot be null");
        return endereco;
    }

    private static TipoCulinariaEnum tipoCulinariaValidation(final String tipoCulinaria) {
        if (isNull(tipoCulinaria)) throw ValidationException.of("Tipo Culinária", "cannot be null");
        final var tipoCulinariaEnum = TipoCulinariaEnum.toEnum(tipoCulinaria);
        if (tipoCulinariaEnum.isEmpty()) throw ValidationException.of("Tipo Culinaria", "is invalid");
        return tipoCulinariaEnum.get();
    }

    private static LocalTime inicioHorarioValidation(final String inicioFuncionamento) {
        if (isNull(inicioFuncionamento))
            throw ValidationException.of("Inicio Funcionamento do Restaurante",
                "cannot be null");
        return DateTimeUtils.toLocalTime(inicioFuncionamento);
    }

    private static LocalTime fimHorarioValidation(final String fimFuncionamento) {
        if (isNull(fimFuncionamento))
            throw ValidationException.of("Fim Funcionamento do Restaurante",
                    "cannot be null");
        return DateTimeUtils.toLocalTime(fimFuncionamento);
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
