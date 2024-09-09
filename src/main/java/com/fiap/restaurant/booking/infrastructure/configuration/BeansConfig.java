package com.fiap.restaurant.booking.infrastructure.configuration;

import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.core.usecases.CreateReservaUseCase;
import com.fiap.restaurant.booking.core.usecases.FindReservaByCpfUseCase;
import com.fiap.restaurant.booking.core.usecases.GetAllReservasUseCase;
import com.fiap.restaurant.booking.core.usecases.impl.CreateReservaUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.impl.FindReservaByCpfUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.impl.GetAllReservasUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public CreateReservaUseCase createReservaUseCase(ReservaGateway reservaGateway) {
        return new CreateReservaUseCaseImpl(reservaGateway);
    }

    @Bean
    public GetAllReservasUseCase getAllReservasUseCase(ReservaGateway reservaGateway) {
        return new GetAllReservasUseCaseImpl(reservaGateway);
    }

    @Bean
    public FindReservaByCpfUseCase findReservaByCpfUseCase(ReservaGateway reservaGateway) {
        return new FindReservaByCpfUseCaseImpl(reservaGateway);
    }
}
