package com.tradingapplication.dto.request;

import lombok.ToString;

public record CurrencyPairRequest(
    String name,
    double rate,
    long amount


) {

  @Override
  public String toString() {
    return "CurrencyPairRequest{" +
        "name='" + name + '\'' +
        ", rate=" + rate +
        ", amount=" + amount +
        '}';
  }
}
