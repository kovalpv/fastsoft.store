package com.fastsoft.store.application.order;

public record OrderItemDto(
    String code,
    String name,
    Double price,
    Double amount,
    Integer count,
    Double vatPercent,
    Double vat
) {

}
