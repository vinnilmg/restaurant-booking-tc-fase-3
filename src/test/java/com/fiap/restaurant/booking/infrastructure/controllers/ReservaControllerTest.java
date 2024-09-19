package com.fiap.restaurant.booking.infrastructure.controllers;

import com.fiap.restaurant.booking.core.domains.ReservaDomain;
import com.fiap.restaurant.booking.core.usecases.reserva.CancelReservaUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.ConfirmReservaUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.CreateReservaUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.FindCanceledReservasUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.FindConfirmedReservasUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.FindRequestedReservasUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.FindReservaByCpfUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.FindReservaByIdUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.GetAllReservasUseCase;
import com.fiap.restaurant.booking.infrastructure.controllers.mappers.ReservaMapper;
import com.fiap.restaurant.booking.utils.fixture.ReservaRequestFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.fiap.restaurant.booking.utils.ConverterUtils.toJsonString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReservaControllerTest {
    private static final String ENDPOINT = "/api/bookings";

    private MockMvc mockMvc;
    private AutoCloseable autoCloseable;
    private ReservaController reservaController;

    @Mock
    private CreateReservaUseCase createReservaUseCase;
    @Mock
    private GetAllReservasUseCase getAllReservasUseCase;
    @Mock
    private FindReservaByCpfUseCase findReservaByCpfUseCase;
    @Mock
    private FindCanceledReservasUseCase findCanceledReservasUseCase;
    @Mock
    private FindRequestedReservasUseCase findRequestedReservasUseCase;
    @Mock
    private FindConfirmedReservasUseCase findConfirmedReservasUseCase;
    @Mock
    private FindReservaByIdUseCase findReservaByIdUseCase;
    @Mock
    private CancelReservaUseCase cancelReservaUseCase;
    @Mock
    private ConfirmReservaUseCase confirmReservaUseCase;
    @Mock
    private ReservaMapper reservaMapper;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        reservaController = new ReservaController(
                createReservaUseCase,
                getAllReservasUseCase,
                findReservaByCpfUseCase,
                findCanceledReservasUseCase,
                findRequestedReservasUseCase,
                findConfirmedReservasUseCase,
                findReservaByIdUseCase,
                cancelReservaUseCase,
                confirmReservaUseCase,
                reservaMapper
        );

        mockMvc = MockMvcBuilders.standaloneSetup(reservaController)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void shouldCreateReserva() throws Exception {
        final var request = ReservaRequestFixture.FULL();
        final var reserva = mock(ReservaDomain.class);

        when(reservaMapper.toReserva(request))
                .thenReturn(reserva);

        when(createReservaUseCase.execute(reserva))
                .thenAnswer(i -> i.getArgument(0));

        mockMvc.perform(post(ENDPOINT)
                        .content(toJsonString(request))
                        .header("Content-type", "application/json"))
                .andExpect(status().isCreated());

        verify(reservaMapper).toReserva(request);
        verify(createReservaUseCase).execute(reserva);
    }
}
