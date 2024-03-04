package com.fastsoft.store;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
public class TestStoreConfiguration {

  @Bean
  @ServiceConnection
  PostgreSQLContainer<?> postgresContainer(DynamicPropertyRegistry registry) {
    var postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:15"));
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
    registry.add("spring.flyway.clean-disabled", () -> false);
    return postgres;
  }

}
