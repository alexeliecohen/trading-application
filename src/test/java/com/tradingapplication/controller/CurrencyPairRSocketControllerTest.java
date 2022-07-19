package com.tradingapplication.controller;

import com.tradingapplication.dto.request.CurrencyPairRequest;
import com.tradingapplication.model.currency.CurrencyPair;
import groovy.util.logging.Slf4j;
import io.restassured.internal.common.assertion.Assertion;
import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.tradingapplication.controller.IntegrationTest.postgreSqlProperties;

@SpringBootTest
@Slf4j
class CurrencyPairRSocketControllerTest extends AbstractTest {


  @Container
  public static PostgreSQLContainer<?> postgreSQL =
      new PostgreSQLContainer<>("postgres:13.2")
          .withUsername("testUsername")
          .withPassword("testPassword");

  @DynamicPropertySource
  public static void iniPostgrest(@NotNull DynamicPropertyRegistry registry) {
    postgreSqlProperties(registry, postgreSQL);
  }


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
        .expectError(Exception.class)
        .verify();
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

  @Test
  void addCurrencyPair() {
    var currencyPairRequest = new CurrencyPairRequest("CADUSD",1.3, 100L);
    RSocketRequester requester = createRSocketRequester();
    Mono<CurrencyPair> response = requester.route("addCurrencyPair")
        .data(currencyPairRequest)
        .retrieveMono(CurrencyPair.class);
    StepVerifier.create(response)
        .expectNext(new CurrencyPair(1,"CADUSD",1.3,100L))
        .expectComplete()
        .log()
        .verify();
  }
}