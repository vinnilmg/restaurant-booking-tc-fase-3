package com.fiap.restaurant.booking.infrastructure.controllers;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.core.usecases.feedback.CreateFeedBackUseCase;
import com.fiap.restaurant.booking.core.usecases.feedback.DeleteFeedBackUseCase;
import com.fiap.restaurant.booking.core.usecases.feedback.FindFeedBackByIdRestauranteUseCase;
import com.fiap.restaurant.booking.core.usecases.feedback.FindFeedBackByIdUseCase;
import com.fiap.restaurant.booking.core.usecases.feedback.GetAllFeedBackByNomeClienteUseCase;
import com.fiap.restaurant.booking.core.usecases.feedback.GetAllFeedBackUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByIdUseCase;
import com.fiap.restaurant.booking.infrastructure.controllers.mappers.FeedBackMapper;
import com.fiap.restaurant.booking.infrastructure.controllers.mappers.FeedBackMapperImpl;
import com.fiap.restaurant.booking.infrastructure.controllers.response.MessageResponse;
import com.fiap.restaurant.booking.utils.ConverterUtils;
import com.fiap.restaurant.booking.utils.InformationsFeedbackConstants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FeedBackControllerTest {

    private MockMvc mockMvc;

    private AutoCloseable mock;

    @Mock
    private CreateFeedBackUseCase createFeedBackUseCase;

    @Mock
    private DeleteFeedBackUseCase deleteFeedBackUseCase;

    @Mock
    private FindFeedBackByIdUseCase findFeedBackByIdUseCase;

    @Mock
    private FindFeedBackByIdRestauranteUseCase findFeedBackByIdRestauranteUseCase;

    @Mock
    private GetAllFeedBackUseCase getAllFeedBackUseCase;

    @Mock
    private GetAllFeedBackByNomeClienteUseCase getAllFeedBackByNomeClienteUseCase;

    @Mock
    private FindRestauranteByIdUseCase findRestauranteByIdUseCase;

    private FeedBackMapper feedBackMapper;

    @BeforeEach
    void setUp() {
        this.feedBackMapper = new FeedBackMapperImpl();
        mock = MockitoAnnotations.openMocks(this);
        FeedBackController feedBackController = new FeedBackController(findFeedBackByIdUseCase
                , createFeedBackUseCase
                , getAllFeedBackByNomeClienteUseCase
                , getAllFeedBackUseCase
                , deleteFeedBackUseCase
                , findFeedBackByIdRestauranteUseCase
                , findRestauranteByIdUseCase
                , feedBackMapper);
        mockMvc = MockMvcBuilders.standaloneSetup(feedBackController).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }


    @Test
    void createFeedbackRequestTest() throws Exception {
        var feedback = InformationsFeedbackConstants.buildFeedBackTest(1L,
                1);

        when(createFeedBackUseCase.execute(any(FeedBack.class))).thenReturn(feedback);
        when(findRestauranteByIdUseCase.execute(any(Long.class))).thenReturn(feedback.getRestaurante());
        mockMvc.perform(
                post(InformationsFeedbackConstants.ROUTE_CONTROLLER_DEFAULT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConverterUtils.toJsonString(feedBackMapper.toFeedbackResponse(feedback)))
        ).andExpect(status().isCreated());

        verify(createFeedBackUseCase, times(1)).execute(any(FeedBack.class));
    }


    @Test
    void findByIdFeedbackRequestTest() throws Exception {

        Long id = 1L;

        when(findFeedBackByIdUseCase.execute(id)).thenReturn(any(FeedBack.class));

        mockMvc.perform(
                get(InformationsFeedbackConstants.ROUTE_CONTROLLER_FIND_BY_ID, id)
        ).andExpect(status().isOk());

        verify(findFeedBackByIdUseCase, times(1)).execute(anyLong());
    }

    @Test
    void setFindFeedBackByIdRestauranteUseCaseTest() throws Exception {

        Long id = 1L;

        when(findFeedBackByIdRestauranteUseCase.execute(id)).thenReturn(any(FeedBack.class));

        mockMvc.perform(
                get(InformationsFeedbackConstants.ROUTE_CONTROLLER_FIND_BY_RESTAURANTE_ID, id)
        ).andExpect(status().isOk());

        verify(findFeedBackByIdRestauranteUseCase, times(1)).execute(anyLong());
    }

    @Test
    void getAllFeedBackByNomeClienteTest() throws Exception {

        String paramNomeCliente = "teste";

        List<FeedBack> mockFeedbackList = new ArrayList<>();

        when(getAllFeedBackByNomeClienteUseCase.execute(paramNomeCliente)).thenReturn(mockFeedbackList);

        mockMvc.perform(
                get(InformationsFeedbackConstants.ROUTE_CONTROLLER_FIND_BY_NOME_CLIENTE, paramNomeCliente)
        ).andExpect(status().isOk());

        verify(getAllFeedBackByNomeClienteUseCase, times(1)).execute(any(String.class));
    }

    @Test
    void getAllFeedBackTest() throws Exception {
        List<FeedBack> mockFeedbackList = new ArrayList<>();

        when(getAllFeedBackUseCase.execute()).thenReturn(mockFeedbackList);

        mockMvc.perform(
                get(InformationsFeedbackConstants.ROUTE_CONTROLLER_DEFAULT)
        ).andExpect(status().isOk());

        verify(getAllFeedBackUseCase, times(1)).execute();
    }

    @Test
    void deleteFeedBackTest() throws Exception {
        Long id = 1L;
        when(deleteFeedBackUseCase.execute(id)).thenReturn(any(MessageResponse.class));

        mockMvc.perform(
                delete(InformationsFeedbackConstants.ROUTE_CONTROLLER_DELETE_BY_ID, id)
        ).andExpect(status().isOk());

        verify(deleteFeedBackUseCase, times(1)).execute(anyLong());
    }
}
