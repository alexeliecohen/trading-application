package com.tradingapplication.controller;

import com.tradingapplication.dto.Mapper;
import com.tradingapplication.dto.request.CurrencyPairRequest;
import com.tradingapplication.dto.response.CurrencyPairResponse;
import com.tradingapplication.model.currency.CurrencyPair;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.data.repository.query.Param;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/v1/trading-application/currency-pair")
public class CurrencyPairRestController {
  private final RSocketRequester rSocketRequester;

  public CurrencyPairRestController(RSocketRequester rSocketRequester) {
    this.rSocketRequester = rSocketRequester;
  }

  @PostMapping("/currencyPair")
  public Publisher<CurrencyPairResponse> currencyPair(@RequestBody CurrencyPairRequest currencyPairRequest) {
//    log.info(String.format(
//        "Received request %d, %s, %.2f, %d",
//        currencyPairRequest.id(),
//        currencyPairRequest.name(),
//        currencyPairRequest.rate(),
//        currencyPairRequest.amount()
//    ));
    log.info(currencyPairRequest.toString());
    log.info("Rest Controller");
    return rSocketRequester
        .route("getCurrencyPair")
        .data(currencyPairRequest)
//        .retrieveMono()
        .retrieveMono(CurrencyPair.class)
        .map(Mapper::map)
        .log();
  }

  @PostMapping()
  public Publisher<String> hello(@RequestBody String message) {
    log.info(message);
    return rSocketRequester
        .route("hello")
        .data(message)
        .retrieveMono(String.class);
  }
}
