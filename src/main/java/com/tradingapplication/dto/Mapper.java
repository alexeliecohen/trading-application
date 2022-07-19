package com.tradingapplication.dto;

import com.tradingapplication.dto.response.CurrencyPairResponse;
import com.tradingapplication.model.currency.CurrencyPair;

import java.util.Currency;

public class Mapper {
  public static CurrencyPairResponse map(CurrencyPair currencyPair) {
    return new CurrencyPairResponse(
        currencyPair.getName(),
        currencyPair.getRate(),
        currencyPair.getAmount()
    );
  }
}
