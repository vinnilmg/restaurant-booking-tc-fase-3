package com.fiap.restaurant.booking.infrastructure.configuration;

import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.*;
import com.fiap.restaurant.booking.core.usecases.feedback.impl.*;
import com.fiap.restaurant.booking.core.usecases.reserva.CancelReservaUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.ConfirmReservaUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.CreateReservaUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.FindCanceledReservasUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.FindConfirmedReservasUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.FindRequestedReservasUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.FindReservaByCpfUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.FindReservaByIdUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.GetAllReservasUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.impl.CancelReservaUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.reserva.impl.ConfirmReservaUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.reserva.impl.CreateReservaUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.reserva.impl.FindCanceledReservasUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.reserva.impl.FindConfirmedReservasUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.reserva.impl.FindRequestedReservasUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.reserva.impl.FindReservaByCpfUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.reserva.impl.FindReservaByIdUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.reserva.impl.GetAllReservasUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public CreateReservaUseCase createReservaUseCase(ReservaGateway reservaGateway, FindReservaByCpfUseCase findReservaByCpfUseCase) {
        return new CreateReservaUseCaseImpl(reservaGateway, findReservaByCpfUseCase);
    }

    @Bean
    public GetAllReservasUseCase getAllReservasUseCase(ReservaGateway reservaGateway) {
        return new GetAllReservasUseCaseImpl(reservaGateway);
    }

    @Bean
    public FindReservaByCpfUseCase findReservaByCpfUseCase(ReservaGateway reservaGateway) {
        return new FindReservaByCpfUseCaseImpl(reservaGateway);
    }

    @Bean
    public FindCanceledReservasUseCase findCanceledReservasUseCase(ReservaGateway reservaGateway) {
        return new FindCanceledReservasUseCaseImpl(reservaGateway);
    }

    @Bean
    public FindRequestedReservasUseCase findRequestedReservasUseCase(ReservaGateway reservaGateway) {
        return new FindRequestedReservasUseCaseImpl(reservaGateway);
    }

    @Bean
    public FindConfirmedReservasUseCase findConfirmedReservasUseCase(ReservaGateway reservaGateway) {
        return new FindConfirmedReservasUseCaseImpl(reservaGateway);
    }

    @Bean
    public FindReservaByIdUseCase findReservaByIdUseCase(ReservaGateway reservaGateway) {
        return new FindReservaByIdUseCaseImpl(reservaGateway);
    }

    @Bean
    public CancelReservaUseCase cancelReservaUseCase(FindReservaByIdUseCase findReservaByIdUseCase, ReservaGateway reservaGateway) {
        return new CancelReservaUseCaseImpl(findReservaByIdUseCase, reservaGateway);
    }

    @Bean
    public ConfirmReservaUseCase confirmReservaUseCase(FindReservaByIdUseCase findReservaByIdUseCase, ReservaGateway reservaGateway) {
        return new ConfirmReservaUseCaseImpl(findReservaByIdUseCase, reservaGateway);
    }

    @Bean
    public CreateFeedBackUseCase createFeedBackUseCase(FeedBackGateway feedBackGateway) {
        return new CreateFeedBackUseCaseImpl(feedBackGateway);
    }

   @Bean
    public FindAllFeedBackUseCase findAllFeedBackUseCase(FeedBackGateway feedBackGateway) {
        return new FindAllFeedBackUseCaseImpl(feedBackGateway);
    }

    @Bean
    public FindAllFeedBackByNomeClienteUseCase findAllFeedBackByNomeClienteUseCase(FeedBackGateway feedBackGateway) {
        return new FindAllFeedBackByNomeClienteUseCaseImpl(feedBackGateway);
    }

    @Bean
    public FindByIdFeedBackUseCase findByIdFeedBackUseCase(FeedBackGateway feedBackGateway) {
        return new FindByIdFeedBackUseCaseImpl(feedBackGateway);
    }

    @Bean
    public DeleteFeedBackUseCase deleteFeedBackUseCase(FindByIdFeedBackUseCase findByIdFeedBackUseCase,FeedBackGateway feedBackGateway) {
        return new DeleteFeedBackUseCaseImpl(feedBackGateway,findByIdFeedBackUseCase);
    }







}