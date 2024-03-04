package com.fastsoft.store.infrastructure.order;

import com.fastsoft.store.application.order.OrderDto;
import com.fastsoft.store.application.order.command.AddOrderCommand;
import com.fastsoft.store.application.order.command.DeleteOrderCommand;
import com.fastsoft.store.application.order.command.UpdateOrderCommand;
import com.fastsoft.store.application.order.query.GetOrderQuery;
import com.fastsoft.store.domain.order.OrderCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

  private final AddOrderCommand addCommand;
  private final UpdateOrderCommand updateCommand;
  private final DeleteOrderCommand deleteCommand;
  private final GetOrderQuery getQuery;

  @GetMapping("/{order-code}")
  public ResponseEntity<OrderDto> getOrder(
      @PathVariable(name = "order-code") OrderCode code) {

    return ResponseEntity.ok(getQuery.execute(code));
  }

  @PostMapping
  public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto order) {

    var code = addCommand.execute(order);
    return ResponseEntity.ok(getQuery.execute(code));
  }

  @PutMapping("/{order-code}")
  public ResponseEntity<OrderDto> updateOrder(
      @PathVariable(name = "order-code") OrderCode code,
      @RequestBody OrderDto order) {

    updateCommand.execute(code, order);
    return ResponseEntity.ok(getQuery.execute(code));
  }

  @DeleteMapping("/{order-code}")
  public void deleteOrder(
      @PathVariable(name = "order-code") OrderCode code) {

    deleteCommand.execute(code);
  }
}
