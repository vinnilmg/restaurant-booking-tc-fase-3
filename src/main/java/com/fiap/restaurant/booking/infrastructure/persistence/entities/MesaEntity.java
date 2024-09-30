package com.fiap.restaurant.booking.infrastructure.persistence.entities;

import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
