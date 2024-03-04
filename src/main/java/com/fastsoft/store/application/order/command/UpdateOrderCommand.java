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
public class UpdateOrderCommand {

  private final OrderRepository orderRepository;
  private final GoodRepository goodRepository;

  @Transactional
  public void execute(OrderCode code, OrderDto orderDto) {
    Order order = orderRepository.getByCode(code);
    order.clearItems();
    orderDto.items()
        .forEach(x -> {
          Good good = goodRepository.getByCode(x.code());
          order.addItem(good, x.count());
        });
    orderRepository.save(order);
  }
}
