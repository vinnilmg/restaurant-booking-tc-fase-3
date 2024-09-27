package com.fiap.restaurant.booking.infrastructure.persistence.entities;

import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "mesa")
public class MesaEntity {

    @Id
    @GeneratedValue
    private Long id;

    private int numeroDaMesa;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status não pode ser nulo")
    private StatusMesaEnum status;
    @ManyToOne
    private RestauranteEntity restaurante;
}
