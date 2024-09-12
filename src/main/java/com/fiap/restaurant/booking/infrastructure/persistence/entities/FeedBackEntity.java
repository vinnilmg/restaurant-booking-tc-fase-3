package com.fiap.restaurant.booking.infrastructure.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "feedback")
public class FeedBackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    // TODO: Criar relacionamento com o restaurante
    @NotNull
    private Long restauranteId;

    @NotNull
    private String nomeCliente;

    @NotNull
    private Integer avaliacao;

    @CreationTimestamp
    private LocalDateTime dataHoraCriacao;

}
