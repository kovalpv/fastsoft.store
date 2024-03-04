package com.fastsoft.store.application.good.query;

import com.fastsoft.store.application.good.GoodDto;
import com.fastsoft.store.domain.good.GoodRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAllGoodsQuery {

  private final static Sort DEFAULT_SORT = Sort.by(Order.asc("code"));
  private final GoodRepository goodRepository;

  @Transactional
  public List<GoodDto> execute() {
    return GoodMapper.MAPPER.asGoods(goodRepository.findAll(DEFAULT_SORT));
  }
}
