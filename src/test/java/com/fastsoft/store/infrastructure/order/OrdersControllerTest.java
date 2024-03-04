package com.fastsoft.store.infrastructure.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fastsoft.store.TestStoreConfiguration;
import com.fastsoft.store.application.order.OrderDto;
import com.fastsoft.store.application.order.OrderItemDto;
import com.fastsoft.store.application.order.query.GetOrderQuery;
import com.fastsoft.store.domain.order.OrderCode;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import lombok.SneakyThrows;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(classes = TestStoreConfiguration.class)
@AutoConfigureMockMvc
class OrdersControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper mapper;

  @Autowired
  private GetOrderQuery orderQuery;


  @BeforeEach
  void clearDatabase(@Autowired Flyway flyway) {
    flyway.clean();
    flyway.migrate();
  }

  @Test
  void contextLoads() {
  }

  @Test
  @SneakyThrows
  void saveFirstOrder_Test() {

    var result = mvc.perform(post("/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding(StandardCharsets.UTF_8)
            .content("""
                {
                  "date": "2020-01-05",
                  "items": [
                    {"code": "BREAD::001", "count": 1 },
                    {"code": "WATER::001", "count": 2 }
                  ]
                }
                """))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andDo(print())
        .andReturn();

    var order = orderQuery.execute(new OrderCode("ORDER::2020-01-05::000001"));
    assertThat(result.getResponse().getContentAsString(StandardCharsets.UTF_8))
        .isEqualTo(
            mapper.writeValueAsString(order));

    assertThat(order)
        .usingRecursiveComparison()
        .isEqualTo(
            new OrderDto(
                "ORDER::2020-01-05::000001", 1,
                LocalDate.parse("2020-01-05"),
                200D,
                38D,
                List.of(
                    new OrderItemDto("ORDER::2020-01-05::000001::BREAD::001", "bread", 100.0, 100D,
                        1,
                        18.0, 18.0),
                    new OrderItemDto("ORDER::2020-01-05::000001::WATER::001", "water", 50.0, 100D,
                        2,
                        20.0, 20.0)
                )));
  }


  @Test
  @SneakyThrows
  void updateFirstOrder_Test() {

    saveFirstOrder_Test();
    var result = mvc.perform(put("/orders/ORDER::2020-01-05::000001")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding(StandardCharsets.UTF_8)
            .content("""
                {
                  "date": "2020-01-05",
                  "items": [
                    {"code": "BREAD::001", "count": 4 },
                    {"code": "WATER::001", "count": 2 },
                    {"code": "BURGER::001", "count": 10 }
                  ]
                }
                """))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andDo(print())
        .andReturn();

    var order = orderQuery.execute(new OrderCode("ORDER::2020-01-05::000001"));
    assertThat(result.getResponse().getContentAsString(StandardCharsets.UTF_8))
        .isEqualTo(
            mapper.writeValueAsString(order));

    assertThat(order)
        .usingRecursiveComparison()
        .isEqualTo(
            new OrderDto(
                "ORDER::2020-01-05::000001", 1,
                LocalDate.parse("2020-01-05"),
                2500D,
                492D,
                List.of(
                    new OrderItemDto("ORDER::2020-01-05::000001::BREAD::001", "bread", 100.0, 400D,
                        4,
                        18D, 72D),
                    new OrderItemDto("ORDER::2020-01-05::000001::WATER::001", "water", 50.0, 100D,
                        2,
                        20.0, 20.0),
                    new OrderItemDto("ORDER::2020-01-05::000001::BURGER::001", "burger", 200.0,
                        2000D, 10,
                        20.0, 400.0)
                )));

  }

  @Test
  @SneakyThrows
  void deleteFirstOrder_Test() {

    saveFirstOrder_Test();
    mvc.perform(delete("/orders/ORDER::2020-01-05::000001"))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andDo(print())
        .andReturn();
  }

  @Test
  @SneakyThrows
  void getShouldReturn404_Test() {
    var result = mvc.perform(get("/orders/ORDER::2020-01-05::000001"))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andDo(print())
        .andReturn();
  }

}