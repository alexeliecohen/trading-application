package com.tradingapplication.controller;

import com.tradingapplication.model.currency.CurrencyPair;
import groovy.util.logging.Slf4j;
import io.restassured.internal.common.assertion.Assertion;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@Slf4j
class CurrencyPairRSocketControllerTest extends AbstractTest {

  @Test
  void getCurrencyPairs() {
    RSocketRequester requester = createRSocketRequester();
    var response = requester.route("currencyPairData")
        .retrieveFlux(CurrencyPair.class);
    StepVerifier.create(response)
        .expectNext(new CurrencyPair(1, "CADUSD", 1.31, 100))
        .expectNext(new CurrencyPair(2, "CADEUR", 1.51, 300))
        .expectNext(new CurrencyPair(3, "USDJPY", 1.61, 302))
        .expectNext(new CurrencyPair(4, "AUSEUR", 1.21, 130))
        .verifyComplete();
  }

  @Test
  void getCurrencyPair() {
  }


  @Test
  @DisplayName("Text hello world server")
  void hello() {
    RSocketRequester requester = createRSocketRequester();
    Mono<String> response = requester.route("hello")
        .data("hello")
        .retrieveMono(String.class);
    StepVerifier.create(response)
        .expectNext("Hello Back!")
        .expectComplete()
        .log()
        .verify();
  }
}