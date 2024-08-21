package com.arch.catalogservice;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

public class BaseIT {

  @Container
  @ServiceConnection
  static PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>("postgres:alpine");

}
