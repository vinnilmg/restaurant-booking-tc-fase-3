package com.fiap.restaurant.booking.infrastructure.configuration;

import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.core.usecases.CreateReservaUseCase;
import com.fiap.restaurant.booking.core.usecases.impl.CreateReservaUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public CreateReservaUseCase createReservaUseCase(ReservaGateway reservaGateway) {
        return new CreateReservaUseCaseImpl(reservaGateway);
    }
}
