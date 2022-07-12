package com.tradingapplication.controller;

import com.tradingapplication.model.currency.CurrencyPair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController("/v1/trading-application/currency-pair")
public class CurrencyPairController {

  @GetMapping
  public Flux<CurrencyPair> getCurrencyPairs() {

  }
}
