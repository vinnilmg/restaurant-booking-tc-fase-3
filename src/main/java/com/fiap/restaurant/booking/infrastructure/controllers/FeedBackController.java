package com.fiap.restaurant.booking.infrastructure.controllers;

import com.fiap.restaurant.booking.core.usecases.feedback.CreateFeedBackUseCase;
import com.fiap.restaurant.booking.core.usecases.feedback.DeleteFeedBackUseCase;
import com.fiap.restaurant.booking.core.usecases.feedback.FindFeedBackByIdRestauranteUseCase;
import com.fiap.restaurant.booking.core.usecases.feedback.FindFeedBackByIdUseCase;
import com.fiap.restaurant.booking.core.usecases.feedback.GetAllFeedBackByNomeClienteUseCase;
import com.fiap.restaurant.booking.core.usecases.feedback.GetAllFeedBackUseCase;
import com.fiap.restaurant.booking.infrastructure.controllers.mappers.FeedBackMapper;
import com.fiap.restaurant.booking.infrastructure.controllers.request.FeedBackRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.FeedBackResponse;
import com.fiap.restaurant.booking.infrastructure.controllers.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedBackController {

    private final FindFeedBackByIdUseCase findByIdFeedBackUseCase;

    private final CreateFeedBackUseCase createFeedBackUseCase;

    private final GetAllFeedBackByNomeClienteUseCase getAllFeedBackByNomeClienteUseCase;

    private final GetAllFeedBackUseCase getAllFeedBackUseCase;

    private final DeleteFeedBackUseCase deleteFeedBackUseCase;

    private final FindFeedBackByIdRestauranteUseCase findFeedBackByIdRestauranteUseCase;

    private final FeedBackMapper feedBackMapper;

    public FeedBackController(FindFeedBackByIdUseCase findByIdFeedBackUseCase, CreateFeedBackUseCase createFeedBackUseCase, GetAllFeedBackByNomeClienteUseCase getAllFeedBackByNomeClienteUseCase, GetAllFeedBackUseCase getAllFeedBackUseCase, DeleteFeedBackUseCase deleteFeedBackUseCase, FindFeedBackByIdRestauranteUseCase findFeedBackByIdRestauranteUseCase, FeedBackMapper feedBackMapper) {
        this.findByIdFeedBackUseCase = findByIdFeedBackUseCase;
        this.createFeedBackUseCase = createFeedBackUseCase;
        this.getAllFeedBackByNomeClienteUseCase = getAllFeedBackByNomeClienteUseCase;
        this.getAllFeedBackUseCase = getAllFeedBackUseCase;
        this.deleteFeedBackUseCase = deleteFeedBackUseCase;
        this.findFeedBackByIdRestauranteUseCase = findFeedBackByIdRestauranteUseCase;
        this.feedBackMapper = feedBackMapper;
    }


    @PostMapping
    public ResponseEntity<FeedBackResponse> createFeedback(@RequestBody final FeedBackRequest feedBackRequest) {
        var feedbackDomain = feedBackMapper.toFeedBackDomain(feedBackRequest);

        final var response = feedBackMapper.toFeedbackResponse(createFeedBackUseCase.execute(feedbackDomain, feedBackRequest.restauranteId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/nome-cliente/{nomeCliente}")
    public ResponseEntity<List<FeedBackResponse>> getFeedbacksByIdRestaurante(@PathVariable final String nomeCliente) {
        final var responses = getAllFeedBackByNomeClienteUseCase.execute(nomeCliente)
                .stream()
                .map(feedBackMapper::toFeedbackResponse)
                .toList();

        return ResponseEntity.status(OK).body(responses);
    }

    @GetMapping("/restaurante/{idRestaurante}")
    public ResponseEntity<FeedBackResponse> getFeedbacksByIdRestaurante(@PathVariable final Long idRestaurante) {
        final var response = feedBackMapper.toFeedbackResponse(findFeedBackByIdRestauranteUseCase.execute(idRestaurante));
        return ResponseEntity.status(OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<FeedBackResponse>> getAllFeedbacks() {
        final var responses = getAllFeedBackUseCase.execute()
                .stream()
                .map(feedBackMapper::toFeedbackResponse)
                .toList();

        return ResponseEntity.status(OK).body(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFeedBackById(@PathVariable Long id) {
        return ResponseEntity.status(OK).body(deleteFeedBackUseCase.execute(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedBackResponse> getFeedBackById(@PathVariable Long id) {
        final var response = feedBackMapper.toFeedbackResponse(findByIdFeedBackUseCase.execute(id));
        return ResponseEntity.status(OK).body(response);
    }
}
