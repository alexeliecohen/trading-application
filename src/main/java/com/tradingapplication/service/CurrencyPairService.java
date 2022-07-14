package com.tradingapplication.service;

import com.tradingapplication.model.currency.CurrencyPair;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CurrencyPairService {
  private List<CurrencyPair> currencyPairs;

  /**
   * Default constructor for the currency pair service
   */
  public CurrencyPairService() {
    currencyPairs = List.of(
        new CurrencyPair(1, "CADUSD", 1.31, 100),
        new CurrencyPair(2, "CADEUR", 1.51, 300),
        new CurrencyPair(3, "USDJPY", 1.61, 302),
        new CurrencyPair(4, "AUSEUR", 1.21, 130)
    );
  }

  public Stream<CurrencyPair> getAllCurrencyPair() {
    return this.currencyPairs.stream();
  }
}
