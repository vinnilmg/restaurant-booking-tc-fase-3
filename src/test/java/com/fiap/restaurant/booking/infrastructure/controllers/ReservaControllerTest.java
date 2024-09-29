package com.fiap.restaurant.booking.infrastructure.controllers;

import com.fiap.restaurant.booking.core.domains.Reserva;
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

import java.util.List;

import static com.fiap.restaurant.booking.utils.ConverterUtils.toJsonString;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_CPF;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_RESERVA_ID;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.JSON_PATH_CPF;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.JSON_PATH_DATA_HORA_CRIACAO;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.JSON_PATH_DATA_HORA_RESERVA;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.JSON_PATH_ID;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.JSON_PATH_LIST_CPF;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.JSON_PATH_LIST_DATA_HORA_CRIACAO;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.JSON_PATH_LIST_DATA_HORA_RESERVA;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.JSON_PATH_LIST_ID;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.JSON_PATH_LIST_STATUS;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.JSON_PATH_STATUS;
import static java.text.MessageFormat.format;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

        when(createReservaUseCase.execute(request.restauranteId(), request.mesaId(), reserva))
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
        verify(createReservaUseCase).execute(request.restauranteId(), request.mesaId(), reserva);
        verify(reservaMapper).toReservaResponse(reserva);
    }

    @Test
    void shouldGetAllBookings() throws Exception {
        final var reserva = ReservaDomainFixture.SOLICITADA();
        final List<Reserva> reservas = List.of(reserva);

        when(getAllReservasUseCase.execute())
                .thenReturn(reservas);

        when(reservaMapper.toReservasResponse(reservas))
                .thenReturn(List.of(ReservaResponseFixture.BY_DOMAIN(reserva)));

        mockMvc.perform(get(ENDPOINT))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PATH_LIST_ID).value(reserva.getId()))
                .andExpect(jsonPath(JSON_PATH_LIST_CPF).value(reserva.getCpf()))
                .andExpect(jsonPath(JSON_PATH_LIST_STATUS).value(reserva.getStatus()))
                .andExpect(jsonPath(JSON_PATH_LIST_DATA_HORA_RESERVA).value(reserva.getDataHoraReservaFormatted()))
                .andExpect(jsonPath(JSON_PATH_LIST_DATA_HORA_CRIACAO).value(reserva.getDataHoraCriacaoFormatted()));

        verify(getAllReservasUseCase).execute();
        verify(reservaMapper).toReservasResponse(reservas);
    }

    @Test
    void shouldGetBookingsByCpf() throws Exception {
        final var cpf = DEFAULT_CPF;
        final var reserva = ReservaDomainFixture.SOLICITADA();
        final List<Reserva> reservas = List.of(reserva);

        when(findReservaByCpfUseCase.execute(cpf))
                .thenReturn(reservas);

        when(reservaMapper.toReservasResponse(reservas))
                .thenReturn(List.of(ReservaResponseFixture.BY_DOMAIN(reserva)));

        mockMvc.perform(get(format("{0}/customers/{1}", ENDPOINT, cpf)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PATH_LIST_ID).value(reserva.getId()))
                .andExpect(jsonPath(JSON_PATH_LIST_CPF).value(reserva.getCpf()))
                .andExpect(jsonPath(JSON_PATH_LIST_STATUS).value(reserva.getStatus()))
                .andExpect(jsonPath(JSON_PATH_LIST_DATA_HORA_RESERVA).value(reserva.getDataHoraReservaFormatted()))
                .andExpect(jsonPath(JSON_PATH_LIST_DATA_HORA_CRIACAO).value(reserva.getDataHoraCriacaoFormatted()));

        verify(findReservaByCpfUseCase).execute(cpf);
        verify(reservaMapper).toReservasResponse(reservas);
    }

    @Test
    void shouldGetBookingById() throws Exception {
        final var id = DEFAULT_RESERVA_ID;
        final var reserva = ReservaDomainFixture.SOLICITADA();

        when(findReservaByIdUseCase.execute(id))
                .thenReturn(reserva);

        when(reservaMapper.toReservaResponse(reserva))
                .thenReturn(ReservaResponseFixture.BY_DOMAIN(reserva));

        mockMvc.perform(get(format("{0}/{1}", ENDPOINT), id))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PATH_ID).value(reserva.getId()))
                .andExpect(jsonPath(JSON_PATH_CPF).value(reserva.getCpf()))
                .andExpect(jsonPath(JSON_PATH_STATUS).value(reserva.getStatus()))
                .andExpect(jsonPath(JSON_PATH_DATA_HORA_RESERVA).value(reserva.getDataHoraReservaFormatted()))
                .andExpect(jsonPath(JSON_PATH_DATA_HORA_CRIACAO).value(reserva.getDataHoraCriacaoFormatted()));

        verify(findReservaByIdUseCase).execute(id);
        verify(reservaMapper).toReservaResponse(reserva);
    }

    @Test
    void shouldGetCanceledBookings() throws Exception {
        final var reserva = ReservaDomainFixture.CANCELADA();
        final List<Reserva> reservas = List.of(reserva);

        when(findCanceledReservasUseCase.execute())
                .thenReturn(reservas);

        when(reservaMapper.toReservasResponse(reservas))
                .thenReturn(List.of(ReservaResponseFixture.BY_DOMAIN(reserva)));

        mockMvc.perform(get(format("{0}/canceled", ENDPOINT)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PATH_LIST_ID).value(reserva.getId()))
                .andExpect(jsonPath(JSON_PATH_LIST_CPF).value(reserva.getCpf()))
                .andExpect(jsonPath(JSON_PATH_LIST_STATUS).value(reserva.getStatus()))
                .andExpect(jsonPath(JSON_PATH_LIST_DATA_HORA_RESERVA).value(reserva.getDataHoraReservaFormatted()))
                .andExpect(jsonPath(JSON_PATH_LIST_DATA_HORA_CRIACAO).value(reserva.getDataHoraCriacaoFormatted()));

        verify(findCanceledReservasUseCase).execute();
        verify(reservaMapper).toReservasResponse(reservas);
    }

    @Test
    void shouldGetRequestedBookings() throws Exception {
        final var reserva = ReservaDomainFixture.CANCELADA();
        final List<Reserva> reservas = List.of(reserva);

        when(findRequestedReservasUseCase.execute())
                .thenReturn(reservas);

        when(reservaMapper.toReservasResponse(reservas))
                .thenReturn(List.of(ReservaResponseFixture.BY_DOMAIN(reserva)));

        mockMvc.perform(get(format("{0}/requested", ENDPOINT)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PATH_LIST_ID).value(reserva.getId()))
                .andExpect(jsonPath(JSON_PATH_LIST_CPF).value(reserva.getCpf()))
                .andExpect(jsonPath(JSON_PATH_LIST_STATUS).value(reserva.getStatus()))
                .andExpect(jsonPath(JSON_PATH_LIST_DATA_HORA_RESERVA).value(reserva.getDataHoraReservaFormatted()))
                .andExpect(jsonPath(JSON_PATH_LIST_DATA_HORA_CRIACAO).value(reserva.getDataHoraCriacaoFormatted()));

        verify(findRequestedReservasUseCase).execute();
        verify(reservaMapper).toReservasResponse(reservas);
    }

    @Test
    void shouldGetConfirmedBookings() throws Exception {
        final var reserva = ReservaDomainFixture.CANCELADA();
        final List<Reserva> reservas = List.of(reserva);

        when(findConfirmedReservasUseCase.execute())
                .thenReturn(reservas);

        when(reservaMapper.toReservasResponse(reservas))
                .thenReturn(List.of(ReservaResponseFixture.BY_DOMAIN(reserva)));

        mockMvc.perform(get(format("{0}/confirmed", ENDPOINT)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PATH_LIST_ID).value(reserva.getId()))
                .andExpect(jsonPath(JSON_PATH_LIST_CPF).value(reserva.getCpf()))
                .andExpect(jsonPath(JSON_PATH_LIST_STATUS).value(reserva.getStatus()))
                .andExpect(jsonPath(JSON_PATH_LIST_DATA_HORA_RESERVA).value(reserva.getDataHoraReservaFormatted()))
                .andExpect(jsonPath(JSON_PATH_LIST_DATA_HORA_CRIACAO).value(reserva.getDataHoraCriacaoFormatted()));

        verify(findConfirmedReservasUseCase).execute();
        verify(reservaMapper).toReservasResponse(reservas);
    }

    @Test
    void shouldCancelBooking() throws Exception {
        final var id = DEFAULT_RESERVA_ID;

        doNothing()
                .when(cancelReservaUseCase)
                .execute(id);

        mockMvc.perform(put(format("{0}/cancel/{1}", ENDPOINT, id)))
                .andExpect(status().isNoContent());

        verify(cancelReservaUseCase).execute(id);
    }

    @Test
    void shouldConfirmBooking() throws Exception {
        final var id = DEFAULT_RESERVA_ID;

        doNothing()
                .when(confirmReservaUseCase)
                .execute(id);

        mockMvc.perform(put(format("{0}/confirm/{1}", ENDPOINT, id)))
                .andExpect(status().isNoContent());

        verify(confirmReservaUseCase).execute(id);
    }
}
