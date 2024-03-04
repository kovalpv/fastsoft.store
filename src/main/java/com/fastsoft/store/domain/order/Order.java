package com.fastsoft.store.domain.order;

import com.fastsoft.store.domain.good.Good;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Закакз товаров.
 */
@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

  /**
   * Код заказа.
   */
  @EmbeddedId
  private OrderCode code;

  /**
   * Номер документв
   */
  @Column(name = "number")
  private Integer number;

  /**
   * Дата документа.
   */
  @Column(name = "date_at")
  private LocalDate date;

  /**
   * Итого стоимость.
   */
  @Column(name = "amount")
  @Setter(AccessLevel.NONE)
  private Double amount;

  /**
   * Итого НДС.
   */
  @Column(name = "vat")
  @Setter(AccessLevel.NONE)
  private Double vat;

  /**
   * Метка изменения времени.
   */
  @Version
  @Column(name = "modified_at")
  private LocalDateTime modifiedAt;

  /**
   * Строки заказа.
   */
  @ElementCollection
  @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_code"))
  @Setter(AccessLevel.NONE)
  private List<OrderItem> items = new LinkedList<>();

  public Order(OrderCode code, Integer number, LocalDate date, LocalDateTime modifiedAt) {

    this.code = code;
    this.number = number;
    this.date = date;
    this.modifiedAt = modifiedAt;
  }

  private void addItem(OrderItem orderItem) {
    this.items.add(orderItem);
    this.updateTotalValue();
  }

  private void updateTotalValue() {
    amount = items.stream().map(OrderItem::getAmount).mapToDouble(Double::doubleValue).sum();
    vat = items.stream().map(OrderItem::getVat).mapToDouble(Double::doubleValue).sum();
  }

  /**
   * Добавление товара в заказ.
   *
   * @param good  Товар.
   * @param count Количество добавленных товаров.
   */
  public void addItem(Good good, Integer count) {
    String code = getCode().getCode() + "::" + good.getCode();
    addItem(new OrderItem(code, good, count));
  }

  /**
   * Очистить заказ.
   */
  public void clearItems() {
    this.items.clear();
  }
}
