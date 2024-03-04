package com.fastsoft.store.application.order.query;

import com.fastsoft.store.application.order.OrderDto;
import com.fastsoft.store.application.order.OrderMapper;
import com.fastsoft.store.domain.order.OrderCode;
import com.fastsoft.store.domain.order.OrderNotFoundException;
import com.fastsoft.store.domain.order.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetOrderQuery {

  private final OrderRepository orderRepository;

  @Transactional
  public OrderDto execute(OrderCode code) {

    return orderRepository.findById(code)
        .map(OrderMapper.MAPPER::asOrder)
        .orElseThrow(() -> new OrderNotFoundException(code));
  }
}
