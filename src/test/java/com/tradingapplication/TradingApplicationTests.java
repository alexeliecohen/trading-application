package com.tradingapplication;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rsocket.server.LocalRSocketServerPort;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;

@SpringBootTest
class TradingApplicationTests {
    private static RSocketRequester requester;

    @BeforeAll
    public static void setupOnce(@Autowired RSocketRequester.Builder builder,
                                 @LocalRSocketServerPort Integer port,
                                 @Autowired RSocketStrategies strategies) {
        requester = builder
            .tcp("localhost",port);
    }

    @Test
    void contextLoads() {
    }

}
