package com.fiap.restaurant.booking.infrastructure.configuration;

import com.fiap.restaurant.booking.core.gateways.EnderecoGateway;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.gateways.MesaGateway;
import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.endereco.AtualizaEnderecoUseCase;
import com.fiap.restaurant.booking.core.usecases.endereco.CreateEnderecoUseCase;
import com.fiap.restaurant.booking.core.usecases.endereco.DeleteEnderecoUseCase;
import com.fiap.restaurant.booking.core.usecases.endereco.FindEnderecoByBairroUseCase;
import com.fiap.restaurant.booking.core.usecases.endereco.FindEnderecoByCepUseCase;
import com.fiap.restaurant.booking.core.usecases.endereco.FindEnderecoByCidadeUseCase;
import com.fiap.restaurant.booking.core.usecases.endereco.FindEnderecoByIdUseCase;
import com.fiap.restaurant.booking.core.usecases.endereco.FindEnderecoByRuaUseCase;
import com.fiap.restaurant.booking.core.usecases.endereco.GetAllEnderecosUseCase;
import com.fiap.restaurant.booking.core.usecases.endereco.impl.AtualizaEnderecoUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.endereco.impl.CreateEnderecoUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.endereco.impl.DeleteEnderecoUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.endereco.impl.FindEnderecoByBairroUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.endereco.impl.FindEnderecoByCepUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.endereco.impl.FindEnderecoByCidadeUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.endereco.impl.FindEnderecoByIdUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.endereco.impl.FindEnderecoByRuaUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.endereco.impl.GetAllEnderecosUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.feedback.CreateFeedBackUseCase;
import com.fiap.restaurant.booking.core.usecases.feedback.DeleteFeedBackUseCase;
import com.fiap.restaurant.booking.core.usecases.feedback.FindFeedBackByIdRestauranteUseCase;
import com.fiap.restaurant.booking.core.usecases.feedback.FindFeedBackByIdUseCase;
import com.fiap.restaurant.booking.core.usecases.feedback.GetAllFeedBackByNomeClienteUseCase;
import com.fiap.restaurant.booking.core.usecases.feedback.GetAllFeedBackUseCase;
import com.fiap.restaurant.booking.core.usecases.feedback.impl.CreateFeedBackUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.feedback.impl.DeleteFeedBackUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.feedback.impl.FindFeedBackByIdRestauranteUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.feedback.impl.FindFeedBackByIdUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.feedback.impl.GetAllFeedBackByNomeClienteUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.feedback.impl.GetAllFeedBackUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.mesa.BookMesaUseCase;
import com.fiap.restaurant.booking.core.usecases.mesa.CreateMesaUseCase;
import com.fiap.restaurant.booking.core.usecases.mesa.DeleteMesaUseCase;
import com.fiap.restaurant.booking.core.usecases.mesa.FindIdRestauranteAndNumeroMesa;
import com.fiap.restaurant.booking.core.usecases.mesa.FindMesaByIdUseCase;
import com.fiap.restaurant.booking.core.usecases.mesa.FindMesaByRestauranteUseCase;
import com.fiap.restaurant.booking.core.usecases.mesa.FindMesaByStatusUseCase;
import com.fiap.restaurant.booking.core.usecases.mesa.FindMesasByIdRestauranteUseCase;
import com.fiap.restaurant.booking.core.usecases.mesa.impl.BookMesaUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.mesa.impl.CreateMesaUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.mesa.impl.DeleteMesaUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.mesa.impl.FindIdRestauranteAndNumeroMesaImpl;
import com.fiap.restaurant.booking.core.usecases.mesa.impl.FindMesaByIdRestauranteUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.mesa.impl.FindMesaByIdUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.mesa.impl.FindMesaByRestauranteUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.mesa.impl.FindMesaByStatusUseCaseImpl;
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
import com.fiap.restaurant.booking.core.usecases.restaurante.CreateRestauranteUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByCnpjUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByEnderecoBairroUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByEnderecoCidadeUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByEnderecoRuaUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByIdUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByMediaFeedbackUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByNomeUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByTipoCulinariaUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.GetAllRestaurantesUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.CreateRestauranteUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.FindRestauranteByCnpjUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.FindRestauranteByEnderecoBairroUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.FindRestauranteByEnderecoCidadeUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.FindRestauranteByEnderecoRuaUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.FindRestauranteByIdUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.FindRestauranteByMediaFeedbackUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.FindRestauranteByNomeUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.FindRestauranteByTipoCulinariaUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.restaurante.impl.GetAllRestaurantesUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public CreateReservaUseCase createReservaUseCase(
            ReservaGateway reservaGateway,
            FindRestauranteByIdUseCase findRestauranteByIdUseCase,
            FindMesaByIdUseCase findMesaByIdUseCase,
            FindReservaByCpfUseCase findReservaByCpfUseCase,
            BookMesaUseCase bookMesaUseCase
    ) {
        return new CreateReservaUseCaseImpl(
                reservaGateway,
                findRestauranteByIdUseCase,
                findMesaByIdUseCase,
                findReservaByCpfUseCase,
                bookMesaUseCase
        );
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
    public AtualizaEnderecoUseCase atualizaEnderecoUseCase(FindEnderecoByIdUseCase findEnderecoByIdUseCase, EnderecoGateway enderecoGateway) {
        return new AtualizaEnderecoUseCaseImpl(findEnderecoByIdUseCase, enderecoGateway);
    }

    @Bean
    public CreateEnderecoUseCase createEnderecoUseCase(EnderecoGateway enderecoGateway) {
        return new CreateEnderecoUseCaseImpl(enderecoGateway);
    }

    @Bean
    public DeleteEnderecoUseCase deleteEnderecoUseCase(FindEnderecoByIdUseCase findEnderecoByIdUseCase, EnderecoGateway enderecoGateway) {
        return new DeleteEnderecoUseCaseImpl(findEnderecoByIdUseCase, enderecoGateway);
    }

    @Bean
    public FindEnderecoByBairroUseCase findEnderecoByBairroUseCase(EnderecoGateway enderecoGateway) {
        return new FindEnderecoByBairroUseCaseImpl(enderecoGateway);
    }

    @Bean
    public FindEnderecoByCepUseCase findEnderecoByCepUseCase(EnderecoGateway enderecoGateway) {
        return new FindEnderecoByCepUseCaseImpl(enderecoGateway);
    }

    @Bean
    public FindEnderecoByCidadeUseCase findEnderecoByCidadeUseCase(EnderecoGateway enderecoGateway) {
        return new FindEnderecoByCidadeUseCaseImpl(enderecoGateway);
    }

    @Bean
    public FindEnderecoByIdUseCase findEnderecoByIdUseCase(EnderecoGateway enderecoGateway) {
        return new FindEnderecoByIdUseCaseImpl(enderecoGateway);
    }

    @Bean
    public FindEnderecoByRuaUseCase findEnderecoByRuaUseCase(EnderecoGateway enderecoGateway) {
        return new FindEnderecoByRuaUseCaseImpl(enderecoGateway);
    }

    @Bean
    public GetAllEnderecosUseCase getAllEnderecosUseCase(EnderecoGateway enderecoGateway) {
        return new GetAllEnderecosUseCaseImpl(enderecoGateway);
    }

    @Bean
    public FindRestauranteByCnpjUseCase findRestauranteByCnpjUseCase(RestauranteGateway restauranteGateway) {
        return new FindRestauranteByCnpjUseCaseImpl(restauranteGateway);
    }

    @Bean
    public CreateRestauranteUseCase createRestauranteUseCase(RestauranteGateway restauranteGateway, FindRestauranteByCnpjUseCase findReservaByCpfUseCase) {
        return new CreateRestauranteUseCaseImpl(restauranteGateway, findReservaByCpfUseCase);
    }

    @Bean
    public GetAllRestaurantesUseCase getAllRestaurantesUseCase(RestauranteGateway restauranteGateway) {
        return new GetAllRestaurantesUseCaseImpl(restauranteGateway);
    }

    @Bean
    public FindRestauranteByIdUseCase findRestauranteByIdUseCase(RestauranteGateway restauranteGateway) {
        return new FindRestauranteByIdUseCaseImpl(restauranteGateway);
    }

    @Bean
    public FindRestauranteByNomeUseCase findRestauranteByNomeUseCase(RestauranteGateway restauranteGateway) {
        return new FindRestauranteByNomeUseCaseImpl(restauranteGateway);
    }

    @Bean
    public FindRestauranteByEnderecoRuaUseCase findRestauranteByEnderecoRuaUseCase(RestauranteGateway restauranteGateway) {
        return new FindRestauranteByEnderecoRuaUseCaseImpl(restauranteGateway);
    }

    @Bean
    public FindRestauranteByEnderecoBairroUseCase findRestauranteByEnderecoBairroUseCase(RestauranteGateway restauranteGateway) {
        return new FindRestauranteByEnderecoBairroUseCaseImpl(restauranteGateway);
    }

    @Bean
    public FindRestauranteByEnderecoCidadeUseCase findRestauranteByEnderecoCidadeUseCase(RestauranteGateway restauranteGateway) {
        return new FindRestauranteByEnderecoCidadeUseCaseImpl(restauranteGateway);
    }

    @Bean
    public FindRestauranteByTipoCulinariaUseCase findRestauranteByTipoCulinariaUseCase(RestauranteGateway restauranteGateway) {
        return new FindRestauranteByTipoCulinariaUseCaseImpl(restauranteGateway);
    }

    @Bean
    public FindRestauranteByMediaFeedbackUseCase findRestauranteByMediaFeedbackUseCase(RestauranteGateway restauranteGateway) {
        return new FindRestauranteByMediaFeedbackUseCaseImpl(restauranteGateway);
    }

    @Bean
    public GetAllFeedBackUseCase findAllFeedBackUseCase(FeedBackGateway feedBackGateway) {
        return new GetAllFeedBackUseCaseImpl(feedBackGateway);
    }

    @Bean
    public GetAllFeedBackByNomeClienteUseCase findAllFeedBackByNomeClienteUseCase(FeedBackGateway feedBackGateway) {
        return new GetAllFeedBackByNomeClienteUseCaseImpl(feedBackGateway);
    }

    @Bean
    public FindFeedBackByIdUseCase findFeedBackByIdUseCase(FeedBackGateway feedBackGateway) {
        return new FindFeedBackByIdUseCaseImpl(feedBackGateway);
    }

    @Bean
    public FindFeedBackByIdRestauranteUseCase findByIdRestauranteFeedBackUseCase(FeedBackGateway feedBackGateway) {
        return new FindFeedBackByIdRestauranteUseCaseImpl(feedBackGateway);
    }

    @Bean
    public CreateMesaUseCase createMesaUseCase(MesaGateway mesaGateway, FindRestauranteByIdUseCase findRestauranteByIdUseCase) {
        return new CreateMesaUseCaseImpl(mesaGateway, findRestauranteByIdUseCase);
    }

    @Bean
    public DeleteFeedBackUseCase deleteFeedBackUseCase(FindFeedBackByIdUseCase findFeedBackByIdUseCase, FeedBackGateway feedBackGateway) {
        return new DeleteFeedBackUseCaseImpl(feedBackGateway, findFeedBackByIdUseCase);
    }

    @Bean
    public FindMesaByRestauranteUseCase findMesaByRestauranteUseCase(MesaGateway mesaGateway, FindRestauranteByIdUseCase findRestauranteByIdUseCase) {
        return new FindMesaByRestauranteUseCaseImpl(mesaGateway, findRestauranteByIdUseCase);
    }

    @Bean
    public FindMesaByIdUseCase findMesaByIdUseCase(MesaGateway mesaGateway) {
        return new FindMesaByIdUseCaseImpl(mesaGateway);
    }

    @Bean
    public FindMesaByStatusUseCase findMesaByStatusUseCase(MesaGateway mesaGateway) {
        return new FindMesaByStatusUseCaseImpl(mesaGateway);
    }

    @Bean
    public DeleteMesaUseCase deleteMesaUseCase(MesaGateway mesaGateway, FindIdRestauranteAndNumeroMesa findIdRestauranteAndNumeroMesa, FindRestauranteByIdUseCase findRestauranteByIdUseCase) {
        return new DeleteMesaUseCaseImpl(mesaGateway, findIdRestauranteAndNumeroMesa, findRestauranteByIdUseCase);
    }

    @Bean
    public FindIdRestauranteAndNumeroMesa findIdRestauranteAndNumeroMesa(MesaGateway mesaGateway) {
        return new FindIdRestauranteAndNumeroMesaImpl(mesaGateway);
    }

    @Bean
    public FindMesasByIdRestauranteUseCase findMesasByIdRestauranteUseCase(MesaGateway mesaGateway) {
        return new FindMesaByIdRestauranteUseCaseImpl(mesaGateway);
    }

    @Bean
    public BookMesaUseCase bookMesaUseCase(MesaGateway mesaGateway, FindMesaByIdUseCase findMesaByIdUseCase) {
        return new BookMesaUseCaseImpl(mesaGateway, findMesaByIdUseCase);
    }
}
