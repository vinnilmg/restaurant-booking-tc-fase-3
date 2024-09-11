package com.fiap.restaurant.booking.infrastructure.controllers;

import com.fiap.restaurant.booking.core.usecases.feedback.CreateFeedBackUseCase;
import com.fiap.restaurant.booking.core.usecases.feedback.DeleteFeedBackUseCase;
import com.fiap.restaurant.booking.core.usecases.feedback.GetAllFeedBackByNomeClienteUseCase;
import com.fiap.restaurant.booking.core.usecases.feedback.GetAllFeedBackUseCase;
import com.fiap.restaurant.booking.infrastructure.controllers.mappers.FeedBackMapper;
import com.fiap.restaurant.booking.infrastructure.controllers.request.FeedBackRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.FeedBackResponse;
import com.fiap.restaurant.booking.infrastructure.controllers.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedBackController {

    private final CreateFeedBackUseCase createFeedBackUseCase;

    private final GetAllFeedBackByNomeClienteUseCase getAllFeedBackByNomeClienteUseCase;

    private final GetAllFeedBackUseCase getAllFeedBackUseCase;

    private final DeleteFeedBackUseCase deleteFeedBackUseCase;

    private final FeedBackMapper feedBackMapper;

    public FeedBackController(CreateFeedBackUseCase createFeedBackUseCase, GetAllFeedBackByNomeClienteUseCase getAllFeedBackByNomeClienteUseCase, GetAllFeedBackUseCase getAllFeedBackUseCase, DeleteFeedBackUseCase deleteFeedBackUseCase, FeedBackMapper feedBackMapper) {
        this.createFeedBackUseCase = createFeedBackUseCase;
        this.getAllFeedBackByNomeClienteUseCase = getAllFeedBackByNomeClienteUseCase;
        this.getAllFeedBackUseCase = getAllFeedBackUseCase;
        this.deleteFeedBackUseCase = deleteFeedBackUseCase;
        this.feedBackMapper = feedBackMapper;
    }

    @PostMapping
    public ResponseEntity<FeedBackResponse> createFeedback(@RequestBody final FeedBackRequest feedBackRequest) {
        var response = feedBackMapper.toFeedbackResponse(
                createFeedBackUseCase.execute(feedBackMapper.toFeedBackDomain(feedBackRequest)));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/nome-cliente/{nomeCliente}")
    public ResponseEntity<List<FeedBackResponse>> getFeedbacksByNomeCliente(@PathVariable final String nomeCliente) {
        final var responses = getAllFeedBackByNomeClienteUseCase.execute(nomeCliente)
                .stream()
                .map(feedBackMapper::toFeedbackResponse)
                .toList();

        return ResponseEntity.status(OK).body(responses);
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
      return  ResponseEntity.status(OK).body(deleteFeedBackUseCase.execute(id));
    }

}
