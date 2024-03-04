package com.fastsoft.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestStoreApplication {

  public static void main(String[] args) {
    SpringApplication.from(StoreApplication::main).with(TestConfiguration.class).run(args);
  }

}
