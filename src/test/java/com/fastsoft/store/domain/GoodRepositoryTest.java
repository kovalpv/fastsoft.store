package com.fastsoft.store.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import com.fastsoft.store.TestStoreConfiguration;
import com.fastsoft.store.domain.good.GoodRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(classes = TestStoreConfiguration.class)
class GoodRepositoryTest {

  @Autowired
  private GoodRepository goodRepository;

  @Test
  void isNotEmpty() {
    var goods = goodRepository.findAll();

    assertThat(goods).isNotNull();
    assertThat(goods).isNotEmpty();
  }

  @Test
  void checkDefaultGoods_Test() {

    assertTrue(goodRepository.existsById("BREAD::001"));
    assertTrue(goodRepository.existsById("BURGER::001"));
    assertTrue(goodRepository.existsById("WATER::001"));
  }
}