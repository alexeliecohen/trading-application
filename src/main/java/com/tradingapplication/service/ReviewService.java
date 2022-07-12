package com.tradingapplication.service;

import com.tradingapplication.model.Review;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class ReviewService {
  public Flux<Review> getReviews(long id) {
    var reviews = List.of(
        new Review(1, 1, 3.4, "nice book"),
        new Review(2, 2, 4.5, "great book"),
        new Review(3, 3, 2.2, "bad book")
    );
    return Flux.fromIterable(reviews);
  }

  public Mono<Review> getReviewById(int id) {
    var review = new Review(1, 1, 3.4, "nice book");
    return Mono.just(review);
  }


}
