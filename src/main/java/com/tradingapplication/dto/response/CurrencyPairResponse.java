package com.tradingapplication.dto.response;

public record CurrencyPairResponse(
    int id,
    String name,
    double rate,
    long amount
) {

}
