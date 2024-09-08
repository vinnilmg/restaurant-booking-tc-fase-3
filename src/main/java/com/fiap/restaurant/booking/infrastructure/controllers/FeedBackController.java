package com.fiap.restaurant.booking.infrastructure.controllers;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.core.usecases.feedback.CreateFeedBackUseCase;
import com.fiap.restaurant.booking.infrastructure.controllers.request.FeedBackRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.FeedBackResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/feedback")
@RestController
public class FeedBackController {

    private final CreateFeedBackUseCase createFeedBackUseCase;

    public FeedBackController(CreateFeedBackUseCase createFeedBackUseCase) {
        this.createFeedBackUseCase = createFeedBackUseCase;
    }
    @PostMapping
    public ResponseEntity<FeedBackResponse> createFeedback(@RequestBody final FeedBackRequest feedBackRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createFeedBackUseCase.execute(feedBackRequest));
    }
}
