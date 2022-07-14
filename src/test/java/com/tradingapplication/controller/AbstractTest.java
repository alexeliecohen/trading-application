package com.tradingapplication.controller;

import io.rsocket.transport.netty.client.TcpClientTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeTypeUtils;
import reactor.util.retry.Retry;

import java.time.Duration;

public abstract class AbstractTest {

  @Value("${spring.rsocket.server.port}")
  private int serverPort;
  @Autowired
  private RSocketRequester.Builder builder;

  protected RSocketRequester createRSocketRequester() {
    RSocketStrategies strategies = RSocketStrategies.builder()
        .encoders(encoders -> {
          encoders.add(new Jackson2JsonEncoder());
        })
        .decoders(decoders -> {
          decoders.add(new Jackson2JsonDecoder());
        })
        .build();
    RSocketRequester.Builder builder = RSocketRequester.builder();

    return this.builder
        .rsocketStrategies(strategies)
        .rsocketConnector(
            rSocketConnector ->
                rSocketConnector
                    .reconnect(Retry.fixedDelay(2, Duration.ofSeconds(2)))
        )
        .dataMimeType(MimeTypeUtils.APPLICATION_JSON)
        .tcp("localhost", serverPort);
  }
}
