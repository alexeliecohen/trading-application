package com.tradingapplication;

import com.tradingapplication.model.currency.CurrencyPair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@SpringBootApplication//(exclude={DataSourceAutoConfiguration.class})
@Slf4j
@Controller
public class TradingApplication {
  public static void main(String[] args) {
    SpringApplication.run(TradingApplication.class, args);
  }

//  @GetMapping( "/{id}")
//  @MessageMapping("Get currency pair")
//  public Mono<CurrencyPair> getCurrencyPair(@PathVariable @Valid @Min(1) int id) {
//  public Mono<CurrencyPair> getCurrencyPair() {
//    log.info("Received currency pair id: ");
//  }

}
