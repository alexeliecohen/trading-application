package com.tradingapplication.config;

import io.rsocket.frame.decoder.PayloadDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeTypeUtils;
import reactor.util.retry.Retry;

import java.time.Duration;

@Configuration
public class ClientConfiguration {

  @Bean

  public RSocketRequester getRSocketRequester() {
    RSocketStrategies strategies = RSocketStrategies.builder()
        .encoders(encoders -> {
          encoders.add(new Jackson2JsonEncoder());
        })
        .decoders(decoders -> {
          decoders.add(new Jackson2JsonDecoder());
        })
        .build();
    RSocketRequester.Builder builder = RSocketRequester.builder();

    return builder
        .rsocketStrategies(strategies)
        .rsocketConnector(
            rSocketConnector ->
                rSocketConnector
                    .reconnect(Retry.fixedDelay(2, Duration.ofSeconds(2)))
        )
        .dataMimeType(MimeTypeUtils.APPLICATION_JSON)
        .tcp("localhost", 8000);
  }
}