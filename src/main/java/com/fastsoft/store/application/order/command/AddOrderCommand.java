package com.fastsoft.store.application.order.command;

import com.fastsoft.store.application.order.OrderDto;
import com.fastsoft.store.domain.good.Good;
import com.fastsoft.store.domain.good.GoodRepository;
import com.fastsoft.store.domain.order.Order;
import com.fastsoft.store.domain.order.OrderCode;
import com.fastsoft.store.domain.order.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddOrderCommand {

  private final OrderRepository orderRepository;
  private final GoodRepository goodRepository;

  @Transactional
  public OrderCode execute(OrderDto orderDto) {
    Order order = orderRepository.createNew(orderDto.date());
    orderDto.items()
        .forEach(x -> {
          Good good = goodRepository.getByCode(x.code());
          order.addItem(good, x.count());
        });
    return orderRepository.save(order).getCode();
  }
}
