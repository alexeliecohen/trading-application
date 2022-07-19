package com.tradingapplication.dto.response;

public record CurrencyPairResponse(
    String name,
    double rate,
    long amount
) {

}
