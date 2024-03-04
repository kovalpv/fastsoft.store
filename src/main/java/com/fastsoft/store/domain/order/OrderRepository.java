package com.fastsoft.store.domain.order;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, OrderCode> {

  @Query("""
      select max(o.number)
      from Order o
      where o.date = :date
      """)
  Optional<Integer> findMaxNumber(@Param("date") LocalDate date);

  default OrderCode nextCode(LocalDate date) {
    return findMaxNumber(date)
        .map(id -> OrderCode.of(date, id + 1))
        .orElse(OrderCode.of(date, 1));
  }

  default Order createNew(LocalDate date) {

    OrderCode code = nextCode(date);
    int number = code.getNumber();
    return new Order(code, number, date, LocalDateTime.now());
  }

  default Order getByCode(OrderCode code) {
    return findById(code)
        .orElseThrow(() -> new OrderNotFoundException(code));
  }
}
