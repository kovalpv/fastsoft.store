package com.fastsoft.store.application.order;

import java.time.LocalDate;
import java.util.List;

public record OrderDto(
    String code,
    Integer number,
    LocalDate date,
    Double amount,
    Double vat,
    List<OrderItemDto> items
) {

}
