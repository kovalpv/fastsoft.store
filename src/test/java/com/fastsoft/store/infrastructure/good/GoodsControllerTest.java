package com.fastsoft.store.infrastructure.good;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fastsoft.store.TestStoreConfiguration;
import com.fastsoft.store.application.good.GoodDto;
import com.fastsoft.store.application.good.query.FindAllGoodsQuery;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(classes = TestStoreConfiguration.class)
@AutoConfigureMockMvc
class GoodsControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private FindAllGoodsQuery goodsQuery;

  @Autowired
  private ObjectMapper mapper;

  @Test
  void getGoods() throws Exception {
    var result = mvc.perform(get("/goods"))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andReturn();

    List<GoodDto> goods = goodsQuery.execute();
    assertThat(goods).isNotEmpty();
    assertThat(goods).usingRecursiveComparison()
        .isEqualTo(List.of(
            new GoodDto("BREAD::001", "bread", 18.0, 100.0),
            new GoodDto("BURGER::001", "burger", 20.0, 200.0),
            new GoodDto("WATER::001", "water", 20.0, 50.0)
        ));
    assertThat(result.getResponse().getContentAsString())
        .isEqualTo(mapper.writeValueAsString(goods));
  }

}
