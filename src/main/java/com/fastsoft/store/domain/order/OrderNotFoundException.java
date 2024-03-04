package com.fastsoft.store.domain.order;

import com.fastsoft.store.domain.error.NotFoundException;

/**
 * Ошибка сообщающая о том, что заказ не найден.
 */
public class OrderNotFoundException extends NotFoundException {

  private final static String MESSAGE = "Order %s not found.";

  public OrderNotFoundException(OrderCode code) {
    super(String.format(MESSAGE, code.getCode()));
  }
}
