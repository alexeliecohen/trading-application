package com.tradingapplication.model.currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CurrencyPair {
  private int id;
  private String name;
  private double rate;
  private long amount;

//  public CurrencyPair() {
//  }
//
//  public CurrencyPair(int id, String name, double rate, long amount) {
//    this.id = id;
//    this.name = name;
//    this.rate = rate;
//    this.amount = amount;
//  }
//
//  public int getId() {
//    return id;
//  }
//
//  public void setId(int id) {
//    this.id = id;
//  }
//
//  public String getName() {
//    return name;
//  }
//
//  public void setName(String name) {
//    this.name = name;
//  }
//
//  public double getRate() {
//    return rate;
//  }
//
//  public void setRate(double rate) {
//    this.rate = rate;
//  }
//
//  public long getAmount() {
//    return amount;
//  }
//
//  public void setAmount(long amount) {
//    this.amount = amount;
//  }
}
