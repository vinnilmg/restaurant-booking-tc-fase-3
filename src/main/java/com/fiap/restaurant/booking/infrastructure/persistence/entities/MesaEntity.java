package com.fiap.restaurant.booking.infrastructure.persistence.entities;

import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "mesa")
public class MesaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer numeroDaMesa;

//    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status não pode ser nulo")
    private String status;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RestauranteEntity restaurante;
}
