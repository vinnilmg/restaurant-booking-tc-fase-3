package com.fiap.restaurant.booking.infrastructure.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
@Table(name = "reserva")
public class ReservaEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String cpfCliente;

    @NotNull
    private LocalDateTime dataHoraReserva;

    private String status;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime dataHoraCriacao;

    // TODO: Id da mesa (nao implementado)
}
