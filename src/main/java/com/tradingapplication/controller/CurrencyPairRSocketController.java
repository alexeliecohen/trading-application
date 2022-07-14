package com.tradingapplication.controller;

import com.tradingapplication.dto.request.CurrencyPairRequest;
import com.tradingapplication.model.currency.CurrencyPair;
import com.tradingapplication.service.CurrencyPairService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class CurrencyPairRSocketController {

  private CurrencyPairService currencyPairService;
  public CurrencyPairRSocketController() {
    currencyPairService = new CurrencyPairService();
  }

  @MessageMapping("currencyPairData")
  public Flux<CurrencyPair> getCurrencyPairs() {
    return Flux.fromStream(currencyPairService.getAllCurrencyPair());
  }

  @MessageMapping("getCurrencyPair")
//  public Mono<CurrencyPair> getCurrencyPair(@PathVariable @Valid @Min(1) int id) {
  public Mono<CurrencyPair> getCurrencyPair(CurrencyPairRequest currencyPairRequest) {
//    log.info("Received currency pair id: " + id);
    log.info("Received currency pair id: " + 0);
    log.info(String.format(
        "Received request %d, %s, %.2f, %d",
        currencyPairRequest.id(),
        currencyPairRequest.name(),
        currencyPairRequest.rate(),
        currencyPairRequest.amount()
    ));
    return Mono.just(new CurrencyPair(0, "CADUSD", 1.35, 150));
  }

  @MessageMapping("hello")
  public Mono<String> hello(String data) {
    log.info(String.format("Received %s Request", data));
    return Mono.just("Hello Back!");
  }
}
