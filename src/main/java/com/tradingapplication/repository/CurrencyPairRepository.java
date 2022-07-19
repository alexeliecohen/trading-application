package com.tradingapplication.repository;

import com.tradingapplication.model.currency.CurrencyPair;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrencyPairRepository extends ReactiveCrudRepository<CurrencyPair, Long> {
  @Query("select c from CurrencyPair c where c.id = c.id")
  Mono<CurrencyPair> getCurrencyPairById();

  @Query("select b from CurrencyPair b")
  Flux<CurrencyPair> getAllCurrencyPairs();
}
