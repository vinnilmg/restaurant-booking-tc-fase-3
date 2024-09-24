package com.fiap.restaurant.booking.infrastructure.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;

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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private EnderecoEntity endereco;

    @NotNull
    private String tipoCulinaria;

    @NotNull
    private LocalTime inicioFuncionamento;

    @NotNull
    private LocalTime fimFuncionamento;

    @NotNull
    private Integer capacidade;

    @NotNull
    private Double mediaFeedback;

}
