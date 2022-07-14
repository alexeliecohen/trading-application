package com.tradingapplication.controller;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class IntegrationTest {
  @LocalServerPort
  protected int port;
  protected String uri;
  protected static int INVALID_ID = 9999;

//  public static void postgreSqlProperties(
//      @NotNull DynamicPropertyRegistry registry,
//      PostgreSQLContainer postgrest) {
//
//    registry.add("spring.datasource.url", postgrest::getJdbcUrl);
//    registry.add("spring.datasource.username", postgrest::getUsername);
//    registry.add("spring.datasource.password", postgrest::getPassword);
//  }

  @BeforeEach
  public void initialiseRestAssuredMockMvcStandalone() {
    uri = "http://localhost:" + port;
  }
}
