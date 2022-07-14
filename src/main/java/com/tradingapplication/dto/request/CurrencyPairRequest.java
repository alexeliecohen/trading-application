package com.tradingapplication.dto.request;

import lombok.ToString;

public record CurrencyPairRequest(
    int id,
    String name,
    double rate,
    long amount


) {

  @Override
  public String toString() {
    return "CurrencyPairRequest{" + "id=" + id +
        ", name='" + name + '\'' +
        ", rate=" + rate +
        ", amount=" + amount +
        '}';
  }
}
