package com.tradingapplication.model;

public class CurrencyPair {
  private String name;
  private double rate;
  private long amount;

  public CurrencyPair() {
  }

  public CurrencyPair(String name, double rate, long amount) {
    this.name = name;
    this.rate = rate;
    this.amount = amount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getRate() {
    return rate;
  }

  public void setRate(double rate) {
    this.rate = rate;
  }

  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }
}
