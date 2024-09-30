package com.fiap.restaurant.booking.infrastructure.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "restaurante can't be null")
    @ManyToOne
    private RestauranteEntity restaurante;

    @NotNull(message = "nome cliente can't be null")
    private String nomeCliente;

    @NotNull(message = "comentário can't be null")
    private String comentario;

    @NotNull(message = "avaliação can't be null")
    private Integer avaliacao;

    @CreationTimestamp
    private LocalDateTime dataHoraCriacao;

}
