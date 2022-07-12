package com.tradingapplication.controller;

import com.tradingapplication.model.currency.CurrencyPair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Slf4j
//@RestController("/v1/trading-application/currency-pair")
public class CurrencyPairRSocketController {

//  @GetMapping
  @MessageMapping("currencyPairData")
  public Flux<CurrencyPair> getCurrencyPairs() {
    return Flux.just(new CurrencyPair());
  }

//  @GetMapping("{id}")
  @MessageMapping("Get currency pair")
  public Mono<CurrencyPair> getCurrencyPair(@PathVariable @Valid @Min(1) int id) {
    log.info("Received currency pair id: " + id);
    return Mono.just(new CurrencyPair(id, "CADUSD", 1.35, 100));
  }
}
