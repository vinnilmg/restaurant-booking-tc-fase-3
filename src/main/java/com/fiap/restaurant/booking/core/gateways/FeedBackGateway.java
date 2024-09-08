package com.fiap.restaurant.booking.core.gateways;

import com.fiap.restaurant.booking.core.domains.FeedBack;

import java.util.List;
import java.util.Optional;

public interface FeedBackGateway {

    FeedBack create(Optional<FeedBack> feedBackModel);

    List<FeedBack> findAll();
}
