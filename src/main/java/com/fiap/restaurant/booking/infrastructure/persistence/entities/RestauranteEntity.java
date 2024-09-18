package com.fiap.restaurant.booking.infrastructure.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "restaurante")
public class RestauranteEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String cnpj;

    @NotNull
    private String tipoCulinaria;

    @NotNull
    private LocalDateTime inicioFuncionamento;

    @NotNull
    private LocalDateTime fimFuncionamento;

    @NotNull
    private Integer capacidade;

    @NotNull
    private Double mediaFeedback;

    //TODO: endereço (nao implementado)

}
