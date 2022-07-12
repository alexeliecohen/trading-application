package com.tradingapplication.controller;

import com.tradingapplication.model.currency.CurrencyPair;
import org.reactivestreams.Publisher;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/trading-application/currency-pair")
public class CurrencyPairRestController {
  private final RSocketRequester rSocketRequester;

  public CurrencyPairRestController(RSocketRequester rSocketRequester) {
    this.rSocketRequester = rSocketRequester;
  }

  @GetMapping
  public Publisher<CurrencyPair> current() {
    return rSocketRequester
        .route("currentMarketData")
//        .data(
//            new CurrencyPair(0, "CADUSD", 1.35, 100)
//        )
        .retrieveMono(CurrencyPair.class);
  }
}
