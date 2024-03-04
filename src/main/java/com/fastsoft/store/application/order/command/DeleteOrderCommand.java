package com.fastsoft.store.application.order.command;

import com.fastsoft.store.domain.order.Order;
import com.fastsoft.store.domain.order.OrderCode;
import com.fastsoft.store.domain.order.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteOrderCommand {

  private final OrderRepository orderRepository;

  @Transactional
  public void execute(OrderCode code) {
    Order order = orderRepository.getByCode(code);
    orderRepository.delete(order);
  }
}
