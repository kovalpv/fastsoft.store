package com.fastsoft.store.domain.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class OrderCodeTest {

  @Test
  void shouldReturnCode1_Test() {

    var actual = OrderCode.of(LocalDate.of(2020, 1, 31), 1);

    var expected = new OrderCode("ORDER::2020-01-31::000001");
    assertEquals(expected, actual);
  }

  @Test
  void shouldReturnCode2_Test() {

    var actual = OrderCode.of(LocalDate.of(2020, 1, 31), 401);

    var expected = 401;
    assertEquals(expected, actual.getNumber());
  }

}