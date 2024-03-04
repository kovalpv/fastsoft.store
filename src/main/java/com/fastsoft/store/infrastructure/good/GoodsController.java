package com.fastsoft.store.infrastructure.good;

import com.fastsoft.store.application.good.GoodDto;
import com.fastsoft.store.application.good.query.FindAllGoodsQuery;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodsController {

  private final FindAllGoodsQuery findAllGoodsQuery;

  @GetMapping
  public ResponseEntity<List<GoodDto>> getGoods() {

    return ResponseEntity.ok(findAllGoodsQuery.execute());
  }
}
