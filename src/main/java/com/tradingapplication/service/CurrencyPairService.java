package com.tradingapplication.service;

import com.tradingapplication.dto.request.CurrencyPairRequest;
import com.tradingapplication.model.currency.CurrencyPair;
import com.tradingapplication.repository.CurrencyPairRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CurrencyPairService {
//  private List<CurrencyPair> currencyPairs;
  private CurrencyPairRepository currencyPairRepository;

  /**
   * Default constructor for the currency pair service
   */
  public CurrencyPairService(CurrencyPairRepository currencyPairRepository) {
    this.currencyPairRepository = currencyPairRepository;
//    currencyPairs = List.of(
//        new CurrencyPair(1, "CADUSD", 1.31, 100),
//        new CurrencyPair(2, "CADEUR", 1.51, 300),
//        new CurrencyPair(3, "USDJPY", 1.61, 302),
//        new CurrencyPair(4, "AUSEUR", 1.21, 130)
//    );
  }

  public CurrencyPair getCurrencyPairByName(String name) {
    return null;


  }

  public Flux<CurrencyPair> getAllCurrencyPair() {
    return this.currencyPairRepository.getAllCurrencyPairs();
  }

  public Mono<CurrencyPair> addCurrencyPair(CurrencyPairRequest currencyPairRequest) {
    CurrencyPair currencyPair = CurrencyPair.builder()
        .amount(currencyPairRequest.amount())
        .name(currencyPairRequest.name())
        .rate(currencyPairRequest.rate())
        .build();
    currencyPairRepository.save(currencyPair);
    return Mono.just(currencyPair);
  }
}
