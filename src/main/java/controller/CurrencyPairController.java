package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/v1/trading-application/currency-pair")
public class CurrencyPairController {
  @GetMapping()

  public Flux<CurrencyPair> getCurrencyPairs
}
