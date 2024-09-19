package com.fiap.restaurant.booking.infrastructure.controllers;

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
import com.fiap.restaurant.booking.utils.fixture.ReservaDomainFixture;
import com.fiap.restaurant.booking.utils.fixture.ReservaRequestFixture;
import com.fiap.restaurant.booking.utils.fixture.ReservaResponseFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.fiap.restaurant.booking.utils.ConverterUtils.toJsonString;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.JSON_PATH_CPF;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.JSON_PATH_DATA_HORA_CRIACAO;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.JSON_PATH_DATA_HORA_RESERVA;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.JSON_PATH_ID;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.JSON_PATH_STATUS;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    private HttpHeaders headers;

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

        headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void shouldCreateReserva() throws Exception {
        final var request = ReservaRequestFixture.FULL();
        final var reserva = ReservaDomainFixture.SOLICITADA();

        when(reservaMapper.toReserva(request))
                .thenReturn(reserva);

        when(createReservaUseCase.execute(reserva))
                .thenAnswer(i -> i.getArgument(0));

        when(reservaMapper.toReservaResponse(reserva))
                .thenReturn(ReservaResponseFixture.BY_DOMAIN(reserva));


        mockMvc.perform(post(ENDPOINT)
                        .content(toJsonString(request))
                        .headers(headers))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(JSON_PATH_ID).value(reserva.getId()))
                .andExpect(jsonPath(JSON_PATH_CPF).value(reserva.getCpf()))
                .andExpect(jsonPath(JSON_PATH_STATUS).value(reserva.getStatus()))
                .andExpect(jsonPath(JSON_PATH_DATA_HORA_RESERVA).value(reserva.getDataHoraReservaFormatted()))
                .andExpect(jsonPath(JSON_PATH_DATA_HORA_CRIACAO).value(reserva.getDataHoraCriacaoFormatted()));

        verify(reservaMapper).toReserva(request);
        verify(createReservaUseCase).execute(reserva);
        verify(reservaMapper).toReservaResponse(reserva);
    }

  /*  @Test
    void shouldGetAllBookings() throws Exception {
        final var reserva = ReservaDomainFixture.SOLICITADA();

        when(getAllReservasUseCase.execute())
                .thenReturn(List.of(reserva));

        mockMvc.perform(get(ENDPOINT))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$[0].id").value(reserva.getId()));
    }*/
}
