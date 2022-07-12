package com.tradingapplication.model;

public class Stock {
  private String symbol;
  private double price;
  private double yield;

  public Stock() {
  }

  public Stock(String symbol, double price, double yield) {
    this.symbol = symbol;
    this.price = price;
    this.yield = yield;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getYield() {
    return yield;
  }

  public void setYield(double yield) {
    this.yield = yield;
  }
}
