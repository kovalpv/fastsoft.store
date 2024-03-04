package com.fastsoft.store.application.order;

import com.fastsoft.store.domain.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

  OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

  @Mapping(target = "code", source = "code.code")
  OrderDto asOrder(Order order);

}
